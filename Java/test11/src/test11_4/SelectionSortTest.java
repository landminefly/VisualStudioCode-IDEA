package test11_4;

import java.util.Arrays;

public class SelectionSortTest {
    public static void main(String[] args) {
        int[] ints = new int[10];
        for(int i = 0; i < ints.length; i++){
            ints[i] = (int)(Math.random() * 100);
        }
        SelectionSort.sortInOrder(ints);
        System.out.println(Arrays.toString(ints));
        SelectionSort.sortInReverseOrder(ints);
        System.out.println(Arrays.toString(ints));
    }
}

class SelectionSort{
    public static int index;
    public static int value;

    public static void sortInOrder(int[] arr){
        for(int i = 0; i<arr.length - 1; i++){
            index = i;
            value = arr[i];
            for(int j = i + 1; j<arr.length; j++){
                if(arr[j] < value){
                    index = j;
                    value = arr[j];
                }
            }
            if(value != i){
                arr[index] = arr[i];
                arr[i] = value;
            }
        }
    }

    public static void sortInReverseOrder(int[] arr){
        for(int i = 0; i<arr.length - 1; i++){
            index = i;
            value = arr[i];
            for(int j = i + 1; j<arr.length; j++){
                if(arr[j] > value){
                    index = j;
                    value = arr[j];
                }
            }
            if(value != i){
                arr[index] = arr[i];
                arr[i] = value;
            }
        }
    }
}
