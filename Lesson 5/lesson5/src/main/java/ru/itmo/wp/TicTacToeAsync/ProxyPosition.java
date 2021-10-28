package ru.itmo.wp.TicTacToeAsync;

public class ProxyPosition implements IPosition {
    private final IPosition position;

    public ProxyPosition(IPosition position) {
        this.position = position;
    }

    @Override
    public boolean isValid(Move move) {
        return position.isValid(move);
    }

    @Override
    public int getCols() {
        return position.getCols();
    }

    @Override
    public int getRows() {
        return position.getRows();
    }

    @Override
    public Cell getCell(int row, int col) {
        return position.getCell(row, col);
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
