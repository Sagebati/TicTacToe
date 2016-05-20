package com.samblaise.tictactoe.utils;

/**
 * Created by administrateur on 20/05/16.
 */
public interface MesageI{
    static String JSON = "json";

    String getIp();
    String getPort();
    void getMesage();
    Boolean testMes();
    void setProt();
}
