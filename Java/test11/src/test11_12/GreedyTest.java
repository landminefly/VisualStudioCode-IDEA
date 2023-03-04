package test11_12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyTest {
    public static void main(String[] args) {
        //存放所有广播台及其能覆盖的地区，广播台以String的形式存储，其覆盖的地区以HashSet<String>的形式存储
        HashMap<String, HashSet<String>> allBroadcasts = new HashMap<>();

        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        allBroadcasts.put("B1", hashSet1);

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        allBroadcasts.put("B2", hashSet2);

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        allBroadcasts.put("B3", hashSet3);

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        allBroadcasts.put("B4", hashSet4);

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
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

        //存放已经选择的广播，初始化为空集合
        ArrayList<String> broadcastSelected = new ArrayList<>();
        //临时集合，在遍历剩余广播时，存放当前遍历广播的覆盖地区与还没被覆盖地区的交集
        ArrayList<String> temp = new ArrayList<>();

        //在一次遍历中，存放能覆盖最多未覆盖地区的广播台
        String maxBroadcast = null;
        //在一次遍历中，存放上述广播台所覆盖的地区数
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
            //将maxBroadcast加入broadcastSelected中，并将maxBroadcast新覆盖的地区从areasRemained中移除
            broadcastSelected.add(maxBroadcast);
            areasRemained.removeAll(allBroadcasts.get(maxBroadcast));
        }
        System.out.println(broadcastSelected);
    }
}

