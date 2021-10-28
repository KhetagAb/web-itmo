package ru.itmo.wp.TicTacToeAsync;

import java.util.Random;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class RandomPlayer implements IPlayer {
    private final Random random;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer() {
        this(new Random());
    }

    @Override
    public Move move(final IPosition position, final Cell cell) {
        while (true) {
            int r = random.nextInt(position.getRows());
            int c = random.nextInt(position.getCols());
            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
