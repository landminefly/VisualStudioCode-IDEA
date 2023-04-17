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
        // System.out.println(binarySortTree.delete(1));
        System.out.println(binarySortTree.delete(2));
        // System.out.println(binarySortTree.delete(3));
        // System.out.println(binarySortTree.delete(4));
        // System.out.println(binarySortTree.delete(5));
        System.out.println(binarySortTree.delete(6));
        // System.out.println(binarySortTree.delete(7));
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

    //插入
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

    //查找指定节点
    public Node search(int keyValue) {
        return searchWithParent(keyValue);
    }

    //父节点指针，用于记录searchWithParent查找到的父节点
    private Node parent;

    /**
     * 查找指定节点及其父节点
     * 可以看到，当且仅当 parent == null 时，表示要找的节点就是根节点
     * 在delete方法中会用到该逻辑
     */
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

    //删除指定节点的右子树中的最小值节点，并返回该最小值
    private int deleteMinNodeOfRightSubtree(Node node) {
        Node temp = node.right;
        Node tempParent = node;
        //如果指定节点的右子树根就是最小值节点，那么它被父节点的右指针指向
        if (temp.left == null) {
            tempParent.right = temp.right;
        } else {
            //其余情况，最小值节点都是被其父节点的左指针指向
            while (temp.left != null) {
                tempParent = temp;
                temp = temp.left;
            }
            tempParent.left = temp.right;
        }
        return temp.value;
    }

    //将指定节点的左子树合并到右子树中
    private void MergeLeftSubtreeToRightSubtree(Node node) {
        Node leftRoot = node.left;
        Node minNode = node.right;
        while (minNode.left != null) {
            minNode = minNode.left;
        }
        minNode.left = leftRoot;
    }

    //删除指定节点，删除成功返回true，反之返回false
    public boolean delete(int keyValue) {
        //先找到该节点及其父节点
        Node nodeToDelete = searchWithParent(keyValue);
        if (nodeToDelete == null) {
            return false;
        }
        //如果待删除节点是叶子节点
        if (nodeToDelete.left == null && nodeToDelete.right == null) {
            //如果是根节点，直接将root置空
            if (parent == null) {
                root = null;
            }
            //否则将父节点指向它的指针置空
            else {
                if (parent.left != null && parent.left.value == keyValue) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            return true;
        }
        //如果待删除节点是分支点，且左右节点都不为空
        else if (nodeToDelete.left != null && nodeToDelete.right != null) {
            //两种方法，二选一

            //1.找到其右子树的最小值节点，删除该最小值节点，再将该最小值赋值给待删除节点
            nodeToDelete.value = deleteMinNodeOfRightSubtree(nodeToDelete);
            return true;

            //2.将其左子树合并到右子树中，并将待删除节点的父节点指向它的指针改为指向它的右子节点即可
            // MergeLeftSubtreeToRightSubtree(nodeToDelete);
            // if (parent.left == nodeToDelete) {
            //     parent.left = nodeToDelete.right;
            // } else {
            //     parent.right = nodeToDelete.right;
            // }
            // return true;
        }
        //如果待删除节点是分支点，且只有一个子节点
        else {
            //如果左子节点不为空
            if (nodeToDelete.left != null) {
                //如果是根节点，将root置为root的左子节点
                if (parent == null) {
                    root = root.left;
                }
                //否则将父节点指向它的指针指向它的左子节点
                else {
                    if (parent.left.value == keyValue) {
                        parent.left = nodeToDelete.left;
                    } else {
                        parent.right = nodeToDelete.left;
                    }
                }
            }
            //如果右子节点不为空
            else {
                //如果是根节点，将root置为root的右子节点
                if (parent == null) {
                    root = root.right;
                }
                //否则将父节点指向它的指针指向它的右子节点
                else {
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
