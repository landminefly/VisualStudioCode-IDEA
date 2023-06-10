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

    //正序
    public static void sortInOrder(int[] arr) {
        //每次循环开始前，都会先将 gap 减半，直到 gap > 0 结束循环
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //每趟循环，都对各个小数组进行了一次完整的插入排序
            //每趟循环的第一次插入排序应该从第一个小数组的第二个元素开始
            for (int i = gap; i < arr.length; i++) {
                insertIndex = i;
                insertValue = arr[i];
                //注意：小数组中上一个元素的下标应该是 insertIndex - gap 而不是 insertIndex - 1
                if (arr[insertIndex] < arr[insertIndex - gap]) {
                    //注意：小数组中首元素的下标也不是 0 了，应该通过 insertIndex - gap < 0 来判断是否到达小数组的首元素
                    while (insertIndex - gap >= 0 && insertValue < arr[insertIndex - gap]) {
                        arr[insertIndex] = arr[insertIndex - gap];
                        insertIndex -= gap;
                    }
                    arr[insertIndex] = insertValue;
                }
            }
        }
    }

    //倒序
    public static void sortInReverseOrder(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                insertIndex = i;
                insertValue = arr[i];
                if (arr[insertIndex] > arr[insertIndex - gap]) {
                    while (insertIndex - gap >= 0 && insertValue > arr[insertIndex - gap]) {
                        arr[insertIndex] = arr[insertIndex - gap];
                        insertIndex -= gap;
                    }
                    arr[insertIndex] = insertValue;
                }
            }
        }
    }
}
