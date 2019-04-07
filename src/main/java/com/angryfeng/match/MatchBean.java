package com.angryfeng.match;

/**
 * Created by Administrator on 2019/4/7 0007.
 */
public class MatchBean {
    private int playerId;
    private long matchBeginTime;
    private MatchStatus matchStatus = MatchStatus.matching;

    public MatchBean(int playerId, long matchBeginTime) {
        this.playerId = playerId;
        this.matchBeginTime = matchBeginTime;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public long getMatchBeginTime() {
        return matchBeginTime;
    }

    public void setMatchBeginTime(long matchBeginTime) {
        this.matchBeginTime = matchBeginTime;
    }

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    public synchronized boolean canMatch(MatchBean visitor) {
        //第2档匹配
        //第1档匹配
        if (matchStatus.equals(MatchStatus.matching)) {
            matchStatus = MatchStatus.matched;
            return true;
        }
        return false;
    }
}
