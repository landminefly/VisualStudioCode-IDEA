package view;

import domain.Employee;
import domain.Programmer;
import service.NameListService;
import service.TeamException;
import service.TeamService;

public class TeamView
{
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    public void enterMainMenu()
    {
        boolean flag = true;
        do
        {
            listAllEmployees();
            System.out.println("功能选项：1、团队列表 2、添加团队成员 3、删除团队成员 4、退出");
            System.out.print("请选择：");
            char key = TSUtility.readMenuSelection();
            switch (key)
            {
            case '1':
                getTeam();
                break;
            case '2':
                addMember();
                break;
            case '3':
                deleteMember();
                break;
            case '4':
                System.out.print("是否确认退出？（Y/N）：");
                char isExit = TSUtility.readConfirmSelection();
                if (isExit == 'Y')
                    flag = false;
                break;
            }
        }while(flag);
    }

    private void listAllEmployees()
    {
        System.out.println("---------------------------开发团队调度软件---------------------------");
        Employee[] e = listSvc.getAllEmployees();
        if(e == null)
        {
            System.out.println("没有员工信息!");
            return;
        }
        System.out.println("ID\t姓名\t年龄\t工资\t职位\t状态\t奖金\t股票\t领用设备");
        for(int i = 0; i<e.length; i++)
        {
            System.out.println(e[i].toString());
        }
    }

    private void getTeam()
    {
        System.out.println("---------------------------团队成员列表---------------------------");
        Programmer[] p = teamSvc.getTeam();
        if(p.length == 0)
        {
            System.out.println("团队中没有成员!");
            TSUtility.readReturn();
            return;
        }
        for(int i = 0; i<p.length; i++)
        {
            System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
            System.out.println(p[i].getDetailsForTeam());
        }
        TSUtility.readReturn();
    }

    private void addMember()
    {
        System.out.println("---------------------------添加团队员工---------------------------");
        System.out.print("请输入要添加的员工ID:");
        int id = TSUtility.readInt();
        try
        {
            Employee e = listSvc.getEmployee(id);
            teamSvc.addMember(e);
            System.out.println("添加成功!");
            TSUtility.readReturn();
        } catch (TeamException ex)
        {
            System.out.println("添加失败,原因:"+ex.getMessage());
            TSUtility.readReturn();
        }
    }

    private void deleteMember()
    {
        System.out.println("---------------------------删除团队成员---------------------------");
        System.out.print("请输入要删除的员工的memberID:");
        try
        {
            int mID = TSUtility.readInt();
            teamSvc.removeMember(mID);
            System.out.println("删除成功!");
            TSUtility.readReturn();
        } catch (TeamException e)
        {
            System.out.println("添加失败,原因:"+e.getMessage());
            TSUtility.readReturn();
        }
    }

    public static void main(String[] args)
    {
        TeamView teamView = new TeamView();
        teamView.enterMainMenu();
    }
}
