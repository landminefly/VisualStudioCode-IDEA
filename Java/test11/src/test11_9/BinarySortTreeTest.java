package test11_9;

import java.util.ArrayList;

public class BinarySortTreeTest {
    public static void main(String[] args) {
        int[] ints = {5, 7, 6, 1, 0, 3, 4, 2, 8};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i : ints) {
            binarySortTree.add(new Node(i));
        }
        binarySortTree.inorder();
        // System.out.println(binarySortTree.searchWithParent(10));
        // System.out.println(binarySortTree.parent);
        System.out.println(binarySortTree.search(8));

        System.out.println(binarySortTree.delete(0));
        System.out.println(binarySortTree.delete(1));
        System.out.println(binarySortTree.delete(2));
        System.out.println(binarySortTree.delete(3));
        System.out.println(binarySortTree.delete(4));
        System.out.println(binarySortTree.delete(5));
        System.out.println(binarySortTree.delete(6));
        System.out.println(binarySortTree.delete(7));
        System.out.println(binarySortTree.delete(8));
        System.out.println(binarySortTree.delete(9));

        binarySortTree.inorder();
    }
}

class BinarySortTree {
    public Node root;

    public void inorder() {
        System.out.println("开始输出");
        if (root != null) {
            root.inorder();
        }
        System.out.println("输出完毕");
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public Node search(int keyValue){
        return searchWithParent(keyValue);
    }

    private Node parent;
    private Node searchWithParent(int keyValue) {
        parent = null;
        if (root == null) {
            return null;
        }
        return searchWithParent(root,keyValue);
    }

    private Node searchWithParent(Node node, int keyValue) {
        if(node.value == keyValue){
            return node;
        } else if (node.value > keyValue) {
            if(node.left == null){
                return null;
            }
            parent = node;
            return searchWithParent(node.left,keyValue);
        }else{
            if (node.right == null) {
                return null;
            }
            parent = node;
            return searchWithParent(node.right,keyValue);
        }
    }

    private int deleteMinNodeOfRightSubtree(Node node){
        Node temp = node;
        Node tempParent = node;
        while(temp.left != null){
            tempParent = temp;
            temp = temp.left;
        }
        tempParent.left = null;
        return temp.value;
    }

    public boolean delete(int keyValue){
        Node nodeToDelete = searchWithParent(keyValue);
        if(nodeToDelete == null){
            return false;
        }
        if(nodeToDelete.left == null && nodeToDelete.right == null){
            if(parent == null){
                root = null;
            }else{
                if (parent.left != null && parent.left.value == keyValue) {
                    parent.left = null;
                }else{
                    parent.right = null;
                }
            }
            return true;
        }else if(nodeToDelete.left != null && nodeToDelete.right != null){
            nodeToDelete.value = deleteMinNodeOfRightSubtree(nodeToDelete);
            return true;
        }else{
            if(nodeToDelete.left != null){
                if (parent == null) {
                    root = root.left;
                }else{
                    if(parent.left.value == keyValue){
                        parent.left = nodeToDelete.left;
                    }else{
                        parent.right = nodeToDelete.left;
                    }
                }
            }else{
                if(parent == null){
                    root = root.right;
                }else{
                    if (parent.left.value == keyValue) {
                        parent.left = nodeToDelete.right;
                    }else{
                        parent.right = nodeToDelete.right;
                    }
                }
            }
            return true;
        }
    }
}

class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "value: " + value;
    }

    public void inorder() {
        if (left != null) {
            left.inorder();
        }
        System.out.println(this);
        if (right != null) {
            right.inorder();
        }
    }

    public void add(Node node) {
        if (value > node.value) {
            if (left != null) {
                left.add(node);
            } else {
                left = node;
            }
        } else {
            if (right != null) {
                right.add(node);
            } else {
                right = node;
            }
        }
    }
}
