package com.lavinia.domain;

import java.util.ArrayList;

/**
 * Bowling scorecard containing the total number of frames (which can vary depending on the variation of game being
 * played), frames played, and total score.
 *
 */
public class Scorecard {

    public static final Integer DEFAULT_TOTAL_FRAMES = 10;

    public final Integer totalFrames;
    public final ArrayList<Frame> playedFrames;
    public final Integer totalScore;

    public Scorecard(final Integer totalFrames, final ArrayList<Frame> playedFrames) {
        this.totalFrames = totalFrames;
        this.playedFrames = playedFrames;
        this.totalScore = 0;
    }

    public Scorecard(final Integer totalFrames) {
        this(totalFrames, new ArrayList<>(totalFrames));
    }

    public Scorecard() {
        this(DEFAULT_TOTAL_FRAMES);
    }

    public Frame getFrame(Integer frameNum) {
        return this.playedFrames.get(frameNum - 1);
    }

    public Integer getFrameNumberToBePlayed() {
        return this.playedFrames.size() + 1;
    }

    public Integer getTotalFramesPlayed() {
        return this.playedFrames.size();
    }

    public Scorecard addFrame(final Frame frame) {
        this.playedFrames.add(frame);
        return this;
    }

    public Integer getTotalScore() {
        return this.playedFrames.stream().mapToInt(f -> f.score).sum();
    }

    public boolean isScorecardComplete() {
        return this.playedFrames.size() == this.totalFrames;
    }
}
