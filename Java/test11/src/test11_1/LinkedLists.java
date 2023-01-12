package test11_1;

import java.util.Stack;

public class LinkedLists {
    public static void main(String[] args) {
        Node s1 = new Node(1, "Diana", 90);
        Node s2 = new Node(2, "Ava", 80);
        Node s3 = new Node(3, "Eileen", 92);
        Node s4 = new Node(4, "Bella", 95);

        LinkedList link = new LinkedList();
        link.insertById(s2);
        link.insertById(s1);
        link.insertById(s3);
        link.insertById(s4);
        reverseList(link);
        link.showAllNodes();
        reversePrint(link);
        System.out.println(findLastIndexNode(link, 3));

    }

    //实现单向链表的反转，要求在原有节点上操作
    public static void reverseList(LinkedList link) {
        if (link.head.next == null || link.head.next.next == null) {
            return;
        }
        //思路是遍历链表，依次将每个节点都插入到临时头节点之后，这样就将原有节点反转了
        //这里创建了一个临时头节点，最后再让原有头节点指向临时头节点指向的节点即可
        Node newHead = new Node(0, null, 0);
        Node temp = link.head.next;
        Node nextNode;
        while (temp != null) {
            nextNode = temp.next;
            temp.next = newHead.next;
            newHead.next = temp;
            temp = nextNode;
        }
        link.head.next = newHead.next;
    }

    //将链表的倒数第index个节点返回
    public static Node findLastIndexNode(LinkedList link, int index) {
        int count = 0;
        Node temp = link.head.next;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        if (index > count) {
            return null;
        }
        temp = link.head.next;
        for (int i = 0; i < (count - index); i++) {
            temp = temp.next;
        }
        return temp;
    }

    //将链表反向打印，这里运用了栈的知识
    public static void reversePrint(LinkedList link) {
        Stack<Node> nodes = new Stack<>();
        Node temp = link.head.next;
        while (temp != null) {
            nodes.push(temp);
            temp = temp.next;
        }
        while (nodes.size() > 0) {
            System.out.println(nodes.pop());
        }
    }
}
