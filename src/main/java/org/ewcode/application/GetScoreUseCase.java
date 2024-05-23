package org.ewcode.application;

import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.bson.Document;
import org.ewcode.infra.score.dto.ScoreDTO;

import java.time.ZoneId;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class GetScoreUseCase {

    public static final Logger logger = Logger.getLogger(GetScoreUseCase.class.getName());
    @Inject
    ReactiveMongoClient mongoClient;


    private ScoreDTO toDTO(Document doc) {
        return new ScoreDTO(
                doc.getInteger("playerId"),
                doc.getInteger("points"),
                doc.getInteger("assists"),
                doc.getInteger("rebounds"),
                doc.getDate("createdAt")
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime(),
                doc.getString("transactionId")
        );
    }

    public Uni<List<ScoreDTO>> execute() {
        logger.info("executing get score use case");
        return mongoClient.getDatabase("nbascoring")
                .getCollection("score")
                .find()
                .map(this::toDTO)
                .collect()
                .asList();
    }
}
