package ru.itmo.wp.TicTacToeAsync;

public class SquareMnkBoard extends MnkBoard {
    public SquareMnkBoard(int rows, int cols, int winCntCond, int skipCntCond) {
        super(rows, cols, winCntCond, skipCntCond, (row, col) -> (0 <= row && row < rows && 0 <= col && col < cols));
    }
}