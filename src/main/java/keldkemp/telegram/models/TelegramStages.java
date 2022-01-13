package keldkemp.telegram.models;

import javax.persistence.*;

@Entity
public class TelegramStages {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_stages_seq")
    private Long id;

    private String name;

    @Column(name = "previous_stage")
    private Long previousStage;

    @ManyToOne
    @JoinColumn(name = "telegram_bot_id", referencedColumnName = "id")
    private TelegramBots telegramBot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPreviousStage() {
        return previousStage;
    }

    public void setPreviousStage(Long previousStage) {
        this.previousStage = previousStage;
    }

    public TelegramBots getTelegramBot() {
        return telegramBot;
    }

    public void setTelegramBot(TelegramBots telegramBot) {
        this.telegramBot = telegramBot;
    }
}
