package org.ewcode.infra.score.dto;

import java.time.LocalDateTime;

public record ScoreDTO(int playerId, int points, int assists, int rebounds, LocalDateTime timestamp,
                       String transactionId) {

}
