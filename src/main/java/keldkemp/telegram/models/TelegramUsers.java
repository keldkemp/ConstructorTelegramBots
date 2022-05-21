package keldkemp.telegram.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TelegramUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_users_seq")
    private Long id;

    @Column(name = "user_id", unique = true)
    private Long tgUserId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "telegram_bot_id", referencedColumnName = "id")
    private TelegramBots telegramBot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTgUserId() {
        return tgUserId;
    }

    public void setTgUserId(Long tgUserId) {
        this.tgUserId = tgUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public TelegramBots getTelegramBot() {
        return telegramBot;
    }

    public void setTelegramBot(TelegramBots telegramBot) {
        this.telegramBot = telegramBot;
    }
}
