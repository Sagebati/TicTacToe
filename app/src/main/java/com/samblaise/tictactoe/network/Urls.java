package com.samblaise.tictactoe.network;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Project : TicTacToe
 * ${PACKAGE_NAME}
 * Created by sam on 28/05/16.
 */

enum Urls {
    GAME ("/games"),
    PLAYER ("/players");

    private String url;
    public static String PROT = "http://";
    public static String PORT = "3000";
    public static String ABBAYE = "abbaye.noip.me:";
    public static String LOCALHOST = "10.0.3.2:";

    Urls(String s){
        this.url = s;
    }

    public URL getUrl() {
        URL res= null;
        try {
            res = new URL(ABBAYE+this.url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public String getUrlStr(){
        return  PROT+LOCALHOST+PORT+this.url;
    }
    public static String paramToStr(Map<String,String> p){
        StringBuilder sb = new StringBuilder();
        for (String s :
                p.keySet()) {
            sb.append(s);
            sb.append('/');
            sb.append(p.get(s));
        }
        return  sb.toString();
    }
}
