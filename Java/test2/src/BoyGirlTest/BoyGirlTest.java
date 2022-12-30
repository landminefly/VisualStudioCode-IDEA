package BoyGirlTest;

import static java.lang.System.*;

public class BoyGirlTest
{
    public static void main(String[] args)
    {
        Boy boy = new Boy("奥托",500);
        Girl girl = new Girl("卡莲",20);

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
            System.out.println("金童玉女捏");
        }
        else
        {
            System.out.println("是真爱就行啦");
        }
    }
}
