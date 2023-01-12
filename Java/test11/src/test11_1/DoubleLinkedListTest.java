package test11_1;

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

//双向链表
class DoubleLinkedList {
    public DoubleNode head;

    //在构造函数中，先创建一个头节点
    public DoubleLinkedList() {
        head = new DoubleNode(0, null, 0);
    }

    //将传入节点加入到链表尾部
    public void add(DoubleNode newNode) {
        DoubleNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.previous = temp;
    }

    //按照id的大小排序，将传入节点插入到链表的指定位置
    //（前提是该链表已存在节点中的id本来就是有序的）
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
        //注意：当传入节点插入到链表末尾时，temp.next为空
        //而在单向链表中，因为不存在previous指针，所以无需考虑这个问题
        if (temp.next != null) {
            temp.next.previous = newNode;
        }
        temp.next = newNode;
        newNode.previous = temp;
    }

    //根据传入节点的id，更改链表中相同id节点的数据
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

    //删除指定id节点
    //因为这是双向链表，因此在删除节点时，可以直接定位到要删除节点本身
    //然后找到它的上一个节点和下一个节点，并完成删除操作
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
        //注意：当要删除节点位于链表末尾时，temp.next为空
        //而在单向链表中，因为不存在previous指针，所以无需考虑这个问题
        if(temp.next != null)
        {
            temp.next.previous = temp.previous;
        }
    }

    //打印链表的所有节点
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

//双向链表节点
class DoubleNode {
    //节点存放的数据
    public int id;
    public String name;
    public int score;
    //节点存放的指针，默认为null，在插入到链表中时才赋值
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
