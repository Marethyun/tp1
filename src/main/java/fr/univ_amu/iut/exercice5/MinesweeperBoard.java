package fr.univ_amu.iut.exercice5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinesweeperBoard {

    private List<String> board;

    public MinesweeperBoard(List<String> inputBoard) {
        this.board = inputBoard;

        if (this.board == null) throw new IllegalArgumentException("Input board may not be null.");

        this.board.forEach(line -> {
            for (char c : line.toCharArray()) {
                if (!(c == ' ' || c == '*')) throw new IllegalArgumentException("Input board can only contain the characters ' ' and '*'.");
            }
        });

        for (int i = 1; i < this.board.size(); i++) {
            if (this.board.get(i).length() != this.board.get(i - 1).length()) throw new IllegalArgumentException("Input board rows must all have the same number of columns.");
        }
    }

    public List<String> getAnnotatedRepresentation() {

        List<String> annotated = new ArrayList<>(this.board);

        for (int y = 0; y < this.board.size(); y++) {
            String line = this.board.get(y);

            for (int x = 0; x < line.toCharArray().length; x++) {
                char c = line.charAt(x);

                if (c == '*') {
                    if (y > 0) {
                        char[] top = annotated.get(y - 1).toCharArray();
                        top[x] = increment(top[x]);

                        if (x > 0) {
                            top[x - 1] = increment(top[x - 1]);
                        }

                        if (x < line.toCharArray().length - 1) {
                            top[x + 1] = increment(top[x + 1]);
                        }

                        annotated.set(y - 1, new String(top));
                    }

                    char[] current = annotated.get(y).toCharArray();
                    if (x > 0) {
                        current[x - 1] = increment(current[x - 1]);
                    }

                    if (x < line.toCharArray().length - 1) {
                        current[x + 1] = increment(current[x + 1]);
                    }
                    annotated.set(y, new String(current));

                    if (y < this.board.size() - 1) {
                        char[] bottom = annotated.get(y + 1).toCharArray();
                        bottom[x] = increment(bottom[x]);

                        if (x > 0) {
                            bottom[x - 1] = increment(bottom[x - 1]);
                        }

                        if (x < line.toCharArray().length - 1) {
                            bottom[x + 1] = increment(bottom[x + 1]);
                        }

                        annotated.set(y + 1, new String(bottom));
                    }
                }
            }
        }

        return annotated;
    }

    private char increment(char c) {
        if (c == ' ') return '1';
        if (c == '*') return '*';
        int val = Character.getNumericValue(c);
        return Character.forDigit(val + 1, 10);
    }
}
