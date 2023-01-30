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

    public Node search(int keyValue) {
        return searchWithParent(keyValue);
    }

    private Node parent;

    private Node searchWithParent(int keyValue) {
        parent = null;
        if (root == null) {
            return null;
        }
        return searchWithParent(root, keyValue);
    }

    private Node searchWithParent(Node node, int keyValue) {
        if (node.value == keyValue) {
            return node;
        } else if (node.value > keyValue) {
            if (node.left == null) {
                return null;
            }
            parent = node;
            return searchWithParent(node.left, keyValue);
        } else {
            if (node.right == null) {
                return null;
            }
            parent = node;
            return searchWithParent(node.right, keyValue);
        }
    }

    private int deleteMinNodeOfRightSubtree(Node node) {
        Node temp = node;
        Node tempParent = node;
        while (temp.left != null) {
            tempParent = temp;
            temp = temp.left;
        }
        tempParent.left = null;
        return temp.value;
    }

    public boolean delete(int keyValue) {
        Node nodeToDelete = searchWithParent(keyValue);
        if (nodeToDelete == null) {
            return false;
        }
        if (nodeToDelete.left == null && nodeToDelete.right == null) {
            if (parent == null) {
                root = null;
            } else {
                if (parent.left != null && parent.left.value == keyValue) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            return true;
        } else if (nodeToDelete.left != null && nodeToDelete.right != null) {
            nodeToDelete.value = deleteMinNodeOfRightSubtree(nodeToDelete);
            return true;
        } else {
            if (nodeToDelete.left != null) {
                if (parent == null) {
                    root = root.left;
                } else {
                    if (parent.left.value == keyValue) {
                        parent.left = nodeToDelete.left;
                    } else {
                        parent.right = nodeToDelete.left;
                    }
                }
            } else {
                if (parent == null) {
                    root = root.right;
                } else {
                    if (parent.left.value == keyValue) {
                        parent.left = nodeToDelete.right;
                    } else {
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

    public static int height(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(node.left == null ? 0 : height(node.left), node.right == null ? 0 : height(node.right)) + 1;
    }

    private void leftRotate() {
        Node newNode = new Node(value);
        newNode.left = left;
        newNode.right = right.left;
        value = right.value;
        left = newNode;
        right = right.right;
    }

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