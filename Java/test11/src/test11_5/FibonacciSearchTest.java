package test11_5;

import java.util.Arrays;

public class FibonacciSearchTest {
    public static void main(String[] args) {
        int[] ints = {1, 3, 5, 8, 10};
        System.out.println(FibonacciSearch.startSearch(ints, 70));
    }
}

class FibonacciSearch {
    //待查找数组的长度
    public static int arrLength;
    //存储斐波那契数的数列
    public static int[] f = f();
    //指向斐波那契数列的辅助指针
    public static int fIndex;

    //获取前20个斐波那契数并放在数组中
    public static int[] f() {
        int[] f = new int[20];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < 20; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    public static int startSearch(int[] arr, int searchedNum) {
        //如果要找的数比数组的最小值更小，或最大值更大，那么肯定找不到，直接返回-1
        if (searchedNum < arr[0] || searchedNum > arr[arr.length - 1]) {
            return -1;
        }
        //记录待查找数组的长度
        arrLength = arr.length;
        //找到第一个符合要求的斐波那契数f[fIndex]，f[fIndex]-1 >= 待查找数组长度
        fIndex = 0;
        while (arrLength > f[fIndex] - 1) {
            fIndex++;
        }
        //将待查找数组复制到一个临时数组中，临时数组的长度就是上面找到的f[fIndex]-1
        //如果临时数组的长度大于待查找数组，则大于的部分用待查找数组的最后一个元素填充
        int[] temp = Arrays.copyOf(arr, f[fIndex]);
        for (int i = arrLength; i < temp.length; i++) {
            temp[i] = arr[arrLength - 1];
        }
        //开始对临时数组进行递归
        return search(temp, 0, temp.length - 1, searchedNum);
    }
    /**
     * @param left  表示区间起点在原数组中的下标
     * @param right 表示区间终点在原数组中的下标
     */
    public static int search(int[] temp, int left, int right, int searchedNum) {
        if (left > right) {
            return -1;
        }
        //区间长度是f[fIndex]-1，middle下标分成的左半区间长度是 f[fIndex-1]-1；右半区间长度是 f[fIndex-2]-1
        //因此，f[fIndex]-1（区间长度）= f[fIndex-1]-1（左半区间）+ f[fIndex-2]-1（右半区间）+ 1（middle元素）
        //符合斐波那契数 f[fIndex] = f[fIndex-1] + f[fIndex-2] 的规则
        int middle = left + f[fIndex - 1] - 1;
        if (temp[middle] > searchedNum) {
            //向左递归前，不要忘记先将 fIndex -= 1
            fIndex -= 1;
            return search(temp, left, middle - 1, searchedNum);
        } else if (temp[middle] < searchedNum) {
            //向右递归前，不要忘记先将 fIndex -= 2
            fIndex -= 2;
            return search(temp, middle + 1, right, searchedNum);
        } else {
            //找到后不要立即返回，先判断
            //如果下标 大于 待查找数组的最大下标，说明找到的是临时数组大于待查找数组的部分
            //又因为临时数组大于待查找数组的部分是用后者的最大下标元素填充的，所以返回待查找数组的最大下标即可
            if (arrLength <= middle) {
                return arrLength - 1;
            }
            //如果下标 小于等于 待查找数组的最大下标，直接返回即可
            else {
                return middle;
            }
        }
    }
}
