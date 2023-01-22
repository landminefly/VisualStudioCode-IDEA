package test11_4;

import java.util.Arrays;

public class RadixSortTest {
    public static void main(String[] args) {
        int[] ints = new int[10];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 100);
        }
        RadixSort.sortInOrder(ints);
        System.out.println(Arrays.toString(ints));
        RadixSort.sortInReverseOrder(ints);
        System.out.println(Arrays.toString(ints));
    }
}

class RadixSort {
    public static void sortInOrder(int[] arr) {
        int max = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }
        int topDigit = ("" + max).length();

        int[][] buckets = new int[10][arr.length];
        int[] bucketCounts = new int[10];

        for (int i = 0, k = 1; i < topDigit; i++, k *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digit = arr[j] / k % 10;
                buckets[digit][(bucketCounts[digit])++] = arr[j];
            }

            int arrIndex = 0;
            for (int m = 0; m < bucketCounts.length; m++) {
                if (bucketCounts[m] != 0) {
                    for (int n = 0; n < bucketCounts[m]; n++) {
                        arr[arrIndex++] = buckets[m][n];
                    }
                }
                bucketCounts[m] = 0;
            }
        }
    }

    public static void sortInReverseOrder(int[] arr) {
        int max = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }
        int topDigit = ("" + max).length();

        int[][] buckets = new int[10][arr.length];
        int[] bucketCounts = new int[10];

        for (int i = 0, k = 1; i < topDigit; i++, k *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digit = arr[j] / k % 10;
                buckets[digit][(bucketCounts[digit])++] = arr[j];
            }

            int arrIndex = 0;
            for (int m = bucketCounts.length - 1; m >= 0; m--) {
                if (bucketCounts[m] != 0) {
                    for (int n = 0; n < bucketCounts[m]; n++) {
                        arr[arrIndex++] = buckets[m][n];
                    }
                }
                bucketCounts[m] = 0;
            }
        }
    }
}