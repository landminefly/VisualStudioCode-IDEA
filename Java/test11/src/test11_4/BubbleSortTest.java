package test11_4;

import java.util.Arrays;

public class BubbleSortTest {
    public static void main(String[] args) {
        int[] ints = new int[10];
        for(int i = 0; i < ints.length; i++){
            ints[i] = (int)(Math.random() * 100);
        }
        BubbleSort.sortInOrder(ints);
        System.out.println(Arrays.toString(ints));
        BubbleSort.sortInReverseOrder(ints);
        System.out.println(Arrays.toString(ints));
    }
}

class BubbleSort {
    public static int temp;
    public static boolean isExchange;

    public static void sortInOrder(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    isExchange = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if(!isExchange){
                break;
            }else{
                isExchange = false;
            }
        }
    }

    public static void sortInReverseOrder(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    isExchange = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if(!isExchange){
                break;
            }else{
                isExchange = false;
            }
        }
    }
}
