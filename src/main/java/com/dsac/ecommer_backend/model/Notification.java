package com.dsac.ecommer_backend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idNotification;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    private String content;
    private LocalDateTime notificationDate;
    private boolean read;

    public Notification() { }

    public Notification(UUID idNotification, User user, String content, LocalDateTime notificationDate, boolean read) {
        this.idNotification = idNotification;
        this.user = user;
        this.content = content;
        this.notificationDate = notificationDate;
        this.read = read;
    }

    public UUID getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(UUID idNotification) {
        this.idNotification = idNotification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
