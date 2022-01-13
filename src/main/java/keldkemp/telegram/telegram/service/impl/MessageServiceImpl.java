package keldkemp.telegram.telegram.service.impl;

import keldkemp.telegram.models.TelegramMessages;
import keldkemp.telegram.models.TelegramStages;
import keldkemp.telegram.repositories.TelegramMessagesRepository;
import keldkemp.telegram.telegram.domain.MessageTypes;
import keldkemp.telegram.telegram.service.KeyboardService;
import keldkemp.telegram.telegram.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private TelegramMessagesRepository tMessagesRepository;

    @Autowired
    private KeyboardService keyboardService;

    @Override
    public List<? extends BotApiMethod<?>> getMessages(TelegramStages stage, Message tMessage, MessageTypes type) {
        TelegramMessages message = tMessagesRepository.getTelegramMessagesByTelegramStageId(stage.getId());

        if (type == MessageTypes.EDIT_MESSAGE) {
            EditMessageText editMessageText = new EditMessageText(message.getMessageText());
            editMessageText.setChatId(tMessage.getChatId().toString());
            editMessageText.setMessageId(tMessage.getMessageId());
            editMessageText.setReplyMarkup(keyboardService.getKeyboard(stage));

            return List.of(editMessageText);
        } else if (type == MessageTypes.SEND_MESSAGE) {
            SendMessage sendMessage = new SendMessage(tMessage.getChatId().toString(), message.getMessageText());
            sendMessage.setReplyMarkup(keyboardService.getKeyboard(stage));

            return List.of(sendMessage);
        }
        return null;
    }
}
