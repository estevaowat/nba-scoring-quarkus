package org.ewcode.infra.score.dto;

public record ScoreWithoutDateDTO(
        int playerId, int points, int assists, int rebounds,
        String transactionId
) {

}
