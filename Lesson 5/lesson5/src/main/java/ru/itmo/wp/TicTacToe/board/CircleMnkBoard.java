package ru.itmo.wp.TicTacToe.board;

public class CircleMnkBoard extends MnkBoard {
    public CircleMnkBoard(int side, int winCntCond, int skipCntCond) {
        super(2 * side - 1, 2 * side - 1, winCntCond, skipCntCond,
                ((row, col) ->  (col - side + 1) * (col - side + 1) + (row - side + 1) * (row - side + 1) <= (side - 1)* (side - 1)));
    }
}
