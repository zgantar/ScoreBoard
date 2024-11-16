package Tests;

import Exceptions.MissingMatchException;
import POJO.Match;
import UseCases.ScoreBoardUseCases;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ScoreBoardTest {
    ScoreBoardUseCases sb = new ScoreBoardUseCases();

    @BeforeEach
    void setUp() {
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
    void testGetSummary() {
        assertEquals("""
                Italy 0 - Argentina 0
                France 0 - Uruguay 0
                Spain 0 - Germany 0
                Mexico 0 - Canada 0
                """, sb.getSummary());
    }

    @Test
    void testStartNewMatch() {
        Match match = sb.startNewMatch("Nicaragua", "Brazil");
        assertNotNull(match);
        assertEquals("Nicaragua 0 - Brazil 0", match.toString());
    }

    @Test
    void testUpdateScore() {
        try {
            assertEquals("""
                    France 0 - Uruguay 1
                    Italy 0 - Argentina 0
                    Spain 0 - Germany 0
                    Mexico 0 - Canada 0
                    """, sb.updateScore("France - Uruguay", "0:1"));
            assertEquals("""
                    France 1 - Uruguay 1
                    Italy 0 - Argentina 0
                    Spain 0 - Germany 0
                    Mexico 0 - Canada 0
                    """, sb.updateScore("France - Uruguay", "1:1"));
            assertEquals("""
                    France 1 - Uruguay 1
                    Mexico 1 - Canada 0
                    Italy 0 - Argentina 0
                    Spain 0 - Germany 0
                    """, sb.updateScore("Mexico - Canada", "1:0"));
            assertEquals("""
                    France 1 - Uruguay 1
                    Mexico 2 - Canada 0
                    Italy 0 - Argentina 0
                    Spain 0 - Germany 0
                    """, sb.updateScore("Mexico - Canada", "2:0"));
        } catch (MissingMatchException e) {
            System.out.println(e.getMessage() + e.getMatchName());
        }
    }

    @Test
    void testUpdateScoreError() {
        try {
            sb.updateScore("Slovenia - Austria", "1:0");
        } catch (MissingMatchException e) {
            System.out.println(e.getMessage() + e.getMatchName());
        }
    }
    @Test
    void testStopMatch() {
        try {
            assertEquals("""
                    France 1 - Uruguay 1
                    Mexico 2 - Canada 0
                    Italy 0 - Argentina 0
                    Spain 0 - Germany 0
                    """, sb.stopMatch("Spain - Germany"));
        } catch (MissingMatchException e) {
            System.out.println(e.getMessage() + e.getMatchName());
        }
    }

}