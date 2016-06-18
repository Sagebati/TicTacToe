package com.samblaise.tictactoe.Classes;

import android.test.mock.MockApplication;

import com.samblaise.tictactoe.utils.JSONSerialisable;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Project : TicTacToe
 * ${PACKAGE_NAME}
 * Created by samuel on 20/05/16.
 */
public class Player extends JSONSerialisable{

    public static final String cCOULEUR = "couleur";
    public static final String cNOM = "name";
    public static final String cID = "_id";

    private String id;
    private String couleur;
    private String nom;


    public Player(String nom) {
        this("", "",nom);
    }

    public Player(String couleur, String nom){
        this("",couleur,nom);
    }

    public Player(String id, String couleur, String nom){
        this.id = id;
        this.couleur = couleur;
        this.nom=  nom;
    }

    public Player(JSONObject jo) throws JSONException {
        this.nom = jo.getString(cNOM);
        this.id = jo.getString(cID);
        this.couleur = jo.getString(cCOULEUR);
    }

    @Override
    public String toJSONString() {
        return "{\'"+cID+"\':\'"+this.id+"\'," +
                "\'"+cCOULEUR+"\':\'"+this.couleur+"\'," +
                "\'"+cNOM+"\':\'"+this.nom+"\'}";
    }
    @Override
    public Map<String,String> getParams(){
        HashMap<String,String> hp = new HashMap<>();
        hp.put(cID, id);
        hp.put(cCOULEUR, couleur);
        hp.put(cNOM,nom);
        return hp;
    }

    @Override
    public ArrayList<String> getJSONNames() {
        ArrayList<String> al = new ArrayList<>();
        al.add(cID);
        al.add(cNOM);
        al.add(cCOULEUR);
        return al;
    }

    @Override
    public String toString(){
        return toJSONString();
    }

    public String getId() {
        return id;
    }

    public String getCouleur() {
        return couleur;
    }

    public String getNom() {
        return nom;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return id.equals(player.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
