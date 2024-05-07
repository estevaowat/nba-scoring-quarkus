package org.ewcode.application.usecases;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.ewcode.ObjectMapperConfig;
import org.ewcode.application.PlayerScoredUseCase;
import org.ewcode.infra.score.dto.ScoreDTO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.UUID;

@QuarkusTest
@Disabled
public class PlayerScoredUseCaseTest {

    @InjectMock
    Session session;

    @BeforeEach
    public void setup() {
        Query mockQuery = Mockito.mock(Query.class);
        Mockito.doNothing().when(session).persist(Mockito.any());
        Mockito.when(session.createQuery(Mockito.anyString())).thenReturn(mockQuery);
        Mockito.when(mockQuery.getSingleResult()).thenReturn(0l);
    }

    @Test
    void playerWithValidScore() throws JsonProcessingException {

        PlayerScoredUseCase usecase = new PlayerScoredUseCase(new ObjectMapperConfig().getObjectMapper());
        int validPoints = 2;
        String scoreDTO = new ScoreDTO(1, validPoints, 1, 2, LocalDateTime.now(), UUID.randomUUID().toString()).toString();
        usecase.execute(scoreDTO);
        Mockito.verify(session, Mockito.times(1)).persist(Mockito.any());
    }
}
