package Tests;

import Exceptions.MissingMatchException;
import Exceptions.UnsupportedScoreUpdateException;
import UseCases.ScoreBoardUseCases;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals("""
                Nicaragua 0 - Brazil 0
                Italy 0 - Argentina 0
                France 0 - Uruguay 0
                Spain 0 - Germany 0
                Mexico 0 - Canada 0
                """, sb.startNewMatch("Nicaragua", "Brazil"));
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
            assertEquals("""
                    Mexico 2 - Canada 1
                    France 1 - Uruguay 1
                    Italy 0 - Argentina 0
                    Spain 0 - Germany 0
                    """, sb.updateScore("Mexico - Canada", "2:1"));
            assertEquals("""
                    Mexico 2 - Canada 1
                    France 1 - Uruguay 1
                    Spain 0 - Germany 1
                    Italy 0 - Argentina 0
                    """, sb.updateScore("Spain - Germany", "0:1"));
            assertEquals("""
                    Mexico 2 - Canada 1
                    France 1 - Uruguay 1
                    Spain 1 - Germany 1
                    Italy 0 - Argentina 0
                    """, sb.updateScore("Spain - Germany", "1:1"));
            assertEquals("""
                    Spain 2 - Germany 1
                    Mexico 2 - Canada 1
                    France 1 - Uruguay 1
                    Italy 0 - Argentina 0
                    """, sb.updateScore("Spain - Germany", "2:1"));
            assertEquals("""
                    France 2 - Uruguay 1
                    Spain 2 - Germany 1
                    Mexico 2 - Canada 1
                    Italy 0 - Argentina 0
                    """, sb.updateScore("France - Uruguay", "2:1"));
        } catch (MissingMatchException e) {
            System.out.println(e.getMessage() + e.getMatchName());
        } catch (UnsupportedScoreUpdateException e) {
            System.out.println(e.getMessage() + e.getScore());
        }
    }

    @Test
    void testUpdateScoreWithStream() {
        try {
            assertEquals("""
                    France 0 - Uruguay 1
                    Italy 0 - Argentina 0
                    Spain 0 - Germany 0
                    Mexico 0 - Canada 0
                    """, sb.updateScoreWithStream("France - Uruguay", "0:1"));
            assertEquals("""
                    France 1 - Uruguay 1
                    Italy 0 - Argentina 0
                    Spain 0 - Germany 0
                    Mexico 0 - Canada 0
                    """, sb.updateScoreWithStream("France - Uruguay", "1:1"));
            assertEquals("""
                    France 1 - Uruguay 1
                    Mexico 1 - Canada 0
                    Italy 0 - Argentina 0
                    Spain 0 - Germany 0
                    """, sb.updateScoreWithStream("Mexico - Canada", "1:0"));
            assertEquals("""
                    France 1 - Uruguay 1
                    Mexico 2 - Canada 0
                    Italy 0 - Argentina 0
                    Spain 0 - Germany 0
                    """, sb.updateScoreWithStream("Mexico - Canada", "2:0"));
            assertEquals("""
                    Mexico 2 - Canada 1
                    France 1 - Uruguay 1
                    Italy 0 - Argentina 0
                    Spain 0 - Germany 0
                    """, sb.updateScoreWithStream("Mexico - Canada", "2:1"));
            assertEquals("""
                    Mexico 2 - Canada 1
                    France 1 - Uruguay 1
                    Spain 0 - Germany 1
                    Italy 0 - Argentina 0
                    """, sb.updateScoreWithStream("Spain - Germany", "0:1"));
            assertEquals("""
                    Mexico 2 - Canada 1
                    France 1 - Uruguay 1
                    Spain 1 - Germany 1
                    Italy 0 - Argentina 0
                    """, sb.updateScoreWithStream("Spain - Germany", "1:1"));
            assertEquals("""
                    Spain 2 - Germany 1
                    Mexico 2 - Canada 1
                    France 1 - Uruguay 1
                    Italy 0 - Argentina 0
                    """, sb.updateScoreWithStream("Spain - Germany", "2:1"));
            assertEquals("""
                    France 2 - Uruguay 1
                    Spain 2 - Germany 1
                    Mexico 2 - Canada 1
                    Italy 0 - Argentina 0
                    """, sb.updateScoreWithStream("France - Uruguay", "2:1"));
        } catch (MissingMatchException e) {
            System.out.println(e.getMessage() + e.getMatchName());
        } catch (UnsupportedScoreUpdateException e) {
            System.out.println(e.getMessage() + e.getScore());
        }
    }

    @Test
    void testUpdateScoreNonExistingMatchError() {
        try {
            sb.updateScore("Slovenia - Austria", "1:0");
        } catch (MissingMatchException e) {
            System.out.println(e.getMessage() + e.getMatchName());
        } catch (UnsupportedScoreUpdateException e) {
            System.out.println(e.getMessage() + e.getScore());
        }
    }

    @Test
    void testUpdateScoreWithStreamNonExistingMatchError() {
        try {
            sb.updateScoreWithStream("Slovenia - Austria", "1:0");
        } catch (MissingMatchException e) {
            System.out.println(e.getMessage() + e.getMatchName());
        } catch (UnsupportedScoreUpdateException e) {
            System.out.println(e.getMessage() + e.getScore());
        }
    }

    @Test
    void testUpdateScoreIncrementGreaterThanOneError() {
        try {
            sb.updateScore("Mexico - Canada", "2:0");
        } catch (MissingMatchException e) {
            System.out.println(e.getMessage() + e.getMatchName());
        } catch (UnsupportedScoreUpdateException e) {
            System.out.println(e.getMessage() + e.getScore());
        }
    }

    @Test
    void testUpdateScoreNegativeScoreError() {
        try {
            sb.updateScore("Mexico - Canada", "-1:0");
        } catch (MissingMatchException e) {
            System.out.println(e.getMessage() + e.getMatchName());
        } catch (UnsupportedScoreUpdateException e) {
            System.out.println(e.getMessage() + e.getScore());
        }
    }

    @Test
    void testUpdateScoreNoSemicolonError() {
        try {
            sb.updateScore("Mexico - Canada", "1");
        } catch (MissingMatchException e) {
            System.out.println(e.getMessage() + e.getMatchName());
        } catch (UnsupportedScoreUpdateException e) {
            System.out.println(e.getMessage() + e.getScore());
        }
    }

    @Test
    void testFinishMatch() {
        try {
            assertEquals("""
                    Italy 0 - Argentina 0
                    France 0 - Uruguay 0
                    Mexico 0 - Canada 0
                    """, sb.finishMatch("Spain - Germany"));
        } catch (MissingMatchException e) {
            System.out.println(e.getMessage() + e.getMatchName());
        }
    }

    @Test
    void testFinishMatchError() {
        try {
            sb.finishMatch("Blb");
        } catch (MissingMatchException e) {
            System.out.println(e.getMessage() + e.getMatchName());
        }
    }
}