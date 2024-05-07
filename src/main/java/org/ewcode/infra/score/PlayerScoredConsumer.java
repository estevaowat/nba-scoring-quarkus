package org.ewcode.infra.score;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.ewcode.application.PlayerScoredUseCase;

import java.util.logging.Logger;

@ApplicationScoped
public class PlayerScoredConsumer {

    private static final Logger logger = Logger.getLogger(PlayerScoredConsumer.class.getSimpleName());
    private final PlayerScoredUseCase playerScoredUseCase;

    public PlayerScoredConsumer(PlayerScoredUseCase playerScoredUseCase) {
        this.playerScoredUseCase = playerScoredUseCase;
    }

    @Incoming("player-scored")
    public void consume(String scoreDTO) throws JsonProcessingException {
        logger.info("received score: " + scoreDTO);
        logger.info("executing player score use case");
        playerScoredUseCase.execute(scoreDTO);
        logger.info("executed");
    }
}
