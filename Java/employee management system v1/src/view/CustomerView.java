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
            System.out.println("�ͻ���Ϣ�������");
            System.out.println("1.��ӿͻ�");
            System.out.println("2.�޸Ŀͻ�");
            System.out.println("3.ɾ���ͻ�");
            System.out.println("4.�ͻ��б�");
            System.out.println("5.�˳�");
            System.out.print("��ѡ��");
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
        System.out.println("��ӿͻ�...");
        System.out.print("������");
        String name = CMUtility.readString(4);
        System.out.println("�Ա�");
        char gender  = CMUtility.readChar();
        System.out.println("���䣺");
        int age = CMUtility.readInt();
        System.out.println("�绰��");
        String phone = CMUtility.readString(13);
        System.out.println("���䣺");
        String email = CMUtility.readString(25);
        Customer customer = new Customer(name,gender,age,phone,email);
        if(customerList.addCustomer(customer))
        {
            System.out.println("��ӳɹ���");
        }
        else
        {
            System.out.println("���ʧ�ܣ�");
        }
    }

    private void modifyCustomer()
    {
        System.out.println("�޸Ŀͻ�...");
        int index = 0;
        Customer customer = null;
        while(true)
        {
            System.out.println("��ѡ����޸Ŀͻ����(-1�˳�):");
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
                    System.out.println("�޷��ҵ�ָ���ͻ�!");
                }
                else
                {
                    break;
                }
            }
        }
        System.out.print("����(" + customer.getName() + ")��");
        String name = CMUtility.readString(4, customer.getName());

        System.out.print("�Ա�(" + customer.getGender() + ")��");
        char gender = CMUtility.readChar(customer.getGender());

        System.out.print("����(" + customer.getAge() + ")��");
        int age = CMUtility.readInt(customer.getAge());

        System.out.print("�绰(" + customer.getPhone() + ")��");
        String phone = CMUtility.readString(15, customer.getPhone());

        System.out.print("����(" + customer.getEmail() + ")��");
        String email = CMUtility.readString(15, customer.getEmail());

        customer = new Customer(name,gender,age,phone,email);
        customerList.replaceCustomer(index-1,customer);
        System.out.println("�޸ĳɹ�!");
    }

    private void deleteCustomer()
    {
        System.out.println("�޸Ŀͻ�...");
        int index = 0;
        Customer customer = null;
        while(true)
        {
            System.out.println("��ѡ����޸Ŀͻ����(-1�˳�):");
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
                    System.out.println("�޷��ҵ�ָ���ͻ�!");
                }
                else
                {
                    break;
                }
            }
        }
        System.out.println("�Ƿ����ɾ��?(Y/N):");
        if(CMUtility.readConfirmSelection() == 'N')
        {
            return;
        }
        else
        {
            customerList.deleteCustomer(index-1);
        }
        System.out.println("ɾ���ɹ�!");
    }

    private void listAllCustomer()
    {
        System.out.println("---------------------------�ͻ��б�---------------------------");
        Customer[] customers = customerList.getAllCustomer();
        if(customers.length == 0)
        {
            System.out.println("û�пͻ���¼��");
        }
        else
        {
            System.out.println("���\t����\t\t�Ա�\t����\t�绰\t\t\t����");
            for (int i = 0; i < customers.length; i++)
            {
                System.out.println(i+1+"\t\t"+customers[i].getName()+
                                       "\t\t"+customers[i].getGender()+
                                       "\t\t"+customers[i].getAge()+
                                       "\t\t"+customers[i].getPhone()+
                                       "\t\t"+customers[i].getEmail());

            }
        }
        System.out.println("---------------------------�б����---------------------------");
    }

    public static void main(String[] args)
    {
        CustomerView view = new CustomerView();
        view.enterMainMenu();
    }
}
