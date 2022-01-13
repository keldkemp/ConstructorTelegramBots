package keldkemp.telegram.repositories;

import keldkemp.telegram.models.TelegramKeyboards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramKeyboardsRepository extends JpaRepository<TelegramKeyboards, Long> {

    TelegramKeyboards getTelegramKeyboardsByTelegramStageId(Long stageId);
}
