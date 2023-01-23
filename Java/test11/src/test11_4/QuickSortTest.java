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

    //正序
    public static void startSortInOrder(int[] arr) {
        sortInOrder(arr, 0, arr.length - 1);
    }
    /**
     * @param left 表示该部分的首元素在原数组中的下标
     * @param right 表示该部分的末元素在原数组中的下标
     */
    public static void sortInOrder(int[] arr, int left, int right) {
        //如果这个部分只有一个元素或没有元素，则结束这部分的递归
        if (left >= right) {
            return;
        }
        //设置两个指针，leftIndex指向该部分的最左端，rightIndex指向该部分的最右端
        int leftIndex = left;
        int rightIndex = right;
        //取数组或该部分的首元素为基准元素
        int pivot = left;
        //当 leftIndex 与 rightIndex 相遇时，结束while循环
        //while循环体中：
            //leftIndex向右移动，遇到比基准元素大的元素 或 碰到rightIndex时停下
            //rightIndex向左移动，遇到比基准元素小的元素 或 碰到leftIndex时停下
            //随后将leftIndex和rightIndex所指向的元素进行交换
        while (leftIndex < rightIndex) {
            //注意：如果以首元素作为基准元素，那么rightIndex的移动必须写在leftIndex的移动之前
            //否则退出循环时，leftIndex（或rightIndex，因为两个指针此时已经相遇）所指向的元素可能大于基准元素
            //从而导致在将基准元素与leftIndex（或rightIndex）所指向的元素进行交换后，
            //首元素可能大于基准元素，违背了基准元素的左侧都比它小（或相等）的规则

            //同样地，如果以末元素作为基准元素，那么leftIndex的移动必须写在rightIndex的移动之前

            //而对于以其他位置的元素作为基准元素的情况，代码的变化不小，因此这里不再赘述
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
        //最后再将基准元素与leftIndex（或rightIndex）所指向的元素进行交换
        //从而实现基准元素的左侧都比它小（或相等），右侧都比它大（或相等）
        temp = arr[pivot];
        arr[pivot] = arr[leftIndex];
        arr[leftIndex] = temp;

        //向左递归
        sortInOrder(arr, left, leftIndex - 1);
        //向右递归
        sortInOrder(arr, leftIndex + 1, right);
    }

    //倒序
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
