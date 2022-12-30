package BankAccountv1;

public class Account
{
    private int id;
    private double balance;
    private double annualInterestRate;

    public Account(int id, double balance, double annualInterestRate)
    {
        this.id = id;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balance)
    {
        this.balance = balance;
    }

    public double getAnnualInterestRate()
    {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate)
    {
        this.annualInterestRate = annualInterestRate;
    }

    public double getMonthlyInterest()
    {
        return annualInterestRate / 12;
    }

    public void withdraw(double amount)
    {
        if(balance < amount)
        {
            System.out.println("?????");
            System.out.println("?????????"+getBalance()+"?");
        }
        else
        {
            balance -= amount;
            System.out.println("?????????"+getBalance()+"?");
        }
    }

    public void deposit(double amount)
    {
        if(amount < 0)
        {
            System.out.println("???????");
        }
        else
        {
            balance += amount;
            System.out.println("?????????"+getBalance()+"?");
        }
    }
}
