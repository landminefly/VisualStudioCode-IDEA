package test5_2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

class GTest<K,V>{}

class Test1
{
    @Test
    public void test()
    {
        ArrayList<String> arrayList = null;
        ArrayList<Date> arrayList1 = null;
        // arrayList = arrayList1;//×
    }
}

class Father<T1,T2>{}

//子类不保留父类泛型
//擦除
class Son1 extends Father{}
//指定
class Son2 extends Father<Integer,String>{}
//新建+擦除
class Son3<K1,K2> extends Father{}
//新建+指定
class Son4<K1,K2> extends Father<Integer,String>{}

//子类保留父类泛型
//全部保留
class Son5<T1,T2> extends Father<T1,T2>{}
//部分保留+指定
class Son6<T2> extends Father<Integer,T2>{}
//新建+全部保留
class Son7<K1,K2,T1,T2> extends Father<T1,T2>{}
//新建+部分保留+指定
class Son8<K1,K2,T2> extends Father<Integer,T2>{}