package test10_5;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Description 使用Apache提供的DbUtils来写BaseDAO类
 */
public class BaseDAOUsingDbUtils<T>
{
    private Class<T> clazz = null;

    //获取BaseDAOUsingDbUtils的泛型类型
    {
        Type genericSuperclass1 = this.getClass().getGenericSuperclass();
        ParameterizedType genericSuperclass2 = (ParameterizedType) genericSuperclass1;
        Type[] actualTypeArguments = genericSuperclass2.getActualTypeArguments();
        clazz = (Class<T>) actualTypeArguments[0];
    }

    //只需一个QueryRunner对象即可，因此将其声明为一个属性
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * @Description 实现通用的增删改操作
     */
    public int update(Connection conn, String sql, Object ... args)
    {
        int count = 0;
        try
        {
            count = queryRunner.update(conn, sql, args);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * @Description 实现通用的查询操作（仅返回一条记录）
     */
    public T selectForOneReturnsBean(Connection conn, String sql, Object ... args)
    {
        T t = null;
        try
        {
            t = queryRunner.query(conn, sql, new BeanHandler<>(clazz), args);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * @Description 实现通用的查询操作（返回多条记录）
     */
    public List<T> selectNotForOneReturnsBeanList(Connection conn, String sql, Object ... args)
    {
        List<T> tList = null;
        try
        {
            tList = queryRunner.query(conn, sql, new BeanListHandler<>(clazz), args);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return tList;
    }

    /**
     * @Description 实现特殊值的查询操作
     */
    public <E> E getValue(Connection conn, String sql, Object ... args)
    {
        E e = null;
        try
        {
            e = queryRunner.query(conn, sql, new ScalarHandler<E>(), args);
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return e;
    }
}
