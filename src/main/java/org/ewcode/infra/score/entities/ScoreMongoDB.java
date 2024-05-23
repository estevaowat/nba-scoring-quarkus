package org.ewcode.infra.score.entities;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

import java.time.LocalDateTime;

@MongoEntity(collection = "score")
public class ScoreMongoDB extends ReactivePanacheMongoEntity {

    public int playerId;
    public int points;
    public int assists;
    public int rebounds;
    public LocalDateTime createdAt;
    public String transactionId;
}
