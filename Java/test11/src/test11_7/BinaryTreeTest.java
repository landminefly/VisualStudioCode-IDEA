package test11_7;

import org.w3c.dom.Node;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeTest {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode node1 = new TreeNode(7, "安全裤换胖次！");
        TreeNode node2 = new TreeNode(4, "不想喝可乐");
        TreeNode node3 = new TreeNode(2, "超合金男同");
        TreeNode node4 = new TreeNode(9, "反二雅士");
        TreeNode node5 = new TreeNode(0, "某格里芬指挥官");
        TreeNode node6 = new TreeNode(8, "你...你掰我腿干...干嘛呀！");
        TreeNode node7 = new TreeNode(1, "神秘口罩男");
        TreeNode node8 = new TreeNode(3, "想在游戏里振刀");

        binaryTree.root = node1;
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;

        binaryTree.preorder();
        binaryTree.inorder();
        binaryTree.postorder();

        System.out.println(binaryTree.preorderSearch(4));
        System.out.println(binaryTree.inorderSearch(0));
        System.out.println(binaryTree.postorderSearch(5));

        System.out.println(binaryTree.height());
        System.out.println(binaryTree.nodeCount());
        System.out.println(binaryTree.leaveCount());
        binaryTree.BFS();

        // System.out.println(binaryTree.deleteSubtree(8));
        // binaryTree.preorder();
    }
}

class BinaryTree {
    //根节点
    public TreeNode root;

    //前序遍历
    public void preorder() {
        System.out.println("开始输出");
        if (root != null) {
            root.preorder();
        }
        System.out.println("输出完毕");
    }

    //中序遍历
    public void inorder() {
        System.out.println("开始输出");
        if (root != null) {
            root.inorder();
        }
        System.out.println("输出完毕");
    }

    //后序遍历
    public void postorder() {
        System.out.println("开始输出");
        if (root != null) {
            root.postorder();
        }
        System.out.println("输出完毕");
    }

    //广度优先遍历
    //因为是从上到下遍历的，所以无需使用数组isVisited来记录节点是否被访问
    public void BFS() {
        System.out.println("开始输出");
        if (root != null) {
            Queue<TreeNode> q = new LinkedList<>();
            System.out.println(root);
            q.add(root);
            while (!q.isEmpty()) {
                TreeNode temp = q.remove();
                if (temp.left != null) {
                    System.out.println(temp.left);
                    q.add(temp.left);
                }
                if (temp.right != null) {
                    System.out.println(temp.right);
                    q.add(temp.right);
                }
            }
        }
        System.out.println("输出完毕");

    }

    //前序查找
    public TreeNode preorderSearch(int keyId) {
        if (root == null) {
            return null;
        } else {
            return root.preorderSearch(keyId);
        }
    }

    //中序查找
    public TreeNode inorderSearch(int keyId) {
        if (root == null) {
            return null;
        } else {
            return root.inorderSearch(keyId);
        }
    }

    //后序查找
    public TreeNode postorderSearch(int keyId) {
        if (root == null) {
            return null;
        } else {
            return root.postorderSearch(keyId);
        }
    }


    /**
     * 删除子树
     *
     * @return 删除成功返回true，反之返回false
     */
    public boolean deleteSubtree(int keyId) {
        if (root == null) {
            return false;
        } else if (root.id == keyId) {
            root = null;
            return true;
        } else {
            return root.deleteSubtree(keyId);
        }
    }

    //返回二叉树的高度
    public int height() {
        return height(root);
    }

    public int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        //递归计算左右子树的高度
        int lHeight = height(node.left);
        int rHeight = height(node.right);
        return Math.max(lHeight, rHeight) + 1;
    }

    //计算二叉树的节点个数
    public int nodeCount() {
        return nodeCount(root);
    }

    public int nodeCount(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return nodeCount(node.left) + nodeCount(node.right) + 1;
    }

    //计算二叉树的叶子节点个数
    public int leaveCount() {
        return leaveCount(root);
    }

    public int leaveCount(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return leaveCount(node.left) + leaveCount(node.right);
    }
}

class TreeNode {
    //数据段
    public int id;
    public String name;
    //左子节点
    public TreeNode left;
    //右子节点
    public TreeNode right;

    public TreeNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + name;
    }

    //前序遍历
    public void preorder() {
        System.out.println(this);
        if (left != null) {
            left.preorder();
        }
        if (right != null) {
            right.preorder();
        }
    }

    //中序遍历
    public void inorder() {
        if (left != null) {
            left.inorder();
        }
        System.out.println(this);
        if (right != null) {
            right.inorder();
        }
    }

    //后序遍历
    public void postorder() {
        if (left != null) {
            left.postorder();
        }
        if (right != null) {
            right.postorder();
        }
        System.out.println(this);
    }

    //前序查找
    public TreeNode preorderSearch(int keyId) {
        if (id == keyId) {
            return this;
        }
        TreeNode result = null;
        if (left != null) {
            result = left.preorderSearch(keyId);
        }
        if (result != null) {
            return result;
        }
        if (right != null) {
            result = right.preorderSearch(keyId);
        }
        return result;
    }

    //中序查找
    public TreeNode inorderSearch(int keyId) {
        TreeNode result = null;
        if (left != null) {
            result = left.inorderSearch(keyId);
        }
        if (result != null) {
            return result;
        }
        if (id == keyId) {
            return this;
        }
        if (right != null) {
            result = right.inorderSearch(keyId);
        }
        return result;
    }

    //后序查找
    public TreeNode postorderSearch(int keyId) {
        TreeNode result = null;
        if (left != null) {
            result = left.postorderSearch(keyId);
        }
        if (result != null) {
            return result;
        }
        if (right != null) {
            result = right.postorderSearch(keyId);
        }
        if (result != null) {
            return result;
        }
        if (id == keyId) {
            return this;
        }
        return result;
    }

    /**
     * 删除子树
     *
     * @return 删除成功返回true，反之返回false
     */
    public boolean deleteSubtree(int keyId) {
        if (left != null && left.id == keyId) {
            left = null;
            return true;
        }
        if (right != null && right.id == keyId) {
            right = null;
            return true;
        }
        boolean isDeleted = false;
        if (left != null) {
            isDeleted = left.deleteSubtree(keyId);
        }
        if (!isDeleted && right != null) {
            isDeleted = right.deleteSubtree(keyId);
        }
        return isDeleted;
    }
}