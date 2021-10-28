package ru.itmo.wp.TicTacToeAsync;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class GameController implements IController {
    private final PrintStream out;
    private final Scanner in;

    public GameController(PrintStream out, InputStream in) {
        this.in = new Scanner(in);
        this.out = out;
    }

    @Override
    public int intInputFor(String name) {
        while (true) {
            out.print("Enter " + name + ": ");
            try {
                if (!in.hasNextLine()) {
                    throw new IllegalStateException("End of input");
                }

                // :NOTE: parseInt
                return Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                out.println("It must be only one integer within reasonable limits!");
            }
        }
    }

    @Override
    public void showPosition(IPosition position) {
        showMsg(position.toString());
    }

    @Override
    public void showMsg(String msg) {
        out.println(msg);
    }

    @Override
    public void close() {
        in.close();
        out.close();
    }
}
