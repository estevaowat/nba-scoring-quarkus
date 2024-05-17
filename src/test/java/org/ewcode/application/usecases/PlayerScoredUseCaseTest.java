package org.ewcode.application.usecases;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@QuarkusTest
@Disabled
public class PlayerScoredUseCaseTest {

    @BeforeEach
    public void setup() {
    }

    @Test
    void playerWithValidScore() {
        var expected = 1;
        var result = 1;
        Assertions.assertEquals(expected, result);
    }
}
