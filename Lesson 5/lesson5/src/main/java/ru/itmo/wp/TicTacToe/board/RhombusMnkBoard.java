package ru.itmo.wp.TicTacToe.board;

public class RhombusMnkBoard extends MnkBoard {
    public RhombusMnkBoard(int side, int winCntCond, int skipCntCond) {
        super(2 * side - 1, 2 * side - 1, winCntCond, skipCntCond,
                ((row, col) ->  Math.abs(side - col - 1) + Math.abs(side - row - 1) <= side - 1));
    }
}