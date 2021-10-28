package ru.itmo.wp.web.page;

import ru.itmo.wp.TicTacToeAsync.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TicTacToeAsyncPage {
    private static Thread gameThread;
    private static Move move = null;
    private static IBoard board = null;

    public void onMove(HttpServletRequest request, Map<String, Object> view) {
        List<String> keys = new ArrayList<>(request.getParameterMap().keySet());
        for (String key : keys) {
            if (key.startsWith("cell_")) {
                int row = Integer.parseInt(key.substring(5, 6));
                int col = Integer.parseInt(key.substring(6, 7));

                move = new Move(row, col, board.getTurnCell());
                break;
            }
        }

        synchronized (gameThread) {
            gameThread.notify();
        }
        synchronized (board) {
            try {
                board.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            view.put("state", getState(board.getPosition(), board.getTurnCell()));
        }
    }

    public void action(Map<String, Object> view) {
        IPlayer player1 = new WebPlayer();

        IPlayer player2 = new WebPlayer();
        Game game = new Game(true, player1, player2);
        board = new CircleMnkBoard(5, 3, 2);
        game.init(board);

        view.put("state", getState(board.getPosition(), board.getTurnCell()));

        gameThread = new Thread(game);
        gameThread.start();
    }

    private State getState(IPosition position, Cell turn) {
        Character[][] cells = new Character[position.getRows()][position.getCols()];

        for (int i = 0; i < position.getRows(); i++) {
            for (int j = 0; j < position.getCols(); j++) {
                Character cell = null;
                switch (position.getCell(i, j)) {
                    case X:
                        cell = 'X';
                        break;
                    case O:
                        cell = 'O';
                        break;
                    case INVALID:
                        cell = '@';
                        break;
                }
                cells[i][j] = cell;
            }
        }

        return new State(position.getCols(), cells, State.Phase.RUNNING, turn == Cell.X);
    }

    public static class WebPlayer implements IPlayer {
        @Override
        public Move move(IPosition position, Cell cell) {
            synchronized (board) {
                board.notify();
            }

            synchronized (gameThread) {
                try {
                    gameThread.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return move;
            }
        }
    }

    public static class State {
        private final int size;
        private final Character[][] cells;
        private final Phase phase;
        private final boolean crossesMove;

        public State(int size, Character[][] cells, Phase phase, boolean crossesMove) {
            this.size = size;
            this.cells = cells;
            this.phase = phase;
            this.crossesMove = crossesMove;
        }

        public boolean isCrossesMove() {
            return crossesMove;
        }

        public Phase getPhase() {
            return phase;
        }

        public Character[][] getCells() {
            return cells;
        }

        public int getSize() {
            return size;
        }

        public enum Phase {
            RUNNING,
            WON_X,
            WON_O,
            DRAW
        }
    }
}