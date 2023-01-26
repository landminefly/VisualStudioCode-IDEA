package test11_6;

public class HashTableTest {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(2);
        hashTable.add(new StudentNode(1, "diana"));
        hashTable.add(new StudentNode(2, "bella"));
        hashTable.add(new StudentNode(4, "eileen"));
        hashTable.add(new StudentNode(3, "ava"));
        hashTable.showAll();
        System.out.println(hashTable.delete(3));
        System.out.println(hashTable.delete(5));
        hashTable.showAll();
        System.out.println(hashTable.search(1));
        System.out.println(hashTable.search(3));
    }
}

//哈希表
class HashTable {
    //底层数组
    public LinkedList[] listArr;
    //数组的长度
    public int maxSize;

    public HashTable(int maxSize) {
        this.maxSize = maxSize;
        listArr = new LinkedList[maxSize];
        //不要忘记先把空链表赋值给每个数组元素
        for (int i = 0; i < listArr.length; i++) {
            listArr[i] = new LinkedList();
        }
    }

    //添加对象
    public void add(StudentNode s) {
        listArr[hashToIndex(s.id)].add(s);
    }

    //删除对象，删除成功返回true，失败返回false
    public boolean delete(int id) {
        return listArr[hashToIndex(id)].delete(id);
    }

    //查找对象，找到返回对象，找不到返回null
    public StudentNode search(int id) {
        return listArr[hashToIndex(id)].search(id);
    }

    //打印所有对象
    public void showAll() {
        for (int i = 0; i < listArr.length; i++) {
            System.out.println("第" + i + "条链表的内容：");
            listArr[i].showAll();
        }
        System.out.println("输出完成");
    }

    //散列函数，这是最简单的把哈希值转换成数组下标的方法
    public int hashToIndex(int hash) {
        return hash % maxSize;
    }
}

//链表对象
class LinkedList {
    //头节点，这里头节点也是存储数据的
    StudentNode head;

    //在该链表上添加节点
    public void add(StudentNode s) {
        if (head == null) {
            head = s;
            return;
        }
        StudentNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = s;
    }

    //在该链表上删除节点
    public boolean delete(int id) {
        if (head == null) {
            return false;
        } else if (head.id == id) {
            head = head.next;
            return true;
        }
        StudentNode temp = head;
        while (temp.next != null) {
            if (temp.next.id == id) {
                temp.next = temp.next.next;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    //在该链表上查找节点
    public StudentNode search(int id) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.id == id) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    //打印该链表的所有节点
    public void showAll() {
        StudentNode temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

//节点对象
class StudentNode {
    //这里假设id是唯一的，因此直接将id值是为节点对象的哈希值，无需重写hashCode()方法了
    int id;
    String name;
    StudentNode next;

    public StudentNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: " + id + " ,name: " + name;
    }
}
