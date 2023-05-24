package test12_3;

import com.google.gson.Gson;
import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost01",6379);

        // HashMap<String,String> hashMap = new HashMap<>();
        // hashMap.put("name","diana");
        // hashMap.put("age","18");
        // hashMap.put("score","88");
        // jedis.hset("myKey",hashMap);

        // jedis.del("myKey");

        String sql = "SELECT * FROM t1";
        // String data1 = "{name:\"diana\",age:18,score:88}";
        // String data2 = "{name:\"ava\",age:19,score:66}";
        // jedis.lpush(sql,data1,data2);

        // jedis.del(sql);

        List<String> lrange = jedis.lrange(sql, 0, -1);
        for(String str : lrange){
            Gson gson = new Gson();
            Bean bean = gson.fromJson(str, Bean.class);
            System.out.println(bean.getName());
            System.out.println(bean.getAge());
            System.out.println(bean.getScore());
        }
    }
}
