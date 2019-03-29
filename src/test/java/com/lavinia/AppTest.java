package com.lavinia;

import com.lavinia.domain.Scorecard;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private final App bowlingScorer;

    public AppTest() {
        bowlingScorer = new App();
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
     * Add first frame and check that score is updated appropriately
     */
    @Test
    public void oneFrameNormal() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 4, 4);

        assertTrue(scorecard.playedFrames.size() == 1);
        assertTrue(scorecard.playedFrames.get(0).score == 8);
    }

    /**
     * Add first frame and check that score is updated appropriately
     */
    @Test
    public void checkGameIsNotComplete() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 4, 4);

        assertTrue(scorecard.playedFrames.size() == 1);
        assertTrue(scorecard.playedFrames.get(0).score == 8);

        boolean complete = bowlingScorer.isGameComplete(scorecard);

        assertFalse(complete);
    }

    /**
     * Add first frame and check that score is updated appropriately
     */
    @Test
    public void gameWithStrike() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 10);

        assertTrue(scorecard.playedFrames.size() == 1);
        assertTrue(scorecard.playedFrames.get(0).score == 10);

        bowlingScorer.recordFrame(scorecard, 4, 3);

        assertTrue(scorecard.playedFrames.size() == 2);
        assertTrue(scorecard.playedFrames.get(1).score == 7);
        assertTrue(scorecard.playedFrames.get(0).score == 17);
    }

    @Test
    public void gameWithStrikeAndTwoFramesAfter() {
        Scorecard scorecard = bowlingScorer.createScorecard();

        bowlingScorer.recordFrame(scorecard, 10);

        assertTrue(scorecard.playedFrames.size() == 1);
        assertTrue(scorecard.playedFrames.get(0).score == 10);

        bowlingScorer.recordFrame(scorecard, 4, 3);

        assertTrue(scorecard.playedFrames.size() == 2);
        assertTrue(scorecard.playedFrames.get(1).score == 7);

        assertTrue(scorecard.playedFrames.get(0).score == 17);

        bowlingScorer.recordFrame(scorecard, 2, 3);

        assertTrue(scorecard.playedFrames.size() == 3);
        assertTrue(scorecard.playedFrames.get(2).score == 5);
        assertTrue(scorecard.playedFrames.get(1).score == 7);
        assertTrue(scorecard.playedFrames.get(0).score == 17);
    }


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
