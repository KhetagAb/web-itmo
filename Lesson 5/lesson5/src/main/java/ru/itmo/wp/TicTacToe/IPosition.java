package ru.itmo.wp.TicTacToe;

import ru.itmo.wp.TicTacToe.game.Cell;
import ru.itmo.wp.TicTacToe.game.Move;

public interface IPosition {
    boolean isValid(Move move);

    int getCols();
    int getRows();

    Cell getCell(int row, int col);
}
