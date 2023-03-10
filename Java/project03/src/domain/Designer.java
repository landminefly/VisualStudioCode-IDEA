package domain;

import service.Status;

public class Designer extends Programmer
{
    private double bonus;

    public Designer()
    {
    }

    public Designer(int id, String name, int age, double salary,Equipment equipment, double bonus)
    {
        super(id, name, age, salary,equipment);
        this.bonus = bonus;
    }

    public double getBonus()
    {
        return bonus;
    }

    public void setBonus(double bonus)
    {
        this.bonus = bonus;
    }

    @Override
    public String toString()
    {
        return getDetails()+"\t设计师\t"+getStatus().toString()+"\t"+bonus+"\t\t\t"+getEquipment().getDescription();
    }

    @Override
    public String getDetailsForTeam()
    {
        return getMemberId()+"/"+getId()+"\t\t"+getName()+"\t"+getAge()+"\t\t"+getSalary()+"\t设计师"+"\t"+getBonus();
    }
}
