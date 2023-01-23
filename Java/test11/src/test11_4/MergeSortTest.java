package test11_4;

import java.util.Arrays;

public class MergeSortTest {
    public static void main(String[] args) {
        int[] ints = new int[10];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * 1000);
        }
        MergeSort.startSortInOrder(ints);
        System.out.println(Arrays.toString(ints));
        MergeSort.startSortInReverseOrder(ints);
        System.out.println(Arrays.toString(ints));
    }
}

class MergeSort {
    //合并&排序时需要一个与原数组等大的临时数组
    public static int[] temp;
    //指向临时数组的辅助指针
    public static int tempIndex;

    //正序
    public static void startSortInOrder(int[] arr) {
        temp = new int[arr.length];
        sortInOrder(arr, 0, arr.length - 1);
    }
    /**
     * @param left 表示该部分的首元素在原数组中的下标
     * @param right 表示该部分的末元素在原数组中的下标
     */
    public static void sortInOrder(int[] arr, int left, int right) {
        //如果这部分只有一个元素，则直接返回
        if (left < right) {
            //以下为分解代码
            int middle = (left + right) / 2;
            //向左递归分解
            sortInOrder(arr, left, middle);
            //向右递归分解
            sortInOrder(arr, middle + 1, right);

            //以下为合并&排序代码
            //先将辅助指针置0，此时它的意义是指向临时数组中的第一个空闲位置
            tempIndex = 0;
            //注意：此时左半部分和右半部分都分别是有序的
            //firstIndex指向左半部分的第一个元素
            int firstIndex = left;
            //secondIndex指向右半部分的第一个元素
            int secondIndex = middle + 1;

            //当左半部分或右半部分的最后一个元素被取出时，结束循环
            while (firstIndex <= middle && secondIndex <= right) {
                //如果firstIndex指向元素 <= secondIndex指向元素，则取出firstIndex指向元素放入临时数组
                //然后firstIndex、tempIndex后移
                if (arr[firstIndex] <= arr[secondIndex]) {
                    temp[tempIndex++] = arr[firstIndex++];
                }
                //如果firstIndex指向元素 > secondIndex指向元素，则取出secondIndex指向元素放入临时数组
                //然后secondIndex、tempIndex后移
                else {
                    temp[tempIndex++] = arr[secondIndex++];
                }
            }

            //如果右半部分的所有元素都被取出导致结束循环
            //则将左半部分的剩余元素放入临时数组
            while (firstIndex <= middle) {
                temp[tempIndex++] = arr[firstIndex++];
            }
            //如果左半部分的所有元素都被取出导致结束循环
            //则将右半部分的剩余元素放入临时数组
            //实际上，执行时只会进入其中一个while的循环体
            while (secondIndex <= right) {
                temp[tempIndex++] = arr[secondIndex++];
            }

            //将临时数组中的元素挪到到原数组中
            //再将辅助指针置0，此时它的意义是辅助元素挪动
            tempIndex = 0;
            //还需要一个辅助变量，表示该部分的元素在原数组中的下标，其意义也是辅助元素挪动
            int arrIndex = left;
            //开始挪动
            while (arrIndex <= right) {
                arr[arrIndex++] = temp[tempIndex++];
            }
        }
    }

    //倒序
    public static void startSortInReverseOrder(int[] arr) {
        temp = new int[arr.length];
        sortInReverseOrder(arr, 0, arr.length - 1);
    }
    public static void sortInReverseOrder(int[] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            sortInReverseOrder(arr, left, middle);
            sortInReverseOrder(arr, middle + 1, right);

            tempIndex = 0;
            int firstIndex = left;
            int secondIndex = middle + 1;

            while (firstIndex <= middle && secondIndex <= right) {
                if (arr[firstIndex] <= arr[secondIndex]) {
                    temp[tempIndex++] = arr[secondIndex++];
                } else {
                    temp[tempIndex++] = arr[firstIndex++];
                }
            }

            while (firstIndex <= middle) {
                temp[tempIndex++] = arr[firstIndex++];
            }
            while (secondIndex <= right) {
                temp[tempIndex++] = arr[secondIndex++];
            }

            tempIndex = 0;
            int arrIndex = left;
            while (arrIndex <= right) {
                arr[arrIndex++] = temp[tempIndex++];
            }
        }
    }
}
