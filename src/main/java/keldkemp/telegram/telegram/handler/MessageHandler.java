package keldkemp.telegram.telegram.handler;

import keldkemp.telegram.models.*;
import keldkemp.telegram.repositories.*;
import keldkemp.telegram.telegram.domain.MessageTypes;
import keldkemp.telegram.telegram.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

//TODO: Refactor
@Component
public class MessageHandler {

    @Autowired
    private MessageService messageService;

    @Autowired
    private TelegramBotsRepository tBotsRepository;

    @Autowired
    private TelegramStagesRepository tStagesRepository;

    @Autowired
    private TelegramButtonsRepository tButtonsRepository;


    public List<? extends BotApiMethod<?>> handle(Update update, String token) {
        TelegramBots bot = tBotsRepository.getTelegramBotsByBotToken(token);
        try {
            if (update.hasCallbackQuery()) {
                return handleCallbackQuery(update.getCallbackQuery(), bot);
            }
            return handlePrivateMessage(update.getMessage(), bot);
        } catch (RuntimeException e) {
            if (update.hasCallbackQuery()) {
                return handleError(update.getCallbackQuery().getMessage(), e);
            }
            return handleError(update.getMessage(), e);
        }
    }

    private List<? extends BotApiMethod<?>> handlePrivateMessage(Message message, TelegramBots bot) {
        TelegramStages stage;
        TelegramButtons button = tButtonsRepository.getTelegramButtonsByButtonText(message.getText());

        if (button == null || button.getCallbackData() == null) {
            stage = tStagesRepository.getFirstStage(bot.getId());
        } else {
            stage = button.getCallbackData();
        }

        return messageService.getMessages(stage, message, MessageTypes.SEND_MESSAGE);
    }

    private List<? extends BotApiMethod<?>> handleCallbackQuery(CallbackQuery callbackQuery, TelegramBots bot) {
        TelegramStages stage = tStagesRepository.getById(Long.parseLong(callbackQuery.getData()));

        return messageService.getMessages(stage, callbackQuery.getMessage(), MessageTypes.EDIT_MESSAGE);
    }

    private List<? extends BotApiMethod<?>> handleError(Message message, RuntimeException e) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setText(e.getMessage());

        return List.of(sendMessage);
    }
}
