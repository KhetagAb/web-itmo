package ru.itmo.wp.TicTacToe;

import ru.itmo.wp.TicTacToe.game.Cell;
import ru.itmo.wp.TicTacToe.game.Move;
import ru.itmo.wp.TicTacToe.game.Phase;

public interface IBoard {
    IPosition getPosition();
    Cell getTurnCell();
    Phase getPhase();
    Phase makeMove(Move move);
}
