package domain;

import service.Status;

public class Architect extends Designer
{
    private int stock;

    public Architect()
    {
    }

    public Architect(int id, String name, int age, double salary, Equipment equipment, double bonus, int stock)
    {
        super(id, name, age, salary,equipment, bonus);
        this.stock = stock;
    }

    public int getStock()
    {
        return stock;
    }

    public void setStock(int stock)
    {
        this.stock = stock;
    }

    @Override
    public String toString()
    {
        return getDetails()+"\t�ܹ�ʦ\t"+getStatus().toString()+"\t"+getBonus()+"\t"+stock+"\t"+getEquipment().getDescription();
    }

    @Override
    public String getDetailsForTeam()
    {
        return getMemberId()+"/"+getId()+"\t\t"+getName()+"\t"+getAge()+"\t\t"+getSalary()+"\t�ܹ�ʦ"+"\t"+getBonus()+"\t"+getStock();
    }
}
