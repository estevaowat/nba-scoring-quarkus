package org.ewcode.infra.score.mappers;

import org.ewcode.domain.Score;
import org.ewcode.infra.score.dto.ScoreDTO;
import org.ewcode.infra.score.entities.ScoreMongoDB;

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

    public static ScoreMongoDB toEntityMongoDB(ScoreDTO scoreDTO) {
        ScoreMongoDB score = new ScoreMongoDB();
        score.playerId = scoreDTO.playerId();
        score.points = scoreDTO.points();
        score.assists = scoreDTO.assists();
        score.rebounds = scoreDTO.rebounds();
        score.createdAt = scoreDTO.timestamp();
        score.transactionId = scoreDTO.transactionId();
        return score;
    }
}
