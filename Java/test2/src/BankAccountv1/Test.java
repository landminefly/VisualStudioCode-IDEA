package BankAccountv1;

public class Test
{
    public static void main(String[] args)
    {
        CheckAccount ca = new CheckAccount(114,514,0.08,20000);
        ca.withdraw(20000);
        ca.withdraw(20000);
        ca.deposit(20000);

        //∂‡Ã¨–‘
        Account a = new CheckAccount(114,514,0.1,1500);
        a.withdraw(2000);
    }
}
