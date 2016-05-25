package com.samblaise.tictactoe.network;

import com.samblaise.tictactoe.utils.AbsMessage;

public final class MessageMove extends AbsMessage{


    public MessageMove(Prot prot, int id, String nom) {
        super(prot, id, nom);
    }

    @Override
    public Boolean isValid() {
        Boolean b = true;
        if (super.getId() == 0)
            b=false;
        if (super.getX() == 0)
            b=false;
        if (super.getIdj() == 0)
            b=false;
        return b;
    }


}
