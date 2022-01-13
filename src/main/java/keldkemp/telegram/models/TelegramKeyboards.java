package keldkemp.telegram.models;

import javax.persistence.*;

@Entity
public class TelegramKeyboards {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_keyboards_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "keyboard_type_id", referencedColumnName = "id")
    private TelegramKeyboardTypes telegramKeyboardType;

    @ManyToOne
    @JoinColumn(name = "stage_id", referencedColumnName = "id")
    private TelegramStages telegramStage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TelegramKeyboardTypes getTelegramKeyboardType() {
        return telegramKeyboardType;
    }

    public void setTelegramKeyboardType(TelegramKeyboardTypes telegramKeyboardType) {
        this.telegramKeyboardType = telegramKeyboardType;
    }

    public TelegramStages getTelegramStage() {
        return telegramStage;
    }

    public void setTelegramStage(TelegramStages telegramStage) {
        this.telegramStage = telegramStage;
    }
}
