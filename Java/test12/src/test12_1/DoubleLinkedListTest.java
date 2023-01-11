package test12_1;

public class DoubleLinkedListTest {
    public static void main(String[] args) {
        DoubleNode s1 = new DoubleNode(1, "Diana", 90);
        DoubleNode s2 = new DoubleNode(2, "Ava", 80);
        DoubleNode s3 = new DoubleNode(3, "Eileen", 92);
        DoubleNode s4 = new DoubleNode(4, "Bella", 95);
        DoubleLinkedList link = new DoubleLinkedList();
        link.insertById(s1);
        link.insertById(s3);
        link.insertById(s2);
        link.insertById(s4);
        link.showAllNodes();
    }
}

class DoubleLinkedList {
    public DoubleNode head;

    public DoubleLinkedList() {
        head = new DoubleNode(0, null, 0);
    }

    public void add(DoubleNode newNode) {
        DoubleNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.previous = temp;
    }

    public void insertById(DoubleNode newNode) {
        DoubleNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.id > newNode.id) {
                break;
            }
            if (temp.next.id == newNode.id) {
                System.out.println("存在重复id，插入失败");
                return;
            }
            temp = temp.next;
        }
        newNode.next = temp.next;
        if (temp.next != null) {
            temp.next.previous = newNode;
        }
        temp.next = newNode;
        newNode.previous = temp;
    }

    public void update(DoubleNode updatedNode) {
        DoubleNode temp = head;
        while (true) {
            if (temp.next == null) {
                System.out.println("没有对应id的节点，修改失败");
                return;
            }
            if (temp.next.id == updatedNode.id) {
                temp = temp.next;
                break;
            }
            temp = temp.next;
        }
        temp.name = updatedNode.name;
        temp.score = updatedNode.score;
    }

    public void delete(int id) {
        DoubleNode temp = head;
        while (true) {
            if (temp.next == null) {
                System.out.println("没有找到对应id节点，删除失败");
                return;
            }
            if (temp.next.id == id) {
                temp = temp.next;
                break;
            }
            temp = temp.next;
        }
        temp.previous.next = temp.next;
        if(temp.next != null)
        {
            temp.next.previous = temp.previous;
        }
    }

    public void showAllNodes() {
        DoubleNode temp = head.next;
        if (temp == null) {
            System.out.println("链表为空！");
            return;
        }
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class DoubleNode {
    public int id;
    public String name;
    public int score;
    public DoubleNode next;
    public DoubleNode previous;

    public DoubleNode(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "id: " + id + " ,name: " + name + " ,score: " + score;
    }
}
