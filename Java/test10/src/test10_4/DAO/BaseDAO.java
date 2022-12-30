package test10_4.DAO;

import util.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 封装了对于数据表的通用操作
 *              将此类设计为抽象类，旨在表示该类不可实例化
 */
public abstract class BaseDAO<T>
{
    Class<T> clazz = null;

    {
        //这个代码块在子类（如CustomerDAOImplement）创建对象时执行，目的是获取子类的父类（也就是BaseDAO）的泛型的Class对象
        //为 selectForOneWithTx 以及 selectNotForOneWithTx 中创建BaseDAO的泛型对象做准备
        //注意这里的this指的是正在创建的子类对象，并不是指BaseDAO
        Type genericSuperclass1 = this.getClass().getGenericSuperclass();
        ParameterizedType genericSuperclass2 = (ParameterizedType) genericSuperclass1;
        Type[] actualTypeArguments = genericSuperclass2.getActualTypeArguments();
        clazz = (Class<T>) actualTypeArguments[0];
    }

    /**
     * @Description 针对事务修改的通用增删改方法
     */
    public void editTableWithTx(Connection connection, String sql, Object ... args)
    {
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i<args.length; i++)
            {
                preparedStatement.setObject(i+1, args[i]);
            }
            preparedStatement.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(null,preparedStatement);
        }
    }

    /**
     * @Description 针对事务修改的通用查询方法，适用于仅返回一条记录的SELECT语句
     */
    public <T> T selectForOneWithTx (Connection connection,String sql, Object ... args)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i< args.length; i++)
            {
                preparedStatement.setObject(i+1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            if(resultSet.next())
            {
                T t = (T) clazz.getConstructor().newInstance();
                for(int i = 0; i<columnCount; i++)
                {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                return t;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(null,preparedStatement,resultSet);
        }
        return null;
    }

    /**
     * @Description 针对事务修改的通用查询方法，适用于返回多条记录的SELECT语句
     */
    public <T> List<T> selectNotForOneWithTx (Connection connection, String sql, Object ... args)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i< args.length; i++)
            {
                preparedStatement.setObject(i+1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            ArrayList<T> list = new ArrayList<>();
            while (resultSet.next())
            {
                T t = (T) clazz.getConstructor().newInstance();
                for(int i = 0; i<columnCount; i++)
                {
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(i + 1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(null,preparedStatement,resultSet);
        }
        return null;
    }

    /**
     * @Description 针对事务修改的，用于查询特殊值的通用方法
     */
    public <E> E getValueWhitTx(Connection conn,String sql,Object...args)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            preparedStatement = conn.prepareStatement(sql);
            for(int i = 0;i < args.length;i++)
            {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return (E) resultSet.getObject(1);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            JDBCUtils.closeResource(null, preparedStatement, resultSet);
        }
        return null;
    }

}
