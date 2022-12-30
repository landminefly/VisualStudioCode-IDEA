package test10_3.DAO;

import bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/*
 * 此接口用于声明对于customers表的操作
 */
public interface CustomerDAO
{
    /**
     * @Description 将Customer对象作为一条记录添加到数据表中
     */
    void insert(Connection connection, Customer customer);

    /**
     * @Description 通过指定id，删除表中记录
     */
    void deleteById(Connection connection, int id);

    /**
     * @Description 将与Customer对象的id值相同的记录的字段值修改成与该对象的属性值一致
     */
    void updateById(Connection connection, Customer customer);

    /**
     * @Description 查询指定id的记录（默认返回一条记录）
     */
    Customer getCustomerById(Connection connection, int id);

    /**
     * @Description 查询表中的所有记录
     */
    List<Customer> getAll(Connection connection);

    //以下属于特殊操作，可以根据实际需求进行更改

    /**
     * @Description 返回表中的记录数
     */
    Long getCount(Connection connection);

    /**
     * @Description 返回表中最大的birth值
     */
    Date getMaxBirth(Connection connection);
}
