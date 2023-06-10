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
    //单次循环中找到的最小元素的下标
    public static int index;
    //单次循环中找到的最小元素值
    public static int value;

    //正序
    public static void sortInOrder(int[] arr){
        for(int i = 0; i<arr.length - 1; i++){
            //找单次循环中的最小元素
            //先假设这次循环中的最小元素就是arr[n]
            index = i;
            value = arr[i];
            for(int j = i + 1; j<arr.length; j++){
                if(arr[j] < value){
                    index = j;
                    value = arr[j];
                }
            }
            //如果这次循环中的最小元素不是arr[n]，则交换
            if(index != i){
                arr[index] = arr[i];
                arr[i] = value;
            }
        }
    }

    //倒序
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
            if(index != i){
                arr[index] = arr[i];
                arr[i] = value;
            }
        }
    }
}
