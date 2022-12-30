package test3_1;

/*
    �쳣��ϵ�ṹ
    java.lang.Throwable
        |------java.lang.Error:һ�㲻��д����ԵĴ�����д���
        |------java.lang.Exception:���Խ����쳣�Ĵ���
            |------����ʱ�쳣
                |------IOException
                |------ClassNotFoundException
                ...
            |------����ʱ�쳣
                |------NullPointerException
                |------ArrayIndexOutOfBoundsException
                |------ClassCastException
                ...
 */
// public class test3_1_1
// {
//     public static void main(String[] args)
//     {
//         try
//         {
//             test3_1_1.method1();
//         }
//         catch(ArithmeticException e)
//         {
//             System.out.println("exception");
//         }
//
//     }
//     public static void method1() throws ArithmeticException
//     {
//         method2();
//     }
//     public static void method2() throws ArithmeticException
//     {
//         int i = 1/0;
//     }
// }

// public class test3_1_1
// {
//     public static void method1()
//     {
//         try
//         {
//             int i = Integer.parseInt("abc");
//         }
//         catch (NumberFormatException e)
//         {
//             System.out.println(e.getMessage());
//             System.out.println("********************");
//             e.printStackTrace();
//         }
//     }
//     public static void main(String[] args)
//     {
//         test3_1_1.method1();
//     }
// }

//
// public class test3_1_1
// {
//     public static void A()
//     {
//         try
//         {
//             int i = B(1);
//             int ii = C(i);
//             int iii = D(ii);
//             System.out.println(iii);
//         } catch (Exception e)
//         {
//             e.printStackTrace();
//         }
//     }
//     public static int B(int x) throws Exception
//     {
//         return x+1;
//     }
//     public static int C(int x) throws Exception
//     {
//         return x+2;
//     }
//     public static int D(int x) throws Exception
//     {
//         return x+3;
//     }
//
//     public static void main(String[] args)
//     {
//         test3_1_1.A();
//     }
// }

public class test3_1_1
{
    public static void main(String[] args)
    {
            Student s = new Student(-1);
    }
}
class Student
{
    private int grade;

    public Student(int grade)
    {
        if(grade >= 0)
        {
            this.grade = grade;
        }
        else
        {
            throw new NegativeNumberException("������Ǹ�����");
        }
    }
}
class NegativeNumberException extends RuntimeException
{
    static final long serialVersionUID = -7034897193246939L;
    public NegativeNumberException()
    {

    }
    public NegativeNumberException(String msg)
    {
        super(msg);
    }
}
