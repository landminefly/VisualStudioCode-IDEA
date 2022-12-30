package test7_2;
public class SaveAccount
{
    private static double Rate;
    private double saving;
    public SaveAccount(double saving)
    {
        this.saving = saving;
    }
    public static void modifyRate(double newRate)
    {
        Rate = newRate;
    }
    public double calculate()
    {
        saving += saving * Rate / 12;
        return saving;
    }
}
