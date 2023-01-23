package test11_5;

import java.util.ArrayList;
import java.util.Collections;

public class InterpolationSearchTest {
    public static void main(String[] args) {
        int[] ints = {1, 2,2,2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(InterpolationSearch.startSearch(ints, 100));
    }
}

class InterpolationSearch {
    //如果有相同元素，只找一个
    public static int startSearch(int[] arr, int searchedNum) {
        return search(arr, 0, arr.length - 1, searchedNum);
    }
    public static int search(int[] arr, int left, int right, int searchedNum) {
        //注意：if内的第二、第三个条件不可忽略，因为searchedNum直接参与到middle下标值的计算中
        //如果searchedNum过大或过小，可能导致计算出的middle下标越界！
        if (left > right || searchedNum < arr[0] || searchedNum > arr[arr.length - 1]) {
            return -1;
        }

        //与二分查找的主要区别就是，计算middle下标的表达式不同
        int middle = left + (right - left) * (searchedNum - arr[left]) / (arr[right] - arr[left]);

        if (arr[middle] > searchedNum) {
            return search(arr, left, middle - 1, searchedNum);
        } else if (arr[middle] < searchedNum) {
            return search(arr, middle + 1, right, searchedNum);
        } else {
            return middle;
        }
    }

    //如果有相同元素，找到全部
    public static ArrayList<Integer> startSearchAll(int[] arr, int searchNum) {
        return searchAll(arr, 0, arr.length - 1, searchNum);
    }
    public static ArrayList<Integer> searchAll(int[] arr, int left, int right, int searchedNum) {
        if (left > right || searchedNum < arr[0] || searchedNum > arr[arr.length - 1]) {
            return new ArrayList<Integer>();
        }
        int middle = left + (right - left) * (searchedNum - arr[left]) / (arr[right] - arr[left]);
        if (arr[middle] > searchedNum) {
            return searchAll(arr, left, middle - 1, searchedNum);
        } else if (arr[middle] < searchedNum) {
            return searchAll(arr, middle + 1, right, searchedNum);
        } else {
            ArrayList<Integer> nums = new ArrayList<>();
            nums.add(middle);
            int tempIndex = middle - 1;
            while (tempIndex >= 0 && arr[tempIndex] == searchedNum) {
                nums.add(tempIndex--);
            }
            tempIndex = middle + 1;
            while (tempIndex < arr.length && arr[tempIndex] == searchedNum) {
                nums.add(tempIndex++);
            }
            Collections.sort(nums);
            return nums;
        }
    }
}
