package test7_1;

import org.junit.jupiter.api.Test;

public class IAmFuckingComeBack
{
    public static void main(String[] args)
    {
        StringBuilder stringBuilder = new StringBuilder("Hello World!");
        //toString()
        String string1 = stringBuilder.toString();
        //subString()
        String substring = stringBuilder.substring(0);
        //String构造器：底层调用的还是toString()
        String string2 = new String(stringBuilder);
    }
    @Test
    public void test1()
    {
        //判断浮点型是否相等
        //由于浮点型的存储机制，计算会出现误差，因此不能直接进行比较
        //二者相减小于某个很小的数就可以判断为相等
        System.out.println(0.47+0.44+0.19);//应该是1.1，但输出是1.0999999999999999
        boolean flag1 = (0.47+0.44+0.19 - 1.1) < 1e-6;//√
        boolean flag2 = 0.47+0.44+0.19 == 1.1;//×

    }

}
