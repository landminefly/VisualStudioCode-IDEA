package BoyGirlTest;

import static java.lang.System.*;

public class BoyGirlTest
{
    public static void main(String[] args)
    {
        Boy boy = new Boy("����",500);
        Girl girl = new Girl("����",20);

        boy.marry(girl);
        System.out.println("*****************************");
        girl.marry(boy);
        System.out.println("*****************************");
        compare(boy,girl);
        out.println();
        System.out.println();
    }

    public static void compare(Boy boy, Girl girl)
    {
        if(boy.getAge() > girl.getAge())
        {
            System.out.println("��ͯ��Ů��");
        }
        else
        {
            System.out.println("���氮������");
        }
    }
}
