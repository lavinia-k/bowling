package com.lavinia.domain;

import java.util.ArrayList;

import static com.lavinia.domain.ScoringType.TRADITIONAL;

public class Game {

    public final Integer numberOfPlayers;
    public final ScoringType scoringType;
    public final ArrayList<Scorecard> scorecards;

    public Game(final Integer numberOfPlayers) {
        this(numberOfPlayers, TRADITIONAL, new ArrayList<Scorecard>());
    }

    public Game(final ArrayList<Scorecard> scorecards) {
        this(scorecards.size(), TRADITIONAL, scorecards);
    }

    public Game(final Integer numberOfPlayers, final ScoringType scoringType) {
        this(numberOfPlayers, scoringType, new ArrayList<Scorecard>());
    }

    public Game(final Integer numberOfPlayers, final ScoringType scoringType, final ArrayList<Scorecard> scorecards) {
        this.numberOfPlayers = numberOfPlayers;
        this.scoringType = scoringType;
        this.scorecards = scorecards;
    }
}
