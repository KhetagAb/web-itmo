package ru.itmo.wp.TicTacToeAsync;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Game implements Runnable {
    public static final int EXTRA_TURN = -2;
    private final boolean log;
    private final IPlayer player1, player2;
    private IBoard board;

    public Game(final boolean log, final IPlayer player1, final IPlayer player2) {
        this.log = log;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void init(IBoard board) {
        this.board = board;
    }

    public int play(IBoard board) {
        while (true) {
            final int result1 = playerMove(board, player1, 1);
            if (result1 != -1) {
                return result1;
            }

            final int result2 = playerMove(board, player2, 2);
            if (result2 != -1) {
                return result2;
            }
        }
    }

    private int playerMove(final IBoard board, final IPlayer player, final int no) {
        int result;
        do {
            result = move(board, player, no);
        } while (result == EXTRA_TURN);

        return result;
    }

    private int move(final IBoard board, final IPlayer player, final int no) {
        final Move move = player.move(board.getPosition(), board.getTurnCell());
        final Result result = board.makeMove(move);
        log("Player " + no + " move: " + move);
        log(board.toString());
        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lose");
            return 3 - no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else if (result == Result.SKIP) {
            log("Bonus turn");
            return EXTRA_TURN;
        } else {
            return -1;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }

    @Override
    public void run() {
        play(board);
    }
}
