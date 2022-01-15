package keldkemp.telegram.services;

import keldkemp.telegram.models.TelegramBots;

import java.util.List;

public interface TelegramBotService {

    /**
     * Get List telegram bots for current user.
     * @return List
     */
    List<TelegramBots> getBots();

    /**
     * Get telegram bot by id.
     * @param id - telegram bot id
     * @return TelegramBots
     */
    TelegramBots getBot(Long id);

    /**
     * Save telegram bot by bot.
     * @param bot - telegram bot
     * @return Saved TelegramBots
     */
    TelegramBots save(TelegramBots bot);

    /**
     * Delete telegram bot by id.
     * @param id - telegram bot id
     */
    void delete(Long id);
}
