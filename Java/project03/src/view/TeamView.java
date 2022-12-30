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
            System.out.println("����ѡ�1���Ŷ��б� 2������Ŷӳ�Ա 3��ɾ���Ŷӳ�Ա 4���˳�");
            System.out.print("��ѡ��");
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
                System.out.print("�Ƿ�ȷ���˳�����Y/N����");
                char isExit = TSUtility.readConfirmSelection();
                if (isExit == 'Y')
                    flag = false;
                break;
            }
        }while(flag);
    }

    private void listAllEmployees()
    {
        System.out.println("---------------------------�����Ŷӵ������---------------------------");
        Employee[] e = listSvc.getAllEmployees();
        if(e == null)
        {
            System.out.println("û��Ա����Ϣ!");
            return;
        }
        System.out.println("ID\t����\t����\t����\tְλ\t״̬\t����\t��Ʊ\t�����豸");
        for(int i = 0; i<e.length; i++)
        {
            System.out.println(e[i].toString());
        }
    }

    private void getTeam()
    {
        System.out.println("---------------------------�Ŷӳ�Ա�б�---------------------------");
        Programmer[] p = teamSvc.getTeam();
        if(p.length == 0)
        {
            System.out.println("�Ŷ���û�г�Ա!");
            TSUtility.readReturn();
            return;
        }
        for(int i = 0; i<p.length; i++)
        {
            System.out.println("TID/ID\t����\t����\t����\tְλ\t����\t��Ʊ");
            System.out.println(p[i].getDetailsForTeam());
        }
        TSUtility.readReturn();
    }

    private void addMember()
    {
        System.out.println("---------------------------����Ŷ�Ա��---------------------------");
        System.out.print("������Ҫ��ӵ�Ա��ID:");
        int id = TSUtility.readInt();
        try
        {
            Employee e = listSvc.getEmployee(id);
            teamSvc.addMember(e);
            System.out.println("��ӳɹ�!");
            TSUtility.readReturn();
        } catch (TeamException ex)
        {
            System.out.println("���ʧ��,ԭ��:"+ex.getMessage());
            TSUtility.readReturn();
        }
    }

    private void deleteMember()
    {
        System.out.println("---------------------------ɾ���Ŷӳ�Ա---------------------------");
        System.out.print("������Ҫɾ����Ա����memberID:");
        try
        {
            int mID = TSUtility.readInt();
            teamSvc.removeMember(mID);
            System.out.println("ɾ���ɹ�!");
            TSUtility.readReturn();
        } catch (TeamException e)
        {
            System.out.println("���ʧ��,ԭ��:"+e.getMessage());
            TSUtility.readReturn();
        }
    }

    public static void main(String[] args)
    {
        TeamView teamView = new TeamView();
        teamView.enterMainMenu();
    }
}
