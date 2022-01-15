package keldkemp.telegram.services.impl;

import keldkemp.telegram.models.TelegramBots;
import keldkemp.telegram.repositories.TelegramBotsRepository;
import keldkemp.telegram.services.TelegramBotService;
import keldkemp.telegram.services.UserService;
import org.apache.http.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TelegramBotServiceImpl implements TelegramBotService {

    Logger logger = LoggerFactory.getLogger(TelegramBotServiceImpl.class);

    @Autowired
    private TelegramBotsRepository tBotsRepository;

    @Autowired
    private UserService userService;

    @Override
    public List<TelegramBots> getBots() {
        return tBotsRepository.getTelegramBotsByUser(userService.getCurrentUser());
    }

    @Override
    public TelegramBots getBot(Long id) {
        TelegramBots bot = tBotsRepository.getById(id);
        checkUser(bot);
        return bot;
    }

    @Override
    @Transactional
    public TelegramBots save(TelegramBots bot) {
        bot.setUser(userService.getCurrentUser());
        validate(bot);
        return tBotsRepository.save(bot);
    }

    @Override
    public void delete(Long id) {
        TelegramBots bot = tBotsRepository.getById(id);
        checkUser(bot);
        tBotsRepository.deleteById(id);
    }

    private void validate(TelegramBots bot) {
        Asserts.notNull(bot.getBotToken(), "Bot token");
        Asserts.notNull(bot.getBotName(), "Bot name");
        checkUser(bot);
    }

    private void checkUser(TelegramBots bot) {
        TelegramBots botPo;
        if (!isNewBot(bot)) {
            botPo = tBotsRepository.getTelegramBotsByBotToken(bot.getBotToken());
        } else {
            botPo = bot;
        }
        Asserts.check(botPo.getUser().equals(userService.getCurrentUser()),
                "Error user");
    }

    private boolean isNewBot(TelegramBots bot) {
        return bot.getId() == null;
    }
}
