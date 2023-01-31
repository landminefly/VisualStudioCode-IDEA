package test11_8;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTreeTest {
    public static void main(String[] args) {
        int[] ints = {1, 3, 6, 7, 9, 5, 1};
        HuffmanTree huffmanTree = new HuffmanTree(ints);
        huffmanTree.preorder();
    }
}

class HuffmanTree {
    public Node root;

    //构造赫夫曼树
    public HuffmanTree(int[] arr) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            //从集合中选出两棵权最小的二叉树，合并成新的二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            //从集合中删除两个旧的二叉树
            nodes.remove(0);
            nodes.remove(0);
            //并加入新的二叉树
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
}

//需要实现Comparable接口
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
