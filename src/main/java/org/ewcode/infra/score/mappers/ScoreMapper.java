package org.ewcode.infra.score.mappers;

import org.ewcode.domain.Score;
import org.ewcode.infra.score.dto.ScoreDTO;

public class ScoreMapper {

    public static Score toEntity(ScoreDTO scoreDTO) {
        Score score = new Score();
        score.playerId = scoreDTO.playerId();
        score.points = scoreDTO.points();
        score.assists = scoreDTO.assists();
        score.rebounds = scoreDTO.rebounds();
        score.createdAt = scoreDTO.timestamp();
        score.transactionId = scoreDTO.transactionId();
        return score;
    }
}
