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
            System.out.println("��ǰ��͸֧��ȣ�"+overdraft);
        }
        else if(getBalance() + overdraft >= amount)
        {
            overdraft -= amount - getBalance();
            setBalance(0);
            System.out.println("��ǰ���Ϊ��"+getBalance()+"Ԫ");
            System.out.println("��ǰ��͸֧��ȣ�"+overdraft);
        }
        else
        {
            System.out.println("��͸֧��Ȳ��㣡");
            System.out.println("��ǰ������͸֧���֮��Ϊ��"+(getBalance()+overdraft)+"Ԫ");
        }
    }
}
