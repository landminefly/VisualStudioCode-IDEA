package test11_4;

import java.util.Arrays;

public class InsertionSortTest {
    public static void main(String[] args) {
        int[] ints = new int[10];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 100);
        }
        InsertionSort.sortInOrder(ints);
        System.out.println(Arrays.toString(ints));
        InsertionSort.sortInReverseOrder(ints);
        System.out.println(Arrays.toString(ints));
    }
}

class InsertionSort {
    public static int insertIndex;
    public static int insertValue;

    //正序
    public static void sortInOrder(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //记录待插入元素的下标和值
            insertIndex = i;
            insertValue = arr[i];
            //如果 待插入元素 >= 有序部分的最后一个元素，那么插入的步骤都可以省去
            if (arr[insertIndex] < arr[insertIndex - 1]) {
                //在空位到达合适位置之前，一直前移
                while (insertIndex > 0 && insertValue < arr[insertIndex - 1]) {
                    arr[insertIndex] = arr[insertIndex - 1];
                    insertIndex--;
                }
                //空位到达合适位置后，将待插入元素插入到空位中
                arr[insertIndex] = insertValue;
            }
        }
    }

    //倒序
    public static void sortInReverseOrder(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            insertIndex = i;
            insertValue = arr[i];
            if (arr[insertIndex] > arr[insertIndex - 1]) {
                while (insertIndex > 0 && insertValue > arr[insertIndex - 1]) {
                    arr[insertIndex] = arr[insertIndex - 1];
                    insertIndex--;
                }
                arr[insertIndex] = insertValue;
            }
        }
    }
}
