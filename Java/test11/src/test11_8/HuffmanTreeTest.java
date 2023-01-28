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
    public HuffmanNode root;

    public HuffmanTree(int[] arr) {
        ArrayList<HuffmanNode> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new HuffmanNode(i));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            HuffmanNode leftNode = nodes.get(0);
            HuffmanNode rightNode = nodes.get(1);
            HuffmanNode parent = new HuffmanNode(leftNode.weight + rightNode.weight);
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

class HuffmanNode implements Comparable<HuffmanNode> {
    public int weight;
    public HuffmanNode left;
    public HuffmanNode right;

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

    public HuffmanNode(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "weight: " + weight;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return weight - o.weight;
    }
}
