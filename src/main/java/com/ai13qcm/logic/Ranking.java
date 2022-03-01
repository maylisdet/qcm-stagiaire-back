package com.ai13qcm.logic;

import java.math.BigInteger;

public class Ranking {
    private BigInteger nbRespondent, scoreRank;
    private Integer  bestScore, durationOfBestScore;

    public Ranking(BigInteger nbRespondent, BigInteger scoreRank, Integer bestScore, Integer durationOfBestScore) {
        this.nbRespondent = nbRespondent;
        this.scoreRank = scoreRank;
        this.bestScore = bestScore;
        this.durationOfBestScore = durationOfBestScore;
    }

    public Integer getBestScore() {
        return bestScore;
    }

    public void setBestScore(Integer bestScore) {
        this.bestScore = bestScore;
    }

    public Integer getDurationOfBestScore() {
        return durationOfBestScore;
    }

    public void setDurationOfBestScore(Integer durationOfBestScore) {
        this.durationOfBestScore = durationOfBestScore;
    }

    public BigInteger getNbRespondent() {
        return nbRespondent;
    }

    public void setNbRespondent(BigInteger nbRespondent) {
        this.nbRespondent = nbRespondent;
    }

    public BigInteger getScoreRank() {
        return scoreRank;
    }

    public void setScoreRank(BigInteger scoreRank) {
        this.scoreRank = scoreRank;
    }
}
