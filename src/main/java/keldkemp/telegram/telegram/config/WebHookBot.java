package keldkemp.telegram.telegram.config;

import keldkemp.telegram.telegram.handler.MessageHandler;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;


public class WebHookBot extends TelegramWebhookBot {

    private final String botToken;
    private final String botPath;
    private final String botUsername;
    private final MessageHandler messageHandler;

    public WebHookBot(String botUsername, String botToken, String botPath, MessageHandler handler) {
        super();
        this.botPath = botPath;
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.messageHandler = handler;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return botPath;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        //TODO: Refactor
        handleMessage(update);
        return null;
    }

    //TODO: Refactor
    private void handleMessage(Update update) {
        List<? extends BotApiMethod<?>> messages = messageHandler.handle(update);
        messages.forEach(message -> {
            try {
                execute(message);
            } catch (Exception e) {

            }
        });
    }
}
