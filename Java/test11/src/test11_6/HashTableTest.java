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

class HashTable {
    public LinkedList[] listArr;
    public int maxSize;

    public HashTable(int maxSize) {
        this.maxSize = maxSize;
        listArr = new LinkedList[maxSize];
        for (int i = 0; i < listArr.length; i++) {
            listArr[i] = new LinkedList();
        }
    }

    public void add(StudentNode s) {
        listArr[hashToIndex(s.id)].add(s);
    }

    public boolean delete(int id) {
        return listArr[hashToIndex(id)].delete(id);
    }

    public StudentNode search(int id) {
        return listArr[hashToIndex(id)].search(id);
    }

    public void showAll() {
        for (int i = 0; i < listArr.length; i++) {
            System.out.println("第" + i + "条链表的内容：");
            listArr[i].showAll();
        }
        System.out.println("输出完成");
    }

    //最简单的把哈希值转换成数组下标的方法
    public int hashToIndex(int hash) {
        return hash % maxSize;
    }
}

class LinkedList {
    StudentNode head;

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

    public void showAll() {
        StudentNode temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class StudentNode {
    //这里假设id是唯一的，因此id值就可以看成是哈希值
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
