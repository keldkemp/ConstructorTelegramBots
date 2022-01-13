package keldkemp.telegram.models;

import javax.persistence.*;

@Entity
public class TelegramKeyboardRows {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_keyboard_rows_seq")
    private Long id;

    @Column(name = "keyboard_ord")
    private Long ord;

    @ManyToOne
    @JoinColumn(name = "keyboard_id", referencedColumnName = "id")
    private TelegramKeyboards telegramKeyboard;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrd() {
        return ord;
    }

    public void setOrd(Long ord) {
        this.ord = ord;
    }

    public TelegramKeyboards getTelegramKeyboard() {
        return telegramKeyboard;
    }

    public void setTelegramKeyboard(TelegramKeyboards telegramKeyboard) {
        this.telegramKeyboard = telegramKeyboard;
    }
}
