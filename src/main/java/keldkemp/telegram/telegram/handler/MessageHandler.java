package keldkemp.telegram.telegram.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

//TODO: Refactor
@Component
public class MessageHandler {

    public List<? extends BotApiMethod<?>> handle(Update update) {
        try {
            if (update.hasCallbackQuery()) {
                return handleCallbackQuery(update.getCallbackQuery());
            }
            return handlePrivateMessage(update.getMessage());
        } catch (RuntimeException e) {
            if (update.hasCallbackQuery()) {
                return handleError(update.getCallbackQuery().getMessage(), e);
            }
            return handleError(update.getMessage(), e);
        }
    }

    private List<? extends BotApiMethod<?>> handlePrivateMessage(Message message) {
        long userId = message.getChatId();
        String text = message.getText();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(userId));
        sendMessage.setText(text + "\ntest_private_msg");

        return List.of(sendMessage);
    }

    private List<? extends BotApiMethod<?>> handleCallbackQuery(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String data = callbackQuery.getData();
        Integer messageId = message.getMessageId();

        EditMessageText sendMessage = new EditMessageText();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setMessageId(messageId);
        sendMessage.setText("test_callback");

        return List.of(sendMessage);
    }

    private List<? extends BotApiMethod<?>> handleError(Message message, RuntimeException e) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setText(e.getMessage());

        return List.of(sendMessage);
    }
}
