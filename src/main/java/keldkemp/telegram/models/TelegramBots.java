package keldkemp.telegram.models;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
public class TelegramBots {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_bots_seq")
    private Long id;

    @Column(name = "bot_name")
    private String botName;

    @Column(name = "bot_token")
    private String botToken;

    @Column(name = "is_active")
    @ColumnDefault("true")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
