package keldkemp.telegram.repositories;

import keldkemp.telegram.models.TelegramButtons;
import keldkemp.telegram.models.TelegramKeyboardRows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TelegramButtonsRepository extends JpaRepository<TelegramButtons, Long> {

    List<TelegramButtons> getTelegramButtonsByTelegramKeyboardRowIn(Collection<TelegramKeyboardRows> telegramKeyboardRow);

    List<TelegramButtons> getTelegramButtonsByTelegramKeyboardRowOrderByButtonOrd(TelegramKeyboardRows telegramKeyboardRow);

    TelegramButtons getTelegramButtonsByButtonText(String buttonText);
}
