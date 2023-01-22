package test11_4;

import java.util.Arrays;

public class QuickSortTest {
    public static void main(String[] args) {
        int[] ints = new int[10];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 100);
        }
        QuickSort.startSortInOrder(ints);
        System.out.println(Arrays.toString(ints));
        QuickSort.startSortInReverseOrder(ints);
        System.out.println(Arrays.toString(ints));
    }
}

class QuickSort {
    public static int temp;

    public static void startSortInOrder(int[] arr) {
        sortInOrder(arr, 0, arr.length - 1);
    }

    public static void sortInOrder(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int leftIndex = left;
        int rightIndex = right;
        int pivot = left;
        while (leftIndex < rightIndex) {
            while (leftIndex < rightIndex && arr[rightIndex] >= arr[pivot]) {
                rightIndex--;
            }
            while (leftIndex < rightIndex && arr[leftIndex] <= arr[pivot]) {
                leftIndex++;
            }
            temp = arr[leftIndex];
            arr[leftIndex] = arr[rightIndex];
            arr[rightIndex] = temp;
        }
        temp = arr[pivot];
        arr[pivot] = arr[leftIndex];
        arr[leftIndex] = temp;

        sortInOrder(arr, left, leftIndex - 1);
        sortInOrder(arr, leftIndex + 1, right);
    }

    public static void startSortInReverseOrder(int[] arr) {
        sortInReverseOrder(arr, 0, arr.length - 1);
    }

    public static void sortInReverseOrder(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int leftIndex = left;
        int rightIndex = right;
        int pivot = left;
        while (leftIndex < rightIndex) {
            while (leftIndex < rightIndex && arr[rightIndex] <= arr[pivot]) {
                rightIndex--;
            }
            while (leftIndex < rightIndex && arr[leftIndex] >= arr[pivot]) {
                leftIndex++;
            }
            temp = arr[leftIndex];
            arr[leftIndex] = arr[rightIndex];
            arr[rightIndex] = temp;
        }
        temp = arr[pivot];
        arr[pivot] = arr[leftIndex];
        arr[leftIndex] = temp;

        sortInReverseOrder(arr, left, leftIndex - 1);
        sortInReverseOrder(arr, leftIndex + 1, right);
    }
}
