package test11_7;

public class ThreadedBinaryTreeTest {
    public static void main(String[] args) {
        ThreadedBinaryTree binaryTree = new ThreadedBinaryTree();
        ThreadedTreeNode node1 = new ThreadedTreeNode(7, "安全裤换胖次！");
        ThreadedTreeNode node2 = new ThreadedTreeNode(4, "不想喝可乐");
        ThreadedTreeNode node3 = new ThreadedTreeNode(2, "超合金男同");
        ThreadedTreeNode node4 = new ThreadedTreeNode(9, "反二雅士");
        ThreadedTreeNode node5 = new ThreadedTreeNode(0, "某格里芬指挥官");
        ThreadedTreeNode node6 = new ThreadedTreeNode(8, "你...你掰我腿干...干嘛呀！");
        ThreadedTreeNode node7 = new ThreadedTreeNode(1, "神秘口罩男");
        ThreadedTreeNode node8 = new ThreadedTreeNode(3, "想在游戏里振刀");

        binaryTree.root = node1;
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;

        binaryTree.translateToInorder();
        binaryTree.inorder();
    }
}

class ThreadedBinaryTree {
    public ThreadedTreeNode root;
    public ThreadedTreeNode preNode;

    public void translateToInorder() {
        if (root == null) {
            return;
        } else {
            translateToInorder(root);
        }
    }

    public void translateToInorder(ThreadedTreeNode node) {
        if (node.left != null) {
            translateToInorder(node.left);
        }

        if (node.left == null) {
            node.left = preNode;
            node.leftType = 1;
        }
        if (preNode != null && preNode.right == null) {
            preNode.right = node;
            preNode.rightType = 1;
        }
        preNode = node;

        if (node.right != null) {
            translateToInorder(node.right);
        }
    }

    public void inorder() {
        ThreadedTreeNode temp = root;
        while (temp != null) {
            while (temp.leftType != 1) {
                temp = temp.left;
            }
            System.out.println(temp);
            while (temp.rightType == 1) {
                temp = temp.right;
                System.out.println(temp);
            }
            temp = temp.right;
        }
    }
}

class ThreadedTreeNode {
    public int id;
    public String name;
    public ThreadedTreeNode left;
    public int leftType;
    public ThreadedTreeNode right;
    public int rightType;

    public ThreadedTreeNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + name;
    }
}
