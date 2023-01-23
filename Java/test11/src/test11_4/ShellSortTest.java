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
            //从第一个小数组的第二个元素开始，对第一个数组执行插入排序的第一个循环
            //接下来 i++
                //如果 gap >= 2，那么进入第二个小数组的第二个元素，对第二个小数组执行插入排序的第一个循环
                //如果 gap == 1，那么进入第一个小数组的第三个元素，对第一个小数组执行插入排序的第二个循环
            //再次 i++
                //如果 gap >= 3，...
                //如果 gap == 2，...
                //如果 gap == 1，...
            //...
            //以此类推，最终的结果就是，所有小数组都分别执行了一次插入排序
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
