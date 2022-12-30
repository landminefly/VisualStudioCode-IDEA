package test10_4.DAO.Implement;

import bean.Customer;
import test10_4.DAO.BaseDAO;
import test10_4.DAO.CustomerDAO;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class CustomerDAOImplement extends BaseDAO<Customer> implements CustomerDAO
{

    @Override
    public void insert(Connection connection, Customer customer)
    {
        String sql = "INSERT INTO customers(name,email,birth) VALUES(?,?,?)";
        editTableWithTx(connection,sql,customer.getName(),customer.getEmail(),customer.getBirth());
    }

    @Override
    public void deleteById(Connection connection, int id)
    {
        String sql = "DELETE FROM customers WHERE id = ?";
        editTableWithTx(connection,sql,id);
    }

    @Override
    public void updateById(Connection connection, Customer customer)
    {
        String sql = "UPDATE customers SET name = ?,email = ?,birth = ? WHERE id = ?";
        editTableWithTx(connection,sql,customer.getName(),customer.getEmail(),customer.getBirth(),customer.getId());
    }

    @Override
    public Customer getCustomerById(Connection connection, int id)
    {
        String sql = "SELECT name,email,birth FROM customers WHERE id = ?";
        return selectForOneWithTx(connection,sql,id);
    }

    @Override
    public List<Customer> getAll(Connection connection)
    {
        String sql = "SELECT name,email,birth FROM customers";
        return selectNotForOneWithTx(connection,sql);
    }

    @Override
    public Long getCount(Connection connection)
    {
        String sql = "SELECT COUNT(*) FROM customers";
        //因为getCount的返回类型为Long，也就指定了getValueWhitTx的泛型E为Long
        return getValueWhitTx(connection, sql);
    }

    @Override
    public Date getMaxBirth(Connection connection)
    {
        String sql = "SELECT MAX(birth) FROM customers";
        //因为getMaxBirth的返回类型为Date，也就指定了getValueWhitTx的泛型E为Date
        return getValueWhitTx(connection,sql);
    }
}
