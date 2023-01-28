package test11_8;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTreeTest {
    public static void main(String[] args) {
        int[] ints = {1, 3, 6, 7, 9, 5, 1};
        HuffmanTree huffmanTree = new HuffmanTree(ints);
        huffmanTree.preorder();
        huffmanTree.inorder();
        huffmanTree.postorder();
    }
}

class HuffmanTree {
    public Node root;

    public HuffmanTree(int[] arr) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(0);
            nodes.remove(0);
            nodes.add(parent);
        }
        root = nodes.get(0);
    }

    public void preorder() {
        System.out.println("开始输出");
        if (root == null) {
            return;
        } else {
            root.preorder();
        }
        System.out.println("输出完毕");
    }

    public void inorder() {
        System.out.println("开始输出");
        if (root == null) {
            return;
        } else {
            root.inorder();
        }
        System.out.println("输出完毕");
    }
    public void postorder() {
        System.out.println("开始输出");
        if (root == null) {
            return;
        } else {
            root.postorder();
        }
        System.out.println("输出完毕");
    }
}

class Node implements Comparable<Node> {
    public int weight;
    public Node left;
    public Node right;

    public void preorder() {
        System.out.println(this);
        if (left != null) {
            left.preorder();
        }
        if (right != null) {
            right.preorder();
        }
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

    public void postorder() {
        if (left != null) {
            left.postorder();
        }
        if (right != null) {
            right.postorder();
        }
        System.out.println(this);
    }

    public Node(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "weight: " + weight;
    }

    @Override
    public int compareTo(Node o) {
        return weight - o.weight;
    }
}
