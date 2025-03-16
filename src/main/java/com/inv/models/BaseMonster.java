package com.inv.models;

public class BaseMonster {
    private String id;
    private String name;
    private String element; // "feu", "eau", "vent"
    private int hp;
    private int atk;
    private int def;
    private int vit;
    private double probability; // poids pour la sélection aléatoire

    public BaseMonster(String id, String name, String element, int hp, int atk, int def, int vit, double probability) {
        this.id = id;
        this.name = name;
        this.element = element;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.vit = vit;
        this.probability = probability;
    }

    // Getters (et setters si nécessaire)
    public String getId() { return id; }
    public String getName() { return name; }
    public String getElement() { return element; }
    public int getHp() { return hp; }
    public int getAtk() { return atk; }
    public int getDef() { return def; }
    public int getVit() { return vit; }
    public double getProbability() { return probability; }
}

