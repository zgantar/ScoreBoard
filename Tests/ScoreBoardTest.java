package Tests;

import POJO.Match;
import POJO.ScoreBoard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ScoreBoardTest {
    ScoreBoard sb;

    @BeforeEach
    void setUp() {
        sb = new ScoreBoard();
        sb.startNewMatch("Mexico", "Canada");
        sb.startNewMatch("Spain", "Germany");
        sb.startNewMatch("France", "Uruguay");
        sb.startNewMatch("Italy", "Argentina");
    }

    @AfterEach
    void tearDown() {
        sb = null;
    }

    @Test
    void testGetScoreBoard() {
        assertEquals("""
                Mexico 0 - Canada 0
                Spain 0 - Germany 0
                France 0 - Uruguay 0
                Italy 0 - Argentina 0
                """, sb.getScoreBoard());
    }

    @Test
    void testStartNewMatch() {
        Match match = sb.startNewMatch("Nicaragua", "Brazil");
        assertNotNull(match);
        assertEquals("Nicaragua 0 - Brazil 0", match.toString());
    }

    @Test
    void testUpdateScore() {
        assertEquals("""
                France 0 - Uruguay 1
                Mexico 0 - Canada 0
                Spain 0 - Germany 0
                Italy 0 - Argentina 0
                """, sb.updateScore("France - Uruguay", "0:1"));
    }

}