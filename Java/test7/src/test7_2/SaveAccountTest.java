package test7_2;

public class SaveAccountTest
{
    public static void main(String[] args)
    {
        SaveAccount saver1 = new SaveAccount(2000.00);
        SaveAccount saver2 = new SaveAccount(3000.00);
        SaveAccount.modifyRate(0.04);
        System.out.println(saver1.calculate());
        System.out.println(saver2.calculate());
    }
}
