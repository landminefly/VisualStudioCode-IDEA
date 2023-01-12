package test11_1;

import java.util.ArrayList;

public class JosephusProblem {
    public static void main(String[] args) {
        CircleLinkedList link = new CircleLinkedList(new NodeForCircle(1, "Diana"));
        link.add(new NodeForCircle(2, "Ava"));
        link.add(new NodeForCircle(3, "Eileen"));
        link.add(new NodeForCircle(4, "Bella"));
        // link.add(new NodeForCircle(5, "Carol"));
        ArrayList<Integer> solve = solve(link, 1, 100086);
        for(int i : solve){
            System.out.println(i);
        }
    }

    //链表中的一个节点就代表一个人，first节点就代表编号为1的人
    public static ArrayList<Integer> solve(CircleLinkedList link,int startId, int countNum){
        ArrayList<Integer> order = new ArrayList<>();
        //如果链表的节点数小于startId，说明传入的参数有问题
        if(link.count() < startId){
            return null;
        }
        //空链表直接返回
        if(link.first == null){
            return null;
        }
        //先获取first和它的前一个节点
        NodeForCircle temp = link.first;
        NodeForCircle beforeTemp = link.first;
        while (beforeTemp.next != link.first) {
            beforeTemp = beforeTemp.next;
        }

        //先到达startId所指定的节点
        for(int i = 1; i<startId; i++){
            temp = temp.next;
            beforeTemp = beforeTemp.next;
        }

        //每次计数时都从1开始，因此temp实际上只需移动 countNum - 1 次（不包括删除节点时的移动）
        //每向后移动 countNum - 1 次，就删除temp所指节点，代表该人被淘汰
        //直至链表中只剩1个节点
        while(temp != beforeTemp){
            for(int i = 0; i<countNum-1; i++){
                temp = temp.next;
                beforeTemp = beforeTemp.next;
            }
            order.add(temp.id);
            //注意，删除节点时，temp也要后移
            temp = temp.next;
            beforeTemp.next = temp;
        }
        //再将最终剩下的节点id记录下来，并返回
        order.add(temp.id);
        return order;
    }
}
