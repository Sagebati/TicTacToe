package com.samblaise.tictactoe.models;

import java.util.Arrays;
import java.util.Objects;

public class Move {
    final int[] matrixMove;
    Player player;

    public Move(int x, int y, Player player) {
        this.matrixMove = new int[2];
        this.matrixMove[0] = x; this.matrixMove[1] = y;
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Move)) return false;
        Move move = (Move) o;
        return Arrays.equals(matrixMove, move.matrixMove) &&
                Objects.equals(player, move.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matrixMove, player);
    }

    public int[] getMatrixMove() {
        return matrixMove;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
