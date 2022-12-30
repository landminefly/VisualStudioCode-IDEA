package BankAccountv1;

public class CheckAccount extends Account
{
    private double overdraft;

    public CheckAccount(int id,double balance,
                        double annualInterestRate,double overdraft)
    {
        super(id,balance,annualInterestRate);
        this.overdraft = overdraft;
    }

    @Override
    public void withdraw(double amount)
    {
        if(getBalance() >= amount)
        {
            super.withdraw(amount);
            System.out.println("当前可透支额度："+overdraft);
        }
        else if(getBalance() + overdraft >= amount)
        {
            overdraft -= amount - getBalance();
            setBalance(0);
            System.out.println("当前余额为："+getBalance()+"元");
            System.out.println("当前可透支额度："+overdraft);
        }
        else
        {
            System.out.println("可透支额度不足！");
            System.out.println("当前余额与可透支额度之和为："+(getBalance()+overdraft)+"元");
        }
    }
}
