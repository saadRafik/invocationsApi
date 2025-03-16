package com.inv.services;

import com.inv.models.BaseMonster;
import com.inv.models.InvokedMonster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class InvocationService {

    private List<BaseMonster> baseMonsters = new ArrayList<>();
    private List<InvokedMonster> invocationHistory = new ArrayList<>();
    private Random random = new Random();

    @Autowired
    private RestTemplate restTemplate; // Inject RestTemplate

    public InvocationService() {
        // Initializing base monsters with probabilities
        baseMonsters.add(new BaseMonster("m1", "Dragon", "fire", 100, 50, 30, 20, 0.1));
        baseMonsters.add(new BaseMonster("m2", "Kraken", "water", 120, 40, 40, 25, 0.2));
        baseMonsters.add(new BaseMonster("m3", "Phoenix", "wind", 90, 60, 20, 30, 0.7));
    }

    /**
     * Handles the monster invocation process and updates the player's inventory via playerApi.
     */
    public InvokedMonster invokeMonsterForPlayer(String playerId) {
        BaseMonster selected = selectRandomMonster();
        String generatedMonsterId = "MON-" + selected.getId() + "-" + System.currentTimeMillis();

        InvokedMonster invoked = new InvokedMonster(generatedMonsterId, selected.getName(), playerId, LocalDateTime.now());
        invocationHistory.add(invoked);

        // Call player API to add the invoked monster to the player's inventory
        String playerApiUrl = "http://localhost:8083/player/addMonster";
        try {
            restTemplate.postForObject(playerApiUrl, invoked, Void.class);
        } catch (Exception e) {
            System.err.println("Failed to update player inventory: " + e.getMessage());
        }

        return invoked;
    }

    // Selects a monster based on probability
    private BaseMonster selectRandomMonster() {
        double totalWeight = baseMonsters.stream().mapToDouble(BaseMonster::getProbability).sum();
        double r = random.nextDouble() * totalWeight;
        double cumulative = 0.0;
        for (BaseMonster monster : baseMonsters) {
            cumulative += monster.getProbability();
            if (r <= cumulative) {
                return monster;
            }
        }
        return baseMonsters.get(0); // Fallback (should never happen)
    }

    public List<InvokedMonster> getInvocationHistory() {
        return invocationHistory;
    }
}
