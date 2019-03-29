package com.lavinia;

import com.lavinia.domain.Roll;
import com.lavinia.domain.Scorecard;

import java.util.ArrayList;

/**
 * Interface containing methods that should be implemented in every scoring system (see ScoringType).
 */
public interface ScoringSystem {

    Scorecard createScorecard();

    Scorecard addFrameAndUpdateScores(final Scorecard scorecard, final ArrayList<Roll> frameRolls);

}
