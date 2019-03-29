package com.lavinia.domain;

import java.util.ArrayList;

public class Scorecard {

    public final Integer DEFAULT_TOTAL_FRAMES = 10;

    public final Integer totalFrames;
    public final ArrayList<Frame> playedFrames;
    public final Integer totalScore;

    public Scorecard(final Integer totalFrames, final ArrayList<Frame> playedFrames) {
        this.playedFrames = playedFrames;
        this.totalScore = 0;
        this.totalFrames = totalFrames;
    }

    public Scorecard(final Integer totalFrames) {
        this(totalFrames, new ArrayList<Frame>(totalFrames));
    }

    public Scorecard() {
        this(10);
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

    public boolean isScoreComplete() {
        return this.playedFrames.size() == 10;
    }

}
