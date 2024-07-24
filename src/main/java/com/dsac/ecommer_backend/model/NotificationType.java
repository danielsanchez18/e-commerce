package com.dsac.ecommer_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "notification_type")
public class NotificationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotificationType;

    private String typeName;

    public NotificationType() { }

    public NotificationType(Long idNotificationType, String typeName) {
        this.idNotificationType = idNotificationType;
        this.typeName = typeName;
    }

    public Long getIdNotificationType() {
        return idNotificationType;
    }

    public void setIdNotificationType(Long idNotificationType) {
        this.idNotificationType = idNotificationType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
