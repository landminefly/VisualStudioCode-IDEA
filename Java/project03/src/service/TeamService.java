package service;

import domain.Architect;
import domain.Designer;
import domain.Employee;
import domain.Programmer;

public class TeamService
{
    private int counter = 1;
    private final int MAX_MEMBER = 5;
    private Programmer[] team = new Programmer[MAX_MEMBER];
    private int total = 0;

    public Programmer[] getTeam()
    {
        Programmer[] team = new Programmer[total];
        for(int i = 0; i<team.length; i++)
        {
            team[i] = this.team[i];
        }
        return team;
    }

    public void addMember(Employee e) throws TeamException
    {
        if(total >= MAX_MEMBER)
        {
            throw new TeamException("��Ա������");
        }
        if(!(e instanceof Programmer))
        {
            throw new TeamException("�ó�Ա���ǿ�����Ա��");
        }
        if(isExist(e))
        {
            throw new TeamException("��Ա�����ڱ������Ŷ��У�");
        }

        Programmer p = (Programmer)e;
        if("BUSY".equals(p.getStatus().getNAME()))
        {
            throw new TeamException("��Ա������ĳ�Ŷӳ�Ա��");
        }else if("VOCATION".equals(p.getStatus().getNAME()))
        {
            throw new TeamException("��Ա�������ݼ٣���н���������ɣ�");
        }

        int numOfArch = 0, numOfDes = 0, numOfPro = 0;
        for(int i = 0; i<total; i++)
        {
            if(team[i] instanceof Architect)
                numOfArch++;
            else if(team[i] instanceof Designer)
                numOfDes++;
            else if(team[i] instanceof Programmer)
                numOfPro++;
        }
        if(p instanceof Architect)
        {
            if (numOfArch >= 1)
            // ����д�� if(p instance of Architect && numOfArch >= 1) ����ͬ
            {
                throw new TeamException("�Ŷ������ֻ����һ���ܹ�ʦ��");
            }
        }
        else if(p instanceof Designer)
        {
            if (numOfDes >= 2)
            {
                throw new TeamException("�Ŷ������ֻ�����������ʦ��");
            }
        }
        else if(p instanceof Programmer)
            {
                if (numOfPro >= 3)
                {
                    throw new TeamException("�Ŷ������ֻ������������Ա��");
                }
            }

        team[total++] = p;
        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);

    }

    public boolean isExist(Employee e)
    {
        for(int i = 0; i<total; i++)
        {
            if(team[i].getId() == e.getId())
                return true;
        }
        return false;
    }

    public void removeMember(int memberId) throws TeamException
    {
        int i = 0;
        for(i=0; i<total; i++)
        {
            if(team[i].getMemberId() == memberId)
            {
                team[i].setStatus(Status.FREE);
                break;
            }
        }
        if(i == total)
        {
            throw new TeamException("�Ҳ���ָ����Ա����");
        }
        for(int j = i + 1; j<total; j++)
        {
            team[j-1] = team[j];
        }
        team[--total] = null;
    }

}
