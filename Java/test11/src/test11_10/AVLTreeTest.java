package test11_10;

public class AVLTreeTest {
    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        AVLTree AVLTree = new AVLTree();
        for (int i : ints) {
            AVLTree.add(new Node(i));
        }
        AVLTree.inorder();
        System.out.println(Node.height(AVLTree.root.left));
        System.out.println(Node.height(AVLTree.root.right));
    }
}

class AVLTree {
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

    //计算以node为树根的子树的树高
    public static int height(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(node.left == null ? 0 : height(node.left), node.right == null ? 0 : height(node.right)) + 1;
    }

    //左旋转
    private void leftRotate() {
        Node newNode = new Node(value);
        newNode.left = left;
        newNode.right = right.left;
        value = right.value;
        left = newNode;
        right = right.right;
    }

    //右旋转
    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.left = left.right;
        newNode.right = right;
        value = left.value;
        left = left.left;
        right = newNode;
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

        //在添加节点后的回溯过程中，自下而上依次对各子树进行检查和旋转
        if (height(left) - height(right) > 1) {
            if (left != null && height(left.right) > height(left.left)) {
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
            }
            return;
        }

        if (height(right) - height(left) > 1) {
            if (right != null && height(right.left) > height(right.right)) {
                right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
        }
    }
}