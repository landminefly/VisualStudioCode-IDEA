// /**
// @author LeiLi
// @version v1.0
// This is my first Java program!!I am so fucking happy!!
//  */

// public class test1
// {
//     /**
//     The main() is the entrance of the program.
//      */
//     public static void main(String[] args)
//     {
//         //This is a single-line comment.
//
//         /*
//             This is a multi-line comment.
//         */
//         System.out.println("hello world!");
//     }
// }

// class President
// {
//     public static void main(String[] arr)
//     {
//         System.out.println("������ϰ��ƽ\n");
//         System.out.println("�Ա���");
//         System.out.println("סַ���������Ϻ�");
//     }
// }

// public class test1
// {
//     public static void main(String[] args)
//     {
//         int num1 = 996;
//         System.out.println(num1);
//         int num2;
//         num2 = 648;
//         System.out.println(num2);
//         //��C���Ի���һ����̫����
//     }
// }

// public class test1
// {
//     public static void main(String[] args)
//     {
//         // boolean motherOfCheery = false;
//         // if(motherOfCheery)
//         // {
//         //     System.out.println("�� ��ʲôʱ������?");
//         // }
//         // else
//         // {
//         //     System.out.println("���ƣ������Ŀ���");
//         // }

//         // char c = 'a';
//         // int num = 10;
//         // String str = "hello";

//         // System.out.println(c + num + str); //107hello
//         // System.out.println(c + str + num); //ahello10
//         // System.out.println(c + (num + str)); //a10hello
//         // System.out.println(str + num + c); //hello10a

//         // byte b1 = 10;
//         // byte b2 = 20;
//         // byte b3 = b1 + b2;//����: �����ݵ�����: ��intת����byte���ܻ�����ʧ
//         // byte b3 = (byte)(b1 + b2);//��

//         // //find the max number between the two number.
//         // byte a = 10;
//         // byte b = 10;
//         // String maxStr = (a > b) ? "a is max" : ((a == b) ? "a and b are the same" : "b is max");
//         // System.out.println(maxStr);
//     }
// }

import java.util.Scanner;//����

class test1
{
    public static void main(String[] args)
    {
        // Scanner scan = new Scanner(System.in);

        // int a = scan.nextInt();//int
        // System.out.println(a);

        // double b = scan.nextDouble();//double
        // System.out.println(b);

        // String c = scan.next();//String
        // System.out.println(c);

        // boolean d = scan.nextBoolean();//boolean
        // System.out.println(d);

        // String E = scan.next();//char
        // char e = E.charAt(0);//��ȡ�ַ�����nλ���ַ�
        // System.out.println(e);

        // //�������Χ��10 - 99
        // int num = (int)(Math.random() * 90 + 10);
        // //[0.0,1.0) -> [0.0,90.0) -> [10.0,100.0) ->[10,99]

        // //��ʽ��[a,b]������ : (int)(Math.random() * (b - a + 1) + a);
        // System.out.println(num);

        // String str = "��Ȼ";
        // System.out.println(str.equals("����"));

        // String Asoul = "Diana";
        // switch(Asoul)
        // {
        //     case "Diana":
        //         System.out.println("��Ȼ");
        //         break;
        //     case "Ava":
        //         System.out.println("����");
        //         break;
        //     case "Bella":
        //         System.out.println("����");
        //         break;
        //     case "Carol":
        //         System.out.println("����");
        //         break;
        //     case "Eileen":
        //         System.out.println("����");
        //         break;
        // }

        // for(int i = 0; i < 5; i++)
        // {
        //     for(int j = 0; j < 4 - i; j++)
        //     {
        //         System.out.print(" ");
        //     }
        //     for(int j = 0; j < i + 1; j++)
        //     {
        //         System.out.print("* ");
        //     }
        //     System.out.println();
        // }
        // for(int i = 0; i < 4; i++)
        // {
        //     for(int j = 0; j < i + 1; j++)
        //     {
        //         System.out.print(" ");
        //     }
        //     for(int j = 0; j < 4 - i; j++)
        //     {
        //         System.out.print("* ");
        //     }
        //     System.out.println();
        // }

        // for(int i = 1; i <= 9; i++)
        // {
        //     for(int j = 1; j <= i; j++)
        //     {
        //         System.out.print(i+" * "+j+" = "+(i*j)+"\t");
        //     }
        //     System.out.println();
        // }

        // int i = 9;
        // double j = Math.sqrt(i);
        // System.out.println(j);

        // long time = System.currentTimeMillis();
        // System.out.println(time);
        
        label:for(int i = 1; i<5; i++)
        {
            for(int j = 1; j<10; j++)
            {
                if(j % 4 == 0)
                {
                    break label;
                }
                System.out.print(j);
            }
            System.out.println();
        }
        //���н����123
    }
}