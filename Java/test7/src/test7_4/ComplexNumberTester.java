package test7_4;

public class ComplexNumberTester
{
    public static void main(String[] args)
    {
        ComplexNumber c1 = new ComplexNumber(2,3);
        ComplexNumber c2 = new ComplexNumber(4, 6);
        ComplexNumber c3 = new ComplexNumber(2, 3);
        ComplexNumber c4 = new ComplexNumber(1, 2);
        System.out.println(c1.equals(c2));
        System.out.println(c1.equals(c3));
        System.out.println(c1.compareTo(c2));
        System.out.println(c1.compareTo(c3));
        System.out.println(c1.compareTo(c4));
        System.out.println(ComplexNumber.plus(c1, c1).toString());
        System.out.println(ComplexNumber.subtract(c1, c2).toString());
        System.out.println(ComplexNumber.multiply(c1, c3).toString());
        System.out.println(ComplexNumber.divide(c1, c4).toString());
    }
}
