package ru.itmo.wp.TicTacToeAsync;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface IBoard {
    IPosition getPosition();
    Cell getTurnCell();
    Result makeMove(Move move);
}
