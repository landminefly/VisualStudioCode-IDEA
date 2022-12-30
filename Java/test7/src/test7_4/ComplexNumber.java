package test7_4;

public class ComplexNumber implements Comparable
{
    int realNumber;
    int imaginaryNumber;

    public ComplexNumber(int realNumber, int imaginaryNumber)
    {
        this.realNumber = realNumber;
        this.imaginaryNumber = imaginaryNumber;
    }

    public ComplexNumber()
    {
    }

    @Override
    public String toString()
    {
        if(imaginaryNumber > 0)
            return realNumber + "+" + imaginaryNumber + "i";
        else
            return realNumber + "" + imaginaryNumber + "i";
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof ComplexNumber))
        {
            throw new RuntimeException("传入对象不是ComplexNumber类！");
        }
        ComplexNumber c2 = (ComplexNumber) obj;
        return (realNumber == c2.realNumber) && (imaginaryNumber == c2.imaginaryNumber);
    }

    @Override
    public int compareTo(Object obj)
    {
        if(!(obj instanceof ComplexNumber))
        {
            throw new RuntimeException("传入对象不是ComplexNumber类！");
        }
        ComplexNumber c2 = (ComplexNumber) obj;
        if(ComplexNumber.modify(this) - ComplexNumber.modify(c2) >0)
            return 1;
        else if(ComplexNumber.modify(this) - ComplexNumber.modify(c2) <0)
            return -1;
        else
            return 0;
    }

    public static double modify(ComplexNumber c1)
    {
        return Math.sqrt(c1.realNumber*c1.realNumber + c1.imaginaryNumber*c1.imaginaryNumber);
    }

    public static ComplexNumber plus(ComplexNumber c1, ComplexNumber c2)
    {
        return new ComplexNumber(c1.realNumber+c2.realNumber,c1.imaginaryNumber+c2.imaginaryNumber);
    }

    public static ComplexNumber subtract(ComplexNumber c1,ComplexNumber c2)
    {
        return new ComplexNumber(c1.realNumber-c2.realNumber,c1.imaginaryNumber-c2.imaginaryNumber);
    }

    public static ComplexNumber multiply(ComplexNumber c1,ComplexNumber c2)
    {
        int newReal = c1.realNumber * c2.realNumber - c1.imaginaryNumber * c2.imaginaryNumber;
        int newImage = c1.imaginaryNumber * c2.realNumber + c1.realNumber * c2.imaginaryNumber;
        return new ComplexNumber(newReal, newImage);
    }

    public static ComplexNumber divide(ComplexNumber c1,ComplexNumber c2)
    {
        int newReal = (c1.realNumber*c2.realNumber + c1.imaginaryNumber*c2.imaginaryNumber)
                        /(c2.realNumber*c2.realNumber + c2.imaginaryNumber*c2.imaginaryNumber);
        int newImage = (c1.imaginaryNumber*c2.realNumber + c1.realNumber*c2.imaginaryNumber)
                        /(c2.realNumber*c2.realNumber + c2.imaginaryNumber*c2.imaginaryNumber);
        return new ComplexNumber(newReal,newImage);
    }

}
