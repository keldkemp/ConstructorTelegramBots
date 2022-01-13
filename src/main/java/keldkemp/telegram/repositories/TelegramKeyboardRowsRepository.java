package keldkemp.telegram.repositories;

import keldkemp.telegram.models.TelegramKeyboardRows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TelegramKeyboardRowsRepository extends JpaRepository<TelegramKeyboardRows, Long> {

    List<TelegramKeyboardRows> getTelegramKeyboardRowsByTelegramKeyboardIdOrderByOrd(Long keyboardId);
}
