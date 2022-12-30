package service;

import bean.Customer;

public class CustomerList
{
    private Customer[] customers;
    private int total = 0;

    public CustomerList(int totalCustomer)
    {
        customers = new Customer[totalCustomer];
    }

    public boolean addCustomer(Customer customer)
    {
        if(total >= customers.length)
        {
            return false;
        }
        customers[total++] = customer;
        return true;
    }

    public boolean replaceCustomer(int index,Customer customer)
    {
        if(index < 0 || index >= total)
        {
            return false;
        }
        customers[index] = customer;
        return true;
    }

    public boolean deleteCustomer(int index)
    {
        if(index < 0 || index >= total)
        {
            return false;
        }
        for(int i = index; i<total - 1; i++)
        {
            customers[i] = customers[i+1];
        }
        customers[--total] = null;
        return true;
    }

    public Customer[] getAllCustomer()
    {
        Customer[] customers = new Customer[total];
        System.arraycopy(this.customers, 0, customers, 0, total);
        return customers;
    }

    public Customer getCustomer(int index)
    {
        if(index < 0 || index >= total)
        {
            return null;
        }
        return customers[index];
    }

    public int getTotal()
    {
        return total;
    }

}
