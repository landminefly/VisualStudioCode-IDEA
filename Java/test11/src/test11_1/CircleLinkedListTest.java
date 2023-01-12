package test11_1;

public class CircleLinkedListTest {
    public static void main(String[] args) {
        CircleLinkedList link = new CircleLinkedList();
        link.add(new NodeForCircle(1, "Diana"));
        link.add(new NodeForCircle(2, "Ava"));
        link.add(new NodeForCircle(3, "Eileen"));
        link.add(new NodeForCircle(4, "Bella"));
        link.showAll();
        System.out.println(link.count());
    }
}

//单向环形链表，没有头节点
class CircleLinkedList {
    //通过该节点来访问链表，该节点默认是添加的第一个节点
    //但有可能因为第一个节点的删除而发生变化，详见delete()函数体
    NodeForCircle first;

    //两个构造器，自行决定创建链表时是否同时添加第一个节点
    public CircleLinkedList() {}

    public CircleLinkedList(NodeForCircle first) {
        this.first = first;
        first.next = first;
    }

    //将传入节点插入到first之前
    public void add(NodeForCircle newNode) {
        NodeForCircle temp = first;
        if (temp == null) {
            temp = newNode;
            temp.next = temp;
            first = newNode;
            return;
        }
        while (temp.next != first) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    //根据id大小来将传入节点插入到链表指定位置的方法就不写了，基本思想差不多

    //根据传入节点的id来修改链表中的指定节点
    public void update(NodeForCircle updatedNode) {
        NodeForCircle temp = first;
        while (true) {
            if (temp != null && temp.id == updatedNode.id) {
                temp.name = updatedNode.name;
                return;
            } else if (temp == null || temp.next == first) {
                System.out.println("没有对应id的节点，修改失败");
                return;
            }
            temp = temp.next;
        }
    }

    //根据传入的id，删除链表中的指定节点
    //因为是单向链表，所以删除节点仍需要先得到它的前一个节点
    //而且是环形链表，所以获取first的前一个节点有点麻烦，需要先把链表遍历一次
    public void delete(int id) {
        NodeForCircle temp = first;
        NodeForCircle beforeTemp = first;
        //遍历链表，获取first的前一个节点
        while (beforeTemp != null && beforeTemp.next != first) {
            beforeTemp = beforeTemp.next;
        }
        while (true) {
            //空链表/遍历链表后找不到对应节点
            if (temp == null || (temp.next == first && temp.id != id)) {
                System.out.println("没有对应id的节点，删除失败");
                return;
            }
            //只有一个节点first且要删除的节点就是它
            //temp == beforeTemp可以用来判断链表中是否只有一个节点
            else if (temp.id == id && temp == beforeTemp) {
                first = null;
                return;
            }
            //有多个节点且要删除的节点是first
            else if (temp.id == id && temp == first) {
                beforeTemp.next = temp.next;
                first = temp.next;
                return;
            }
            //有多个节点且要删除的节点不是first
            else if (temp.id == id) {
                beforeTemp.next = temp.next;
                return;
            }
            temp = temp.next;
            beforeTemp = beforeTemp.next;
        }
    }

    //返回链表的节点个数
    public int count() {
        NodeForCircle temp = first;
        int count = 0;
        if (temp == null)
            return count;
        while (temp.next != first) {
            count++;
            temp = temp.next;
        }
        return count + 1;
    }

    //打印所有节点，从first开始
    public void showAll() {
        NodeForCircle temp = first;
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }
        while (true) {
            System.out.println(temp);
            if (temp.next == first) {
                return;
            }
            temp = temp.next;
        }
    }
}

//单项环形链表的节点
class NodeForCircle {
    //节点存放的数据
    public int id;
    public String name;
    //节点存放的指针
    public NodeForCircle next;

    public NodeForCircle(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + id + " ,name: " + name;
    }
}