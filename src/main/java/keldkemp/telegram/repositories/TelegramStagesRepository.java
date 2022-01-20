package keldkemp.telegram.repositories;

import keldkemp.telegram.models.TelegramBots;
import keldkemp.telegram.models.TelegramStages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface TelegramStagesRepository extends JpaRepository<TelegramStages, Long> {

    @Query("select stage from TelegramStages stage where stage.telegramBot.id = ?1 and stage.previousStage is null")
    TelegramStages getFirstStage(Long botId);

    void deleteAllByTelegramBot(TelegramBots telegramBot);

    void deleteAllByIdNotInAndTelegramBot(Collection<Long> id, TelegramBots bot);

    List<TelegramStages> getTelegramStagesByTelegramBot(TelegramBots telegramBot);
}
