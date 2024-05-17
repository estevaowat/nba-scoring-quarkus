package org.ewcode.domain;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class Score extends PanacheEntity {

    public int playerId;
    public int points;
    public int assists;
    public int rebounds;
    public LocalDateTime createdAt;
    public String transactionId;

    public boolean isPointValid() {
        return points >= 0 && points <= 4;
    }
}

