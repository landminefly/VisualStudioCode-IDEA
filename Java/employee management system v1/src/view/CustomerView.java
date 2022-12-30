package view;

import bean.Customer;
import service.CustomerList;
import util.CMUtility;

public class CustomerView
{
    private CustomerList customerList = new CustomerList(10);

    public void enterMainMenu()
    {
        boolean flag = true;
        do
        {
            System.out.println("客户信息管理软件");
            System.out.println("1.添加客户");
            System.out.println("2.修改客户");
            System.out.println("3.删除客户");
            System.out.println("4.客户列表");
            System.out.println("5.退出");
            System.out.print("请选择：");
            System.out.println();

            char key = CMUtility.readChar();
            switch(key)
            {
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    listAllCustomer();
                    break;
                case '5':
                    flag = false;
                    break;
            }
        }while(flag);
    }

    private void addNewCustomer()
    {
        System.out.println("添加客户...");
        System.out.print("姓名：");
        String name = CMUtility.readString(4);
        System.out.println("性别：");
        char gender  = CMUtility.readChar();
        System.out.println("年龄：");
        int age = CMUtility.readInt();
        System.out.println("电话：");
        String phone = CMUtility.readString(13);
        System.out.println("邮箱：");
        String email = CMUtility.readString(25);
        Customer customer = new Customer(name,gender,age,phone,email);
        if(customerList.addCustomer(customer))
        {
            System.out.println("添加成功！");
        }
        else
        {
            System.out.println("添加失败！");
        }
    }

    private void modifyCustomer()
    {
        System.out.println("修改客户...");
        int index = 0;
        Customer customer = null;
        while(true)
        {
            System.out.println("请选择待修改客户编号(-1退出):");
            index = CMUtility.readInt();
            if(index == -1)
            {
                return;
            }
            else
            {
                customer = customerList.getCustomer(index-1);
                if(customer == null)
                {
                    System.out.println("无法找到指定客户!");
                }
                else
                {
                    break;
                }
            }
        }
        System.out.print("姓名(" + customer.getName() + ")：");
        String name = CMUtility.readString(4, customer.getName());

        System.out.print("性别(" + customer.getGender() + ")：");
        char gender = CMUtility.readChar(customer.getGender());

        System.out.print("年龄(" + customer.getAge() + ")：");
        int age = CMUtility.readInt(customer.getAge());

        System.out.print("电话(" + customer.getPhone() + ")：");
        String phone = CMUtility.readString(15, customer.getPhone());

        System.out.print("邮箱(" + customer.getEmail() + ")：");
        String email = CMUtility.readString(15, customer.getEmail());

        customer = new Customer(name,gender,age,phone,email);
        customerList.replaceCustomer(index-1,customer);
        System.out.println("修改成功!");
    }

    private void deleteCustomer()
    {
        System.out.println("修改客户...");
        int index = 0;
        Customer customer = null;
        while(true)
        {
            System.out.println("请选择待修改客户编号(-1退出):");
            index = CMUtility.readInt();
            if(index == -1)
            {
                return;
            }
            else
            {
                customer = customerList.getCustomer(index-1);
                if(customer == null)
                {
                    System.out.println("无法找到指定客户!");
                }
                else
                {
                    break;
                }
            }
        }
        System.out.println("是否继续删除?(Y/N):");
        if(CMUtility.readConfirmSelection() == 'N')
        {
            return;
        }
        else
        {
            customerList.deleteCustomer(index-1);
        }
        System.out.println("删除成功!");
    }

    private void listAllCustomer()
    {
        System.out.println("---------------------------客户列表---------------------------");
        Customer[] customers = customerList.getAllCustomer();
        if(customers.length == 0)
        {
            System.out.println("没有客户记录！");
        }
        else
        {
            System.out.println("编号\t姓名\t\t性别\t年龄\t电话\t\t\t邮箱");
            for (int i = 0; i < customers.length; i++)
            {
                System.out.println(i+1+"\t\t"+customers[i].getName()+
                                       "\t\t"+customers[i].getGender()+
                                       "\t\t"+customers[i].getAge()+
                                       "\t\t"+customers[i].getPhone()+
                                       "\t\t"+customers[i].getEmail());

            }
        }
        System.out.println("---------------------------列表完成---------------------------");
    }

    public static void main(String[] args)
    {
        CustomerView view = new CustomerView();
        view.enterMainMenu();
    }
}
