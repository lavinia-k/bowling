package com.lavinia;

import com.lavinia.domain.Roll;
import com.lavinia.domain.Scorecard;

import java.util.ArrayList;

public interface ScoringSystem {

    Scorecard createScorecard();

    Scorecard addFrameAndUpdateScores(final Scorecard scorecard, final ArrayList<Roll> frameRolls);

//    boolean isGameComplete(final Scorecard scorecard);


}
