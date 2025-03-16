package com.inv.controllers;

import com.inv.models.InvokedMonster;
import com.inv.services.InvocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invocations")
public class InvocationController {

    @Autowired
    private InvocationService invocationService;

    // Endpoint pour invoquer un monstre pour un joueur donné
    @PostMapping("/{playerId}")
    public InvokedMonster invokeMonster(@PathVariable String playerId) {
        return invocationService.invokeMonsterForPlayer(playerId);
    }

    // Endpoint pour consulter l’historique des invocations
    @GetMapping("/history")
    public List<InvokedMonster> getInvocationHistory() {
        return invocationService.getInvocationHistory();
    }
}
