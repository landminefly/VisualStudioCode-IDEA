package test11_7;

import java.util.Stack;

public class NoRecTraversal {
    public static void main(String[] args) {
        BinaryTree1 binaryTree1 = new BinaryTree1();
        TreeNode1 node1 = new TreeNode1(7, "安全裤换胖次！");
        TreeNode1 node2 = new TreeNode1(4, "不想喝可乐");
        TreeNode1 node3 = new TreeNode1(2, "超合金男同");
        TreeNode1 node4 = new TreeNode1(9, "反二雅士");
        TreeNode1 node5 = new TreeNode1(0, "某格里芬指挥官");
        TreeNode1 node6 = new TreeNode1(8, "你...你掰我腿干...干嘛呀！");
        TreeNode1 node7 = new TreeNode1(1, "神秘口罩男");
        TreeNode1 node8 = new TreeNode1(3, "想在游戏里振刀");

        binaryTree1.root = node1;
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;

        System.out.println("开始前序遍历");
        binaryTree1.noRecPreorder();
        System.out.println("前序遍历结束");

        System.out.println("开始中序遍历");
        binaryTree1.noRecInorder();
        System.out.println("中序遍历结束");

        System.out.println("开始后序遍历");
        binaryTree1.noRecPostorder();
        System.out.println("后序遍历结束");
    }
}

class BinaryTree1 {
    public TreeNode1 root;

    public void noRecPreorder() {
        Stack<TreeNode1> stack = new Stack<>();
        TreeNode1 temp = root;
        while (temp != null || !stack.empty()) {
            while (temp != null) {
                //在入栈时输出该元素
                System.out.println(temp);
                stack.push(temp);
                temp = temp.left;
            }
            if (!stack.empty()) {
                temp = stack.pop();
                temp = temp.right;
            }
        }
    }

    public void noRecInorder() {
        Stack<TreeNode1> stack = new Stack<>();
        TreeNode1 temp = root;
        while (temp != null || !stack.empty()) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
            if (!stack.empty()) {
                //在出栈时输出该元素
                temp = stack.pop();
                System.out.println(temp);
                temp = temp.right;
            }
        }
    }

    public void noRecPostorder() {
        Stack<TreeNode1> stack = new Stack<>();
        TreeNode1 cur;
        TreeNode1 pre = null;
        stack.push(root);
        while (!stack.empty()) {
            cur = stack.peek();
            if ((cur.left == null && cur.right == null) ||
                (pre != null && (cur.left == pre || cur.right == pre))) {
                //在出栈时输出该元素，且必须保证该元素没有左右子节点或者其子节点都被输出过了
                System.out.println(cur);
                stack.pop();
                pre = cur;
            } else {
                //先将右子节点入栈，再将左子节点入栈，这样栈顶元素就是左子节点
                //那么下次循环就先从左子节点开始，保证先左子树后右子树的遍历顺序
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }

        }
    }
}

class TreeNode1 {
    //数据段
    public int id;
    public String name;
    //左子节点
    public TreeNode1 left;
    //右子节点
    public TreeNode1 right;

    public TreeNode1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + name;
    }

}
