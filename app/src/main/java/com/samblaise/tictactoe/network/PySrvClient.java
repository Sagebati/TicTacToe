package com.samblaise.tictactoe.network;

import com.samblaise.tictactoe.models.Move;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class PySrvClient {
    static final String DSTNAME = "abbaye.noip.me";
    static final int PORT = 8686;

    private final Socket socket;
    private final OutputStream writer;

    public PySrvClient() throws IOException {
        this.socket = new Socket(DSTNAME,PORT);
        this.writer = socket.getOutputStream();

    }

    public void jouer(int idGame,Move mv){
        //TODO jouer
    }
}
