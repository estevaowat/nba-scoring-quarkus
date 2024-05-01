package org.ewcode.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.ewcode.infra.dto.ScoreDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.IntStream;

@Path("player")
public class Controller {

    @Channel("player-scored")
    Emitter<String> emitter;
    private static final Logger logger = Logger.getLogger(Controller.class.getName());
    private final ObjectMapper objectMapper;

    public Controller(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @POST
    public void produce() {
        logger.info("producing messages");
        List<String> messages = generateMessages();
        logger.info("generated " + messages.size() + " messages");
        logger.info("emitting " + messages.size() + " messages");
        messages.forEach(message -> emitter.send(Message.of(message)));
    }

    @GET
    public String get() {
        return "ok";
    }

    private List<String> generateMessages() {
        return IntStream.range(0, 1000)
                .mapToObj(i -> {
                    try {
                        return objectMapper.writeValueAsString(new ScoreDTO(1, 2, 1, 0, LocalDateTime.now()));
                    } catch(JsonProcessingException ignored) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }

    @Incoming("player-scored")
    public void process(String message) throws JsonProcessingException {
        var score = objectMapper.readValue(message, ScoreDTO.class);
        logger.info("received score: " + score);

    }
}
