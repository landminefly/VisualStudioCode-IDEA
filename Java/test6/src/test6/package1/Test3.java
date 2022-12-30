package test6.package1;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Test3
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        double salary = scanner.nextDouble();
        final double MIN = 3500;
        double tax;
        if (salary < 3500)
        {
            tax = 0;
        }
        else if (salary - MIN < 1500)
        {
            tax = (salary - MIN) * 0.03;
        }
        else if (salary - MIN < 4500)
        {
            tax = 1500 * 0.03 + (salary - 1500) * 0.1;
        }
        else if (salary - MIN < 9000)
        {
            tax = 1500 * 0.03 + 3000 * 0.1 + (salary - 4500) * 0.2;
        }
        else if (salary - MIN < 35000)
        {
            tax = 1500 * 0.03 + 3000 * 0.1 + 4500 * 0.2 + (salary - 9000) * 0.25;
        }
        else if (salary - MIN < 55000)
        {
            tax = 1500 * 0.03 + 3000 * 0.1 + 4500 * 0.2 + 26000 * 0.25 + (salary - 35000) * 0.30;
        }
        else if (salary - MIN < 80000)
        {
            tax = 1500 * 0.03 + 3000 * 0.1 + 4500 * 0.2 + 26000 * 0.25 + 20000 * 0.3 + (salary - 55000) * 0.35;
        }
        else
        {
            tax = 1500 * 0.03 + 3000 * 0.1 + 4500 * 0.2 + 26000 * 0.25 + 20000 * 0.3 + 25000 * 0.35 + (salary - 80000) * 0.45;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        JOptionPane.showMessageDialog(null, decimalFormat.format(tax));
    }
}
