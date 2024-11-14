package Tests;

import POJO.ScoreBoard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ScoreBoardTest {
    ScoreBoard sb;

    @BeforeEach
    void setUp() {
        sb = new ScoreBoard();
    }

    @AfterEach
    void tearDown() {
        sb = null;
    }

    @Test
    void testGetScoreBoard() {
        assertNotNull(sb.getScoreBoard());
    }

    @Test
    void testStartNewMatch() {
        Long matchId = sb.startNewMatch("Nicaragua", "Brazil");
        assertNotEquals(0L, matchId);
    }
}