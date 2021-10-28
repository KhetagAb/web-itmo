package ru.itmo.wp.TicTacToeAsync;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface IPosition {
    boolean isValid(Move move);

    int getCols();
    int getRows();

    Cell getCell(int row, int col);
}
