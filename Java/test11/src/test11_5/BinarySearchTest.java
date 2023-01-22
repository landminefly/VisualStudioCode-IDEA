package test11_5;

import java.util.ArrayList;
import java.util.Collections;

public class BinarySearchTest {
    public static void main(String[] args) {
        int[] ints = new int[]{11, 22,22,22,22,22, 33, 44, 55, 66, 77, 88, 99};
        System.out.println(BinarySearch.startSearchAll(ints, 22));
    }
}

class BinarySearch {

    public static int startSearch(int[] arr, int searchedNum) {
        return search(arr, 0, arr.length - 1, searchedNum);
    }

    public static int search(int[] arr, int left, int right, int searchedNum) {
        if (left > right) {
            return -1;
        }
        int middle = (left + right) / 2;
        if (arr[middle] > searchedNum) {
            return search(arr, left, middle - 1, searchedNum);
        } else if (arr[middle] < searchedNum) {
            return search(arr, middle + 1, right, searchedNum);
        } else {
            return middle;
        }
    }

    public static ArrayList<Integer> startSearchAll(int[] arr, int searchNum) {
        return searchAll(arr, 0, arr.length - 1, searchNum);
    }
    public static ArrayList<Integer> searchAll(int[] arr, int left, int right, int searchedNum) {
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int middle = (left + right) / 2;
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
