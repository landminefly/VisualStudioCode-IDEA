package test11_4;

import java.util.Arrays;

public class RadixSortTest {
    public static void main(String[] args) {
        int[] ints = new int[10];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 100);
        }
        RadixSort.sortInOrder(ints);
        System.out.println(Arrays.toString(ints));
        RadixSort.sortInReverseOrder(ints);
        System.out.println(Arrays.toString(ints));
    }
}

class RadixSort {
    public static int[][] buckets;
    public static int[] bucketCounts;

    //正序
    public static void sortInOrder(int[] arr) {
        //找到数组中的最大值
        int max = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }
        //计算最大值的位数
        int topDigit = ("" + max).length();

        //准备10个一维数组，也即“桶”，每个一维数组的长度都与待排序数组相同
        buckets = new int[10][arr.length];
        //这个数组用来记录每个“桶”在一次循环中放入的元素数量
        bucketCounts = new int[10];

        //第一次循环获取每个元素的个位，第二次获取十位，直到获取最大值的最高位为止
        for (int i = 0, k = 1; i < topDigit; i++, k *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digit = arr[j] / k % 10;
                //根据获取到的数字，将元素依次放入各个桶中，放入元素后bucketCounts[digit]不要忘了++
                buckets[digit][(bucketCounts[digit])++] = arr[j];
            }

            //元素全部放入桶中后，再按照0-9的顺序，从代表对应数字的桶中依次取出元素，并一一放回原数组中
            //需要一个辅助指针，代表原数组的下标
            int arrIndex = 0;
            for (int m = 0; m < bucketCounts.length; m++) {
                //只有该桶不为空，才从中取出元素
                if (bucketCounts[m] != 0) {
                    for (int n = 0; n < bucketCounts[m]; n++) {
                        arr[arrIndex++] = buckets[m][n];
                    }
                }
                //从桶中取出元素后，不要忘记将该桶的计数置为0，以便下次循环使用
                bucketCounts[m] = 0;
            }
        }
    }

    //倒序
    public static void sortInReverseOrder(int[] arr) {
        int max = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
        }
        int topDigit = ("" + max).length();

        int[][] buckets = new int[10][arr.length];
        int[] bucketCounts = new int[10];

        for (int i = 0, k = 1; i < topDigit; i++, k *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digit = arr[j] / k % 10;
                buckets[digit][(bucketCounts[digit])++] = arr[j];
            }

            //元素全部放入桶中后，再按照9-0的顺序，从代表对应数字的桶中依次取出元素，并一一放回原数组中
            int arrIndex = 0;
            for (int m = bucketCounts.length - 1; m >= 0; m--) {
                if (bucketCounts[m] != 0) {
                    for (int n = 0; n < bucketCounts[m]; n++) {
                        arr[arrIndex++] = buckets[m][n];
                    }
                }
                bucketCounts[m] = 0;
            }
        }
    }
}