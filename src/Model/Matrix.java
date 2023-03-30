package Model;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Matrix implements Serializable{

    private static int n = 4;
    private ArrayList<ArrayList<Integer>> matrix = new ArrayList<>(n + 1);

    public Matrix(ArrayList<ArrayList<Integer>> matrix) {
        this.matrix = new ArrayList<>(matrix.size());
        for (int i = 0; i < matrix.size(); i++) {
            this.matrix.add(new ArrayList<>(matrix.get(i)));
        }
    }

    public Matrix() {
        for (int i = 0; i <= n + 1; i++) {
            ArrayList<Integer> row = new ArrayList<>(n + 1);
            for (int j = 0; j <= n + 1; j++) {
                row.add(0);
            }
            matrix.add(row);
        }

        for (int i = 1; i <= 2; i++) {
            int randRow = ThreadLocalRandom.current().nextInt(1, n + 1);
            int randCol = ThreadLocalRandom.current().nextInt(1, n + 1);

            int randNum = ThreadLocalRandom.current().nextInt(0, 2);
            int randValue = (randNum == 0) ? 2 : 4;
            matrix.get(randRow).set(randCol, randValue);
        }
        
    }

    public void output() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int getN() {
        return n;
    }

    public int getValue(int i, int j) {
        return matrix.get(i).get(j);
    }

    public int setValue(int i, int j, int value) {
        return matrix.get(i).set(j, value);
    }

    public ArrayList<ArrayList<Integer>> getMatrixArray() {
        return matrix;
    }
}