package com.inv.models;

import java.time.LocalDateTime;

public class InvokedMonster {
    private String monsterId;
    private String monsterName;
    private String playerId;
    private LocalDateTime invocationTime;

    public InvokedMonster(String monsterId, String monsterName, String playerId, LocalDateTime invocationTime) {
        this.monsterId = monsterId;
        this.monsterName = monsterName;
        this.playerId = playerId;
        this.invocationTime = invocationTime;
    }

    // Getters
    public String getMonsterId() { return monsterId; }
    public String getMonsterName() { return monsterName; }
    public String getPlayerId() { return playerId; }
    public LocalDateTime getInvocationTime() { return invocationTime; }
}
