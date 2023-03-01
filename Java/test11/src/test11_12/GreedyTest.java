package test11_12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyTest {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> allBroadcasts = new HashMap<>();

        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        allBroadcasts.put("B1", hashSet1);
        allBroadcasts.put("B2", hashSet2);
        allBroadcasts.put("B3", hashSet3);
        allBroadcasts.put("B4", hashSet4);
        allBroadcasts.put("B5", hashSet5);

        //存放还没被覆盖的地区，初始化包含全部地区
        HashSet<String> areasRemained = new HashSet<>();
        areasRemained.add("北京");
        areasRemained.add("上海");
        areasRemained.add("天津");
        areasRemained.add("广州");
        areasRemained.add("深圳");
        areasRemained.add("成都");
        areasRemained.add("杭州");
        areasRemained.add("大连");

        //存放已经选择的广播
        ArrayList<String> broadcastSelected = new ArrayList<>();
        //临时集合，在遍历剩余广播时，存放该广播覆盖地区与还没被覆盖的地区的交集
        ArrayList<String> temp = new ArrayList<>();

        String maxBroadcast = null;
        int maxAreaCount = 0;
        while (areasRemained.size() != 0) {
            maxBroadcast = null;
            for (String b : allBroadcasts.keySet()) {
                temp.clear();
                temp.addAll(allBroadcasts.get(b));
                temp.retainAll(areasRemained);
                if (temp.size() > 0 && (maxBroadcast == null || temp.size() > maxAreaCount)) {
                    maxBroadcast = b;
                    maxAreaCount = temp.size();
                }
            }
            //可能存在遍历一次后，没有任何一个广播能覆盖到任意未覆盖到的地区
            //此时可以尝试通过修改第一次选择的广播等方式解决，这里就不涉及了
            if (maxBroadcast == null) {
                throw new RuntimeException("emm,贪心算法好像出错了");
            }
            broadcastSelected.add(maxBroadcast);
            areasRemained.removeAll(allBroadcasts.get(maxBroadcast));
        }
        System.out.println(broadcastSelected);
    }
}

