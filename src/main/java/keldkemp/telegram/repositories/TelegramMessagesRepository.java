package keldkemp.telegram.repositories;

import keldkemp.telegram.models.TelegramMessages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramMessagesRepository extends JpaRepository<TelegramMessages, Long> {

    TelegramMessages getTelegramMessagesByTelegramStageId(Long stageId);
}
