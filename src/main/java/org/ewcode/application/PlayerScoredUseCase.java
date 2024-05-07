package org.ewcode.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.ewcode.domain.Score;
import org.ewcode.infra.score.PlayerScoredConsumer;
import org.ewcode.infra.score.dto.ScoreDTO;
import org.ewcode.infra.score.mappers.ScoreMapper;

import java.util.function.Function;
import java.util.logging.Logger;

@ApplicationScoped
public class PlayerScoredUseCase {

    private static final Logger logger = Logger.getLogger(PlayerScoredConsumer.class.getSimpleName());
    private final ObjectMapper objectMapper;

    public PlayerScoredUseCase(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void execute(String message) throws JsonProcessingException {
        logger.info("converting score dto to entity");
        Uni.createFrom().item(message)
                .onItem()
                .transform(toEntity(message))
                .invoke(entity -> Panache.withTransaction(entity::persist))
                .subscribe()
                .with(entity -> logger.info("persisted score: " + entity),
                        throwable -> logger.info("failed to persist score: " + throwable.getMessage()));
    }

    private Function<String, Score> toEntity(String message) {
        return t -> {
            try {
                ScoreDTO scoreDTO = objectMapper.readValue(message, ScoreDTO.class);
                logger.info(scoreDTO.toString());
                return ScoreMapper.toEntity(scoreDTO);
            } catch(JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
