package com.lavinia;

import com.lavinia.domain.Roll;
import com.lavinia.domain.Scorecard;

import java.util.ArrayList;

/**
 * BowlingScorer
 *
 */
public class App {

    ScoringSystem scoringSystem = new TraditionalScoringSystem();

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public Scorecard createScorecard() {
        return scoringSystem.createScorecard();
    }

    public Scorecard recordFrame(final Scorecard scorecard, final ArrayList<Roll> rolls) {

        if (isGameComplete(scorecard)) {
            return scorecard; // Exit
        }
        scoringSystem.addFrameAndUpdateScores(scorecard, rolls);
        return scorecard;
    }

    public Scorecard recordFrame(final Scorecard scorecard, final Integer resultOfFirstBall,
                                 final Integer resultOfSecondBall) {
        ArrayList<Roll> rolls = new ArrayList<>();
        rolls.add(new Roll(resultOfFirstBall));
        rolls.add(new Roll(resultOfSecondBall));

        return this.recordFrame(scorecard, rolls);
    }

    public Scorecard recordFrame(final Scorecard scorecard, final Integer resultOfFirstBall,
                                 final Integer resultOfSecondBall, final Integer resultOfThirdBall) {
        ArrayList<Roll> rolls = new ArrayList<>();
        rolls.add(new Roll(resultOfFirstBall));
        rolls.add(new Roll(resultOfSecondBall));
        rolls.add(new Roll(resultOfThirdBall));

        return this.recordFrame(scorecard, rolls);
    }

    public Scorecard recordFrame(final Scorecard scorecard, final Integer resultOfFirstBall) {
        ArrayList<Roll> rolls = new ArrayList<>();
        rolls.add(new Roll(resultOfFirstBall));

        return this.recordFrame(scorecard, rolls);
    }

    public boolean isGameComplete(final Scorecard scorecard) {
        return scorecard.isScoreComplete();
    }

}
