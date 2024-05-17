package org.ewcode.infra.score;

import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.ewcode.domain.Score;
import org.ewcode.infra.score.dto.ScoreDTO;
import org.ewcode.infra.score.mappers.ScoreMapper;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

@Path("score")
public class ScoreController {

    private static final Logger logger = Logger.getLogger(ScoreController.class.getName());
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ScoreController.class);
    private final MessageProducer messageProducer;
    @Channel("player-scored")
    Emitter<String> emitter;

    public ScoreController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    // @POST
    public Response produce(ScoreDTO dto) {
        logger.info("producing messages");
        int messageCount = 100;
        logger.info("generating " + messageCount + " messages");
        logger.info("dto = " + dto);
        return Response.ok("aaaaaa").build();
    }

    @GET
    public Response get() {
        logger.info("getting scores");
        return Response.ok().build();
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
}
