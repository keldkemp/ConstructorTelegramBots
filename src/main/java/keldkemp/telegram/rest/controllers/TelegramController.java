package keldkemp.telegram.rest.controllers;

import keldkemp.telegram.services.BeanFactoryService;
import keldkemp.telegram.telegram.config.WebHookBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class TelegramController {

    @Autowired
    @Qualifier("telegramBotBeanService")
    private BeanFactoryService telegramBotsBean;

    @PostMapping("/webhook/{token}")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update, @PathVariable String token) {
        WebHookBot bot = telegramBotsBean.getBean(token);
        return bot.onWebhookUpdateReceived(update);
    }
}
