package service;

import domain.*;
import org.junit.jupiter.api.Test;
import static service.Data.*;

public class NameListService
{
    // @Test
    // public static void main(String[] args)
    // {
    //     try
    //     {
    //         System.out.println(new NameListService().getEmployee(100));
    //     } catch (TeamException e)
    //     {
    //         e.printStackTrace();
    //     }
    // }

    private Employee[] employees;

    public NameListService()
    {
        employees = new Employee[EMPLOYEES.length];
        for(int i = 0; i<employees.length; i++)
        {
            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = Integer.parseInt(EMPLOYEES[i][4]);

            int type = Integer.parseInt(EMPLOYEES[i][0]);
            switch(type)
            {
            case EMPLOYEE:
                employees[i] = new Employee(id,name,age,salary);
                break;
            case PROGRAMMER:
                Equipment equipment1 = creatEquipment(i);
                employees[i] = new Programmer(id,name,age,salary,equipment1);
                break;
            case DESIGNER:
                Equipment equipment2 = creatEquipment(i);
                employees[i] = new Designer(id,name,age,salary,equipment2,
                        Double.parseDouble(EMPLOYEES[i][5]));
                break;
            case ARCHITECT:
                Equipment equipment3 = creatEquipment(i);
                employees[i] = new Architect(id,name,age,salary,equipment3,
                        Double.parseDouble(EMPLOYEES[i][5]),
                        Integer.parseInt(EMPLOYEES[i][6]));
                break;
            default:
                employees[i] = null;
                break;
            }
        }
    }

    public Employee[] getAllEmployees()
    {
        return employees;
    }

    private Equipment creatEquipment(int i)
    {
        int key = Integer.parseInt(EQUIPMENTS[i][0]);
        switch(key)
        {
        case PC:
            return new PC(EQUIPMENTS[i][1],EQUIPMENTS[i][2]);
        case NOTEBOOK:
            return new NoteBook(EQUIPMENTS[i][1],Double.parseDouble(EQUIPMENTS[i][2]));
        case PRINTER:
            return new Printer(EQUIPMENTS[i][1],EQUIPMENTS[i][2]);
        default:
            return null;
        }
    }

    public Employee getEmployee(int id) throws TeamException
    {
        for(int i = 0; i<employees.length; i++)
        {
            if(employees[i].getId() == id)
            {
                return employees[i];
            }
        }
        throw new TeamException("找不到指定员工！");
    }
}
