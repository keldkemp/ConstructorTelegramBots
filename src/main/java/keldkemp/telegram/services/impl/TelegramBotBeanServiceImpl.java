package keldkemp.telegram.services.impl;

import keldkemp.telegram.configs.SettingsValue;
import keldkemp.telegram.models.TelegramBots;
import keldkemp.telegram.repositories.TelegramBotsRepository;
import keldkemp.telegram.telegram.config.WebHookBot;
import keldkemp.telegram.telegram.handler.MessageHandler;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("telegramBotBeanService")
public class TelegramBotBeanServiceImpl extends BeanFactoryServiceImpl {

    @Autowired
    private TelegramBotsRepository telegramBotsRepository;

    @Autowired
    private SettingsValue settingsValue;

    @Override
    protected void createBean(String token) {
        TelegramBots bot = telegramBotsRepository.getTelegramBotsByBotToken(token);
        WebHookBot newBot = new WebHookBot(bot.getBotName(), bot.getBotToken(),
                settingsValue.getAppUrl() + "/webhook/" + token, getHandler());
        applicationContext.getBeanFactory().registerSingleton(token, newBot);
    }

    @Override
    public WebHookBot getBean(String token) {
        try {
            return super.getBean(token, WebHookBot.class);
        } catch (NoSuchBeanDefinitionException e) {
            createBean(token);
        }
        return super.getBean(token, WebHookBot.class);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initAll() {
        List<TelegramBots> telegramBots = telegramBotsRepository.getTelegramBotsByIsActive(true);
        telegramBots.forEach(bot -> createBean(bot.getBotToken()));
    }

    private MessageHandler getHandler() {
        return applicationContext.getBean("messageHandler", MessageHandler.class);
    }
}
