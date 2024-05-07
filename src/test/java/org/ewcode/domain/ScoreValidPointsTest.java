package org.ewcode.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreValidPointsTest {

    @Test
    @DisplayName("when is valid score should return true")
    void isValidScore() {
        Score score = new Score();
        score.points = 1;
        Assertions.assertTrue(score.isPointValid());
    }

    @Test
    @DisplayName("when is invalid score should return false")
    void isInvalidScore() {
        Score score = new Score();
        score.points = 5;
        Assertions.assertFalse(score.isPointValid());
    }
}
