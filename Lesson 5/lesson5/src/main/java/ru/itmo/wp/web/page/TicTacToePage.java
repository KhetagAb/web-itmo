package ru.itmo.wp.web.page;

import ru.itmo.wp.TicTacToe.*;
import ru.itmo.wp.TicTacToe.board.CircleMnkBoard;
import ru.itmo.wp.TicTacToe.board.TicTacToeBoard;
import ru.itmo.wp.TicTacToe.game.Cell;
import ru.itmo.wp.TicTacToe.game.Move;
import ru.itmo.wp.TicTacToe.game.Phase;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TicTacToePage {
    public void onMove(HttpServletRequest request, Map<String, Object> view) {
        HttpSession session = request.getSession();
        IBoard board = (IBoard) session.getAttribute("board");

        if (board != null) {
            Phase phase = board.getPhase();
            if (phase == Phase.RUNNING || phase == Phase.BONUS) {
                Move move = null;
                List<String> keys = new ArrayList<>(request.getParameterMap().keySet());
                for (String key : keys) {
                    if (key.startsWith("cell_")) {
                        try {
                            move = new Move(Integer.parseInt(key.substring(5, 6)),
                                    Integer.parseInt(key.substring(6, 7)),
                                    board.getTurnCell());
                        } catch (NumberFormatException ignored) {
                            // Do nothing
                        }
                        break;
                    }
                }
                board.makeMove(move);
            }

            throw new RedirectException("/ticTacToe");
        }
    }

    private void showBoard(Map<String, Object> view, IBoard board) {
        view.put("state", State.of(board));
    }

    public void action(HttpServletRequest request, Map<String, Object> view) {
        IBoard board = (IBoard) request.getSession().getAttribute("board");
        if (board == null) {
            newGame(request, view);
        } else {
            showBoard(view, board);
        }
    }

    public void newGame(HttpServletRequest request, Map<String, Object> view) {
        IBoard board = new TicTacToeBoard();
        request.getSession().setAttribute("board", board);
        showBoard(view, board);
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

        public static State of(IBoard board) {
            IPosition position = board.getPosition();
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

            return new State(position.getCols(),
                    cells,
                    board.getPhase(),
                    board.getTurnCell() == Cell.X);
        }
    }
}