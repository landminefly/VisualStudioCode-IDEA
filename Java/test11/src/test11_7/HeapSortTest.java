package test11_7;

import java.util.Arrays;

public class HeapSortTest {
    public static void main(String[] args) {
        int[] ints = new int[10];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 1000);
        }
        HeapSort.sortInOrder(ints);
        System.out.println(Arrays.toString(ints));
        HeapSort.sortInReverseOrder(ints);
        System.out.println(Arrays.toString(ints));
    }
}

class HeapSort {
    public static void sortInOrder(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            translateToMaxHeap(arr, i, arr.length);
        }
        int temp;
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            translateToMaxHeap(arr, 0, j);
        }
    }

    public static void translateToMaxHeap(int[] arr, int index, int maxSize) {
        int temp = arr[index];
        for (int tempIndex = index * 2 + 1; tempIndex < maxSize; tempIndex = tempIndex * 2 + 1) {
            if (tempIndex + 1 < maxSize && arr[tempIndex + 1] > arr[tempIndex]) {
                tempIndex++;
            }
            if (arr[tempIndex] > temp) {
                arr[index] = arr[tempIndex];
                arr[tempIndex] = temp;
                index = tempIndex;
            } else {
                break;
            }
        }
    }

    public static void sortInReverseOrder(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            translateToMinHeap(arr, i, arr.length);
        }
        int temp;
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            translateToMinHeap(arr, 0, j);
        }
    }

    public static void translateToMinHeap(int[] arr, int index, int maxSize) {
        int temp = arr[index];
        for (int tempIndex = index * 2 + 1; tempIndex < maxSize; tempIndex = tempIndex * 2 + 1) {
            if (tempIndex + 1 < maxSize && arr[tempIndex + 1] < arr[tempIndex]) {
                tempIndex++;
            }
            if (arr[tempIndex] < temp) {
                arr[index] = arr[tempIndex];
                arr[tempIndex] = temp;
                index = tempIndex;
            } else {
                break;
            }
        }
    }
}
