package com.lavinia;

import com.lavinia.domain.Scorecard;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for a simple BowlingScorer.
 *
 * Known issues:
 *      - Each test covers many different methods (can be hard to isolate issues in the case of test failures)
 *
 */
public class BowlingScorerTest {

    private final BowlingScorer bowlingScorer;

    public BowlingScorerTest() {
        bowlingScorer = new BowlingScorer();
    }

    /**
     * Check scorecard starting size is 0
     */
    @Test
    public void createNewScorecard() {
        Scorecard scorecard = bowlingScorer.createScorecard();
        assertTrue(scorecard.playedFrames.size() == 0);
    }

    /**
     * Play first frame and check that score is updated appropriately
     */
    @Test
    public void checkScoringOneNormalFrame() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 4, 4);

        assertTrue(scorecard.playedFrames.size() == 1);
        assertTrue(scorecard.playedFrames.get(0).score == 8);
        assertTrue(bowlingScorer.getTotalScore(scorecard) == 8);
    }

    /**
     * Play three frames and check that score is updated appropriately
     */
    @Test
    public void checkScoringThreeNormalFrames() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 4, 4);
        bowlingScorer.recordFrame(scorecard, 3, 6);
        bowlingScorer.recordFrame(scorecard, 0, 2);

        assertTrue(scorecard.playedFrames.size() == 3);
        assertTrue(scorecard.playedFrames.get(0).score == 8);
        assertTrue(scorecard.playedFrames.get(1).score == 9);
        assertTrue(scorecard.playedFrames.get(2).score == 2);
        assertTrue(bowlingScorer.getTotalScore(scorecard) == 19);
    }

    /**
     * Check that game is not complete after playing one frame
     */
    @Test
    public void checkGameIsNotCompleteWithOneFrame() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 4, 4);

        assertTrue(scorecard.playedFrames.size() == 1);
        assertTrue(scorecard.playedFrames.get(0).score == 8);
        assertFalse(bowlingScorer.isGameComplete(scorecard));
        assertTrue(bowlingScorer.getTotalScore(scorecard) > 0);
        assertTrue(bowlingScorer.getTotalScore(scorecard) < 300);
    }

    /**
     * Check that game is not complete after playing nine frames
     */
    @Test
    public void checkGameIsNotCompleteWithNineFrames() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 0, 0);
        bowlingScorer.recordFrame(scorecard, 1, 9);
        bowlingScorer.recordFrame(scorecard, 2, 0);
        bowlingScorer.recordFrame(scorecard, 3, 6);
        bowlingScorer.recordFrame(scorecard, 4, 1);
        bowlingScorer.recordFrame(scorecard, 5, 2);
        bowlingScorer.recordFrame(scorecard, 6, 1);
        bowlingScorer.recordFrame(scorecard, 7, 3);
        bowlingScorer.recordFrame(scorecard, 8, 1);

        assertTrue(scorecard.playedFrames.size() == 9);
        assertFalse(bowlingScorer.isGameComplete(scorecard));
        assertTrue(bowlingScorer.getTotalScore(scorecard) > 0);
        assertTrue(bowlingScorer.getTotalScore(scorecard) < 300);
    }

    /**
     * Check that game is complete after playing ten frames
     */
    @Test
    public void checkGameIsCompleteWithTenFrames() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 0, 0);
        bowlingScorer.recordFrame(scorecard, 1, 9);
        bowlingScorer.recordFrame(scorecard, 2, 0);
        bowlingScorer.recordFrame(scorecard, 3, 6);
        bowlingScorer.recordFrame(scorecard, 4, 1);
        bowlingScorer.recordFrame(scorecard, 5, 2);
        bowlingScorer.recordFrame(scorecard, 6, 1);
        bowlingScorer.recordFrame(scorecard, 7, 3);
        bowlingScorer.recordFrame(scorecard, 8, 1);
        bowlingScorer.recordFrame(scorecard, 2, 1);

        assertTrue(scorecard.playedFrames.size() == 10);
        assertTrue(bowlingScorer.isGameComplete(scorecard));
        assertTrue(bowlingScorer.getTotalScore(scorecard) > 0);
        assertTrue(bowlingScorer.getTotalScore(scorecard) < 300);
    }

    /**
     * Check that last frame accepts 3 rolls when the first roll is a strike
     */
    @Test
    public void checkLastFrameTakesThreeRollsWhenFirstRollIsAStrike() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 0, 0);
        bowlingScorer.recordFrame(scorecard, 1, 9);
        bowlingScorer.recordFrame(scorecard, 2, 0);
        bowlingScorer.recordFrame(scorecard, 3, 6);
        bowlingScorer.recordFrame(scorecard, 4, 1);
        bowlingScorer.recordFrame(scorecard, 5, 2);
        bowlingScorer.recordFrame(scorecard, 6, 1);
        bowlingScorer.recordFrame(scorecard, 7, 3);
        bowlingScorer.recordFrame(scorecard, 8, 1);
        bowlingScorer.recordFrame(scorecard, 10, 2, 4);

        assertTrue(scorecard.playedFrames.size() == 10);
        assertTrue(bowlingScorer.isGameComplete(scorecard));
        assertTrue(bowlingScorer.getTotalScore(scorecard) > 0);
        assertTrue(bowlingScorer.getTotalScore(scorecard) < 300);
    }

    /**
     * Check that last frame accepts 3 rolls when the second roll is a spare
     */
    @Test
    public void checkLastFrameTakesThreeRollsWhenSecondRollIsASpare() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 0, 0);
        bowlingScorer.recordFrame(scorecard, 1, 9);
        bowlingScorer.recordFrame(scorecard, 2, 0);
        bowlingScorer.recordFrame(scorecard, 3, 6);
        bowlingScorer.recordFrame(scorecard, 4, 1);
        bowlingScorer.recordFrame(scorecard, 5, 2);
        bowlingScorer.recordFrame(scorecard, 6, 1);
        bowlingScorer.recordFrame(scorecard, 7, 3);
        bowlingScorer.recordFrame(scorecard, 8, 1);
        bowlingScorer.recordFrame(scorecard, 2, 8, 4);

        assertTrue(scorecard.playedFrames.size() == 10);
        assertTrue(bowlingScorer.isGameComplete(scorecard));
        assertTrue(bowlingScorer.getTotalScore(scorecard) > 0);
        assertTrue(bowlingScorer.getTotalScore(scorecard) < 300);
    }

    /**
     * Check that last frame accepts 2 rolls when the first roll is not a strike or a spare
     */
    @Test
    public void checkLastFrameTakesTwoRolls() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 0, 0);
        bowlingScorer.recordFrame(scorecard, 1, 9);
        bowlingScorer.recordFrame(scorecard, 2, 0);
        bowlingScorer.recordFrame(scorecard, 3, 6);
        bowlingScorer.recordFrame(scorecard, 4, 1);
        bowlingScorer.recordFrame(scorecard, 5, 2);
        bowlingScorer.recordFrame(scorecard, 6, 1);
        bowlingScorer.recordFrame(scorecard, 7, 3);
        bowlingScorer.recordFrame(scorecard, 8, 1);
        bowlingScorer.recordFrame(scorecard, 8, 1);

        assertTrue(scorecard.playedFrames.size() == 10);
        assertTrue(bowlingScorer.isGameComplete(scorecard));
        assertTrue(bowlingScorer.getTotalScore(scorecard) > 0);
        assertTrue(bowlingScorer.getTotalScore(scorecard) < 300);
    }

    /**
     * Check that scoring a strike works with a simple case (two frames)
     */
    @Test
    public void checkScoringStrikeSimple() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 10);

        assertTrue(scorecard.playedFrames.size() == 1);
        assertTrue(scorecard.playedFrames.get(0).score == 10);
        assertTrue(scorecard.getTotalScore() == 10);

        bowlingScorer.recordFrame(scorecard, 4, 3);

        assertTrue(scorecard.playedFrames.size() == 2);
        assertTrue(scorecard.playedFrames.get(1).score == 7);
        assertTrue(scorecard.playedFrames.get(0).score == 17);
        assertTrue(scorecard.getTotalScore() == 24);
    }

    /**
     * Check that scoring a strike works with a three frames
     */
    @Test
    public void checkScoringStrikeWithThreeFrames() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 10);

        assertTrue(scorecard.playedFrames.size() == 1);
        assertTrue(scorecard.playedFrames.get(0).score == 10);

        bowlingScorer.recordFrame(scorecard, 4, 3);

        assertTrue(scorecard.playedFrames.size() == 2);
        assertTrue(scorecard.playedFrames.get(0).score == 17);
        assertTrue(scorecard.playedFrames.get(1).score == 7);
        assertTrue(scorecard.getTotalScore() == 24);

        bowlingScorer.recordFrame(scorecard, 2, 3);

        assertTrue(scorecard.playedFrames.size() == 3);
        assertTrue(scorecard.playedFrames.get(0).score == 17);
        assertTrue(scorecard.playedFrames.get(1).score == 7);
        assertTrue(scorecard.playedFrames.get(2).score == 5);
        assertTrue(scorecard.getTotalScore() == 29);
    }

    /**
     * Check that scoring a spare works with a simple case (two frames)
     */
    @Test
    public void checkScoringSpareSimple() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 2, 8);

        assertTrue(scorecard.playedFrames.size() == 1);
        assertTrue(scorecard.playedFrames.get(0).score == 10);
        assertTrue(scorecard.getTotalScore() == 10);

        bowlingScorer.recordFrame(scorecard, 4, 3);

        assertTrue(scorecard.playedFrames.size() == 2);
        assertTrue(scorecard.playedFrames.get(0).score == 14);
        assertTrue(scorecard.playedFrames.get(1).score == 7);
        assertTrue(scorecard.getTotalScore() == 21);
    }

    /**
     * Check that scoring a spare works with four frames
     */
    @Test
    public void checkScoringSpareWithFourFrames() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 2, 8);

        assertTrue(scorecard.playedFrames.size() == 1);
        assertTrue(scorecard.playedFrames.get(0).score == 10);
        assertTrue(scorecard.getTotalScore() == 10);

        bowlingScorer.recordFrame(scorecard, 4, 6);

        assertTrue(scorecard.playedFrames.size() == 2);
        assertTrue(scorecard.playedFrames.get(0).score == 14);
        assertTrue(scorecard.playedFrames.get(1).score == 10);
        assertTrue(scorecard.getTotalScore() == 24);

        bowlingScorer.recordFrame(scorecard, 5, 0);

        assertTrue(scorecard.playedFrames.size() == 3);
        assertTrue(scorecard.playedFrames.get(0).score == 14);
        assertTrue(scorecard.playedFrames.get(1).score == 15);
        assertTrue(scorecard.playedFrames.get(2).score == 5);
        assertTrue(scorecard.getTotalScore() == 34);

        bowlingScorer.recordFrame(scorecard, 1, 2);

        assertTrue(scorecard.playedFrames.size() == 4);
        assertTrue(scorecard.playedFrames.get(0).score == 14);
        System.out.println(scorecard.playedFrames.get(1).score);
        System.out.println(scorecard.playedFrames.get(1).rolls.get(0).pins);
        System.out.println(scorecard.playedFrames.get(1).rolls.get(1).pins);
        assertTrue(scorecard.playedFrames.get(1).score == 15);
        assertTrue(scorecard.playedFrames.get(2).score == 5);
        assertTrue(scorecard.playedFrames.get(3).score == 3);

        assertTrue(scorecard.getTotalScore() == 37);
    }

    /**
     * Check that scoring a game with a perfect score works
     */
    @Test
    public void gameWithPerfectScore() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 10);
        bowlingScorer.recordFrame(scorecard, 10);
        bowlingScorer.recordFrame(scorecard, 10);
        bowlingScorer.recordFrame(scorecard, 10);
        bowlingScorer.recordFrame(scorecard, 10);

        bowlingScorer.recordFrame(scorecard, 10);
        bowlingScorer.recordFrame(scorecard, 10);
        bowlingScorer.recordFrame(scorecard, 10);
        bowlingScorer.recordFrame(scorecard, 10);
        bowlingScorer.recordFrame(scorecard, 10, 10, 10);

        assertTrue(scorecard.playedFrames.size() == 10);
        assertTrue(scorecard.playedFrames.get(1).score == 30);
        assertTrue(scorecard.getTotalScore() == 300);
    }
}
