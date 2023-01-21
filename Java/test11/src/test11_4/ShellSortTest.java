package test11_4;

import java.util.Arrays;

public class ShellSortTest {
    public static void main(String[] args) {
        int[] ints = new int[10];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 100);
        }
        ShellSort.sortInOrder(ints);
        System.out.println(Arrays.toString(ints));
        ShellSort.sortInReverseOrder(ints);
        System.out.println(Arrays.toString(ints));
    }
}

class ShellSort {
    public static int insertIndex;
    public static int insertValue;

    public static void sortInOrder(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                insertIndex = i;
                insertValue = arr[i];
                if (arr[insertIndex] < arr[insertIndex - gap]) {
                    while (insertIndex > 0 && insertValue < arr[insertIndex - 1]) {
                        arr[insertIndex] = arr[insertIndex - 1];
                        insertIndex--;
                    }
                    arr[insertIndex] = insertValue;
                }
            }
        }
    }

    public static void sortInReverseOrder(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                insertIndex = i;
                insertValue = arr[i];
                if (arr[insertIndex] > arr[insertIndex - gap]) {
                    while (insertIndex > 0 && insertValue > arr[insertIndex - 1]) {
                        arr[insertIndex] = arr[insertIndex - 1];
                        insertIndex--;
                    }
                    arr[insertIndex] = insertValue;
                }
            }
        }
    }
}
