package org.ewcode.infra.score;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.MDC;

import java.util.List;
import java.util.logging.Logger;

@Path("score")
public class ScoreController {

    private static final Logger logger = Logger.getLogger(ScoreController.class.getName());
    private final MessageProducer messageProducer;
    @Channel("player-scored")
    Emitter<String> emitter;

    public ScoreController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @POST
    public void produce() {
        logger.info("producing messages");
        int messageCount = 100;
        List<String> messages = messageProducer.generateMessages(MDC.get("transactionId"), messageCount);
        logger.info("generated " + messages.size() + " messages");
        logger.info("emitting " + messages.size() + " messages");
        messages.forEach(message -> emitter.send(Message.of(message)));
    }
}
