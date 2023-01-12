package test11_1;

public class LinkedListTest {
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
        link.showAllNodes();
    }
}

//单向链表
class LinkedList {
    public Node head;

    //在构造函数中，先创建一个头节点
    public LinkedList() {
        head = new Node(0, null, 0);
    }

    //将传入节点加入到链表尾部
    public void add(Node newNode) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    //按照id的大小排序，将传入节点插入到链表的指定位置
    //（前提是该链表已存在节点中的id本来就是有序的）
    public void insertById(Node newNode) {
        Node temp = head;
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
        temp.next = newNode;
    }

    //根据传入节点的id，更改链表中相同id节点的数据
    public void update(Node updatedNode) {
        Node temp = head;
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

    //删除指定id节点
    //注意：因为这是单向链表，因此在删除节点时，应该定位到要删除节点的前一个节点
    //如果直接定位到要删除的节点，那就无法找到上一个节点，也就无法完成删除操作
    public void delete(int id) {
        Node temp = head;
        while (true) {
            if (temp.next == null) {
                System.out.println("没有找到对应id节点，删除失败");
                return;
            }
            if (temp.next.id == id) {
                break;
            }
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }

    //打印链表的所有节点
    public void showAllNodes() {
        Node temp = head.next;
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

//单链表节点
class Node {
    //节点存放的数据
    public int id;
    public String name;
    public int score;
    //节点存放的指针，默认为null，在插入到链表中时才赋值
    public Node next;

    public Node(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "id: " + id + " ,name: " + name + " ,score: " + score;
    }
}
