import com.mapper.UserMapper;
import com.pojo.User;
import com.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class UserMapperTest {
    @Test
    public void test1(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> allUser = userMapper.getAllUser();
        for(User user : allUser){
            System.out.println(user);
        }
    }

    @Test
    public void test2(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<Map<String, Object>> allUser = userMapper.getAllUserInMapUsingList();
        for(Map<String, Object> user : allUser){
            System.out.println(user);
        }
    }

    @Test
    public void test3(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> allUser = userMapper.getAllUserInMapUsingMap();
        System.out.println(allUser);
    }
}
