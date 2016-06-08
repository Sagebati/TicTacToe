package com.samblaise.tictactoe.runnable;

import com.samblaise.tictactoe.network.Service_ConnectToDB;

/**
 * Project : TicTacToe
 * com.samblaise.tictactoe.runnable
 * Created by sam on 02/06/16.
 */

public class WaitConnectToBd implements Runnable{
    final private Service_ConnectToDB service_connectToDB;

    public WaitConnectToBd(Service_ConnectToDB service_connectToDB) {
        this.service_connectToDB = service_connectToDB;
    }

    @Override
    public void run() {
        
    }
}
