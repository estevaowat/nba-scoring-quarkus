package org.ewcode.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.ewcode.domain.Score;
import org.ewcode.infra.score.PlayerScoredConsumer;
import org.ewcode.infra.score.dto.ScoreDTO;
import org.ewcode.infra.score.entities.ScoreMongoDB;
import org.ewcode.infra.score.mappers.ScoreMapper;

import java.net.URI;
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
    public void execute(String message) {
        logger.info("converting score dto to entity " + message);
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

    public Uni<Response> execute(ScoreDTO scoreDTO) {
        ScoreMongoDB score = ScoreMapper.toEntityMongoDB(scoreDTO);
        return Panache.withTransaction(() -> score.persist()
                .map(v -> {
                    //the ID is populated before sending it to the database
                    String id = score.id.toString();
                    return Response.created(URI.create("/reactive-transaction/" + id)).build();
                }));
    }
}
