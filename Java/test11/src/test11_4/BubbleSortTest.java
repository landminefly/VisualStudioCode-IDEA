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
    //元素交换时的临时变量
    public static int temp;
    //判断一次循环中是否发生过交换的变量
    public static boolean isExchange;

    //正序
    public static void sortInOrder(int[] arr) {
        //最多进行 arr.length - 1 次循环即可
        for (int i = 0; i < arr.length - 1; i++) {
            //每次循环只需比较待排序的元素即可
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    isExchange = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            //判断一次循环中是否发生过交换
            if(!isExchange){
                break;
            }else{
                //若发生过交换，不要忘记将变量重新置为false，以供下次循环使用
                isExchange = false;
            }
        }
    }

    //倒序
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
