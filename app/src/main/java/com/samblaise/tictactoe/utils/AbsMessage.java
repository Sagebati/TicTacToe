package com.samblaise.tictactoe.utils;

import com.samblaise.tictactoe.network.Prot;

public abstract class AbsMessage{
    private final Prot prot;
    /**
     * Id of the game
     */
    private final int id;
    /**
     * Name on the player
     */
    private final String nom;
    private int x,y;
    /**
     * id of the player
     */
    private int idj;

    public AbsMessage(Prot prot, int id, String nom) {
        this.prot = prot;
        this.id = id;
        this.nom = nom;
    }

    public AbsMessage(Prot prot, int id, String nom, int x, int y, int idj) {
        this.prot = prot;
        this.id = id;
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.idj = idj;
    }

    /**
     * @return if the message is valid
     */
    public abstract Boolean isValid();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbsMessage)) return false;

        AbsMessage that = (AbsMessage) o;

        return id == that.id && x == that.x && y == that.y && idj == that.idj && prot == that.prot && nom.equals(that.nom);

    }

    @Override
    public int hashCode() {
        int result = prot.hashCode();
        result = 31 * result + id;
        result = 31 * result + nom.hashCode();
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + idj;
        return result;
    }

    public Prot getProt() {
        return prot;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIdj() {
        return idj;
    }

}
