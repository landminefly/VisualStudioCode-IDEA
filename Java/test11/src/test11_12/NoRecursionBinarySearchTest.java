package test11_12;

public class NoRecursionBinarySearchTest {
    public static void main(String[] args) {
        int[] ints = {1, 5, 9, 10, 34, 56, 57, 100};
        System.out.println(NoRecursionBinarySearch.search(ints, 10));
    }
}

class NoRecursionBinarySearch {
    //如果有相同元素，只找一个
    public static int search(int[] arr, int key) {
        if (key < arr[0] || key > arr[arr.length - 1]) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] > key) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
