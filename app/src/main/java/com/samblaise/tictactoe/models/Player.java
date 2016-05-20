package com.samblaise.tictactoe.models;

import java.util.Objects;

/**
 * Created by samuel on 20/05/16.
 */
public class Player {
    static final String cID = "id";
    static final String cNOM = "nom";
    private int id;
    private String ip;
    private String nom;

    public Player(int id, String ip, String nom){
        this.id = id;
        this.ip = ip;
        this.nom=  nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(ip, player.ip) &&
                Objects.equals(nom, player.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ip, nom);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
