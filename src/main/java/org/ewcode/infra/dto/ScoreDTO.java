package org.ewcode.infra.dto;

import java.time.LocalDateTime;

public record ScoreDTO(int playerId, int points, int assists, int rebounds, LocalDateTime timestamp) {

}
