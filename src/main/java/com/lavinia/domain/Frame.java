package com.lavinia.domain;

import java.util.ArrayList;

public class Frame {

    public final ArrayList<Roll> rolls;
    public Integer score;

    /**
     * @param score
     * @param rolls
     */
    public Frame(final Integer score, final ArrayList<Roll> rolls) {
        this.rolls = rolls;
        this.score = score;
    }

    public Frame(final ArrayList<Roll> rolls) {
        this.rolls = rolls;
        this.score = rolls.stream().mapToInt(r -> r.pins).sum();
    }

    public Integer addToScore(final Integer points) {
        return this.score += points;
    }

    public boolean isStrike() {
        return this.rolls.get(0).pins == 10;
    }

    public boolean isSpare() {
        if (rolls.size() == 2) {
            return this.rolls.stream().mapToInt(r -> r.pins).sum() == 10;
        }

        return false;
    }
}
