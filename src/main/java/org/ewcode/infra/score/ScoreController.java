package org.ewcode.infra.score;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.ewcode.application.GetScoreUseCase;
import org.ewcode.application.PlayerScoredUseCase;
import org.ewcode.domain.Score;
import org.ewcode.infra.score.dto.ScoreDTO;
import org.ewcode.infra.score.mappers.ScoreMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Path("score")
public class ScoreController {

    private static final Logger logger = Logger.getLogger(ScoreController.class.getName());
    private final PlayerScoredUseCase playerScoredUseCase;
    private final GetScoreUseCase getScoreUseCase;


    public ScoreController(PlayerScoredUseCase playerScoredUseCase, GetScoreUseCase getScoreUseCase) {
        this.playerScoredUseCase = playerScoredUseCase;
        this.getScoreUseCase = getScoreUseCase;
    }

    // @POST
    public Response produce(ScoreDTO dto) {
        logger.info("producing messages");
        int messageCount = 100;
        logger.info("generating " + messageCount + " messages");
        logger.info("dto = " + dto);
        return Response.ok("aaaaaa").build();
    }


    @POST
    @Produces("application/json")
    @WithTransaction
    public Uni<Response> post(ScoreDTO dto) {
        Score score = ScoreMapper.toEntity(dto);
        logger.info("persisting score: " + dto);
        logger.info("score = " + score);
        return score.persist()
                .replaceWith(Response.ok(dto).status(Response.Status.CREATED)::build);

    }

    @POST
    @Path("mongo")
    public Uni<Response> post() {
        ScoreDTO scoreDTO = new ScoreDTO(1, 2, 0, 0, LocalDateTime.now(), "");
        logger.info("executing player scored use case");
        return playerScoredUseCase.execute(scoreDTO);
    }

    @GET
    @Path("mongo")
    public Uni<List<ScoreDTO>> get() {
        logger.info("getting scores ");
        return getScoreUseCase.execute();
    }
}
