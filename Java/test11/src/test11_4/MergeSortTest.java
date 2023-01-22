package test11_4;

import java.util.Arrays;

public class MergeSortTest {
    public static void main(String[] args) {
        int[] ints = new int[10];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 1000);
        }
        MergeSort.startSortInOrder(ints);
        System.out.println(Arrays.toString(ints));
        MergeSort.startSortInReverseOrder(ints);
        System.out.println(Arrays.toString(ints));
    }
}

class MergeSort {
    public static int[] temp;
    public static int tempIndex;

    public static void startSortInOrder(int[] arr) {
        temp = new int[arr.length];
        sortInOrder(arr, 0, arr.length - 1);
    }

    public static void sortInOrder(int[] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            sortInOrder(arr, left, middle);
            sortInOrder(arr, middle + 1, right);

            tempIndex = 0;
            int firstIndex = left;
            int secondIndex = middle + 1;

            while (firstIndex <= middle && secondIndex <= right) {
                if (arr[firstIndex] <= arr[secondIndex]) {
                    temp[tempIndex++] = arr[firstIndex++];
                } else {
                    temp[tempIndex++] = arr[secondIndex++];
                }
            }

            while (firstIndex <= middle) {
                temp[tempIndex++] = arr[firstIndex++];
            }
            while (secondIndex <= right) {
                temp[tempIndex++] = arr[secondIndex++];
            }

            tempIndex = 0;
            int arrIndex = left;
            while (arrIndex <= right) {
                arr[arrIndex++] = temp[tempIndex++];
            }
        }
    }

    public static void startSortInReverseOrder(int[] arr) {
        temp = new int[arr.length];
        sortInReverseOrder(arr, 0, arr.length - 1);
    }

    public static void sortInReverseOrder(int[] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            sortInReverseOrder(arr, left, middle);
            sortInReverseOrder(arr, middle + 1, right);

            tempIndex = 0;
            int firstIndex = left;
            int secondIndex = middle + 1;

            while (firstIndex <= middle && secondIndex <= right) {
                if (arr[firstIndex] <= arr[secondIndex]) {
                    temp[tempIndex++] = arr[secondIndex++];
                } else {
                    temp[tempIndex++] = arr[firstIndex++];
                }
            }

            while (firstIndex <= middle) {
                temp[tempIndex++] = arr[firstIndex++];
            }
            while (secondIndex <= right) {
                temp[tempIndex++] = arr[secondIndex++];
            }

            tempIndex = 0;
            int arrIndex = left;
            while (arrIndex <= right) {
                arr[arrIndex++] = temp[tempIndex++];
            }
        }
    }
}
