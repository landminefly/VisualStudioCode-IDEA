package test11_7;

public class ArrayBinaryTreeTest {
    public static void main(String[] args) {

        int[] ints = {7, 4, 2, 9, 0, 8, 1, 3};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(ints);
        arrayBinaryTree.preOrder();
        arrayBinaryTree.inOrder();
        arrayBinaryTree.postOrder();
    }
}

class ArrayBinaryTree {
    public int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        System.out.println("输出开始");
        if (arr == null || arr.length == 0) {
            return;
        }
        preOrder(0);
        System.out.println("输出结束");
    }

    public void preOrder(int index) {
        System.out.println(arr[index]);
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

    public void inOrder() {
        System.out.println("输出开始");
        if (arr == null || arr.length == 0) {
            return;
        }
        inOrder(0);
        System.out.println("输出结束");
    }

    public void inOrder(int index) {
        if ((index * 2 + 1) < arr.length) {
            inOrder(index * 2 + 1);
        }
        System.out.println(arr[index]);
        if ((index * 2 + 2) < arr.length) {
            inOrder(index * 2 + 2);
        }
    }

    public void postOrder() {
        System.out.println("输出开始");
        if (arr == null || arr.length == 0) {
            return;
        }
        postOrder(0);
        System.out.println("输出结束");
    }

    public void postOrder(int index) {
        if ((index * 2 + 1) < arr.length) {
            postOrder(index * 2 + 1);
        }
        if ((index * 2 + 2) < arr.length) {
            postOrder(index * 2 + 2);
        }
        System.out.println(arr[index]);
    }
}
