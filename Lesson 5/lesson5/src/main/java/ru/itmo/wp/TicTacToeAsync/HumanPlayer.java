package ru.itmo.wp.TicTacToeAsync;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements IPlayer {
    private final IController gc;

    public HumanPlayer(final PrintStream out, final InputStream in) {
        this.gc = new GameController(out, in);
    }

    public HumanPlayer() {
        this(System.out, System.in);
    }

    @Override
    public Move move(final IPosition position, final Cell cell) {
        while (true) {
            gc.showPosition(position);
            gc.showMsg(cell + "'s move");

            final int row = gc.intInputFor("Row");
            final int col = gc.intInputFor("Col");
            final Move move = new Move(row - 1, col - 1, cell);

            if (position.isValid(move)) {
                return move;
            }

            gc.showMsg("Move " + move + " is invalid");
        }
    }
}
