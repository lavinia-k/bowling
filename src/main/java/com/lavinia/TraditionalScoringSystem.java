package com.lavinia;

import com.lavinia.domain.Frame;
import com.lavinia.domain.Roll;
import com.lavinia.domain.Scorecard;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * The most common method of scoring in bowling (TRADITIONAL).
 */
public class TraditionalScoringSystem implements ScoringSystem {

    public Integer TOTAL_NUMBER_OF_FRAMES = 10;

    public Scorecard createScorecard() {
        return new Scorecard(TOTAL_NUMBER_OF_FRAMES);
    }

    public Scorecard addFrameAndUpdateScores(final Scorecard scorecard,
                                             final ArrayList<Roll> frameRolls) {

        validateRolls(scorecard, frameRolls);

        Frame frame = new Frame(frameRolls);
        scorecard.addFrame(frame);

        Integer totalFramesPlayed = scorecard.getTotalFramesPlayed();

        if (totalFramesPlayed == 1) {
            return scorecard;
        }

        for (int frameIndex=0; frameIndex < 2; frameIndex++) {

            Integer currentFrameNum = totalFramesPlayed - 1 - frameIndex;

            if (currentFrameNum <= 0) {
                break;
            }

            Frame currentFrame = scorecard.getFrame(currentFrameNum);

            if (currentFrame.isSpare() && frameIndex == 0) {
                currentFrame.addToScore(frameRolls.get(0).pins);
                break;
            }

            if (currentFrame.isStrike()) {
                if (frameIndex == 1 && !scorecard.getFrame(currentFrameNum + 1).isStrike()) {
                    continue;
                }

                currentFrame.addToScore(frameRolls.get(0).pins);

                if (frameIndex == 0 && frame.rolls.size() >= 2) {
                    currentFrame.addToScore(frameRolls.get(1).pins);
                }
            }
        }

        return null;
    }

    public void validateRolls(final Scorecard scorecard, final ArrayList<Roll> rolls) {
        if (scorecard.getFrameNumberToBePlayed() == 10) {

            if (rolls.size() == 0 || rolls.size() > 3) {
                throw new InvalidParameterException(
                        "Expected at least 1 and not more than 3 rolls, but received: " + rolls.size()
                );
            }

            if (rolls.get(0).pins == 10 && rolls.size() != 3) {
                throw new InvalidParameterException(
                        "Found a strike in the first roll of the final frame - expected 3 total rolls for this " +
                                "frame, but received: " + rolls.size()
                );
            }

            if ((rolls.get(0).pins + rolls.get(1).pins) == 10 && rolls.size() != 3) {
                throw new InvalidParameterException(
                        "Found a spare in the second roll of the final frame - expected 3 total rolls for this " +
                                "frame, but received: " + rolls.size()
                );
            }

        } else {
            if (rolls.size() == 0 || rolls.size() > 2) {
                throw new InvalidParameterException(
                        "Expected at least 1 and not more than 2 rolls, but received: " + rolls.size()
                );
            }

            if (rolls.get(0).pins == 10 && rolls.size() > 1) {
                throw new InvalidParameterException("Expected no rolls after a strike: " + rolls.size());
            }
        }
    }
}
