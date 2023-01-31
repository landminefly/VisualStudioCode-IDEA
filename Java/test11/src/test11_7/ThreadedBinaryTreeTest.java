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

    /**
     * 转换为中序线索二叉树
     */
    public void translateToInorder() {
        if (root == null) {
            return;
        } else {
            translateToInorder(root);
        }
    }

    //记录前驱节点，初始化为null
    public ThreadedTreeNode preNode;

    /**
     * 该方法的实质其实就是先中序遍历一次
     * 在遍历的过程中将前驱节点和后继节点赋值给对应节点的左子节点和右子节点（若空）
     *
     * @param node 当前遍历到的节点
     */
    public void translateToInorder(ThreadedTreeNode node) {
        //向左递归
        if (node.left != null) {
            translateToInorder(node.left);
        }

        //如果left为空，则将前驱节点赋值给它，并更改其类型
        if (node.left == null) {
            node.left = preNode;
            node.leftType = 1;
        }

        //因为指针是单向的，所以无法直接获取当前遍历节点的后继节点
        //只有在遍历到其后继节点时，才能通过preNode来设置其后继节点
        //如果preNode的right为空，则将preNode的后继节点（也就是当前遍历节点）赋值给它，并更改其类型
        if (preNode != null && preNode.right == null) {
            preNode.right = node;
            preNode.rightType = 1;
        }
        //在进行下一次递归前，不要忘记设置preNode为当前遍历节点
        preNode = node;

        //向右递归
        if (node.right != null) {
            translateToInorder(node.right);
        }
    }

    /**
     * 中序遍历（无需递归）
     * 要想理解该方法，还得结合中序遍历的特点抽象理解
     */
    public void inorder() {
        ThreadedTreeNode temp = root;
        while (temp != null) {
            //先向左找到第一个left类型为1的节点，这就是中序遍历的第一个遍历节点
            while (temp.leftType != 1) {
                temp = temp.left;
            }
            //先遍历该节点
            System.out.println(temp);
            //然后一直向右遍历，直到遍历完第一个right类型为0的节点
            //该节点有可能是中序遍历的最后一个遍历节点
            //退出该循环后temp = temp.right会将temp赋值为null，从而结束遍历
            while (temp.rightType == 1) {
                temp = temp.right;
                System.out.println(temp);
            }
            //找到该节点的右子树，重复该循环
            temp = temp.right;
        }
    }
}

class ThreadedTreeNode {
    public int id;
    public String name;
    //左指针
    public ThreadedTreeNode left;
    //left的类型，规定：0（默认值）表示left指向左子节点；1表示left指向前驱节点
    public int leftType;
    //右指针
    public ThreadedTreeNode right;
    //right的类型，规定：0（默认值）表示right指向右子节点；1表示right指向后继节点
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
