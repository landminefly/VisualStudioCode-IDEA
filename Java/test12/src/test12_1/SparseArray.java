package test12_1;

import java.util.Arrays;

public class SparseArray {
    public static void main(String[] args) {

        int[][] arr = new int[][]{{1, 2, 3}, {1, 2, 3}};
        for (int[] i : arr) {
            for (int j : i) {
                System.out.print(j);
            }
        }
    }
}
