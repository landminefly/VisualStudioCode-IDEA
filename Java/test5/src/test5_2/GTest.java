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
        // arrayList = arrayList1;//��
    }
}

class Father<T1,T2>{}

//���಻�������෺��
//����
class Son1 extends Father{}
//ָ��
class Son2 extends Father<Integer,String>{}
//�½�+����
class Son3<K1,K2> extends Father{}
//�½�+ָ��
class Son4<K1,K2> extends Father<Integer,String>{}

//���ౣ�����෺��
//ȫ������
class Son5<T1,T2> extends Father<T1,T2>{}
//���ֱ���+ָ��
class Son6<T2> extends Father<Integer,T2>{}
//�½�+ȫ������
class Son7<K1,K2,T1,T2> extends Father<T1,T2>{}
//�½�+���ֱ���+ָ��
class Son8<K1,K2,T2> extends Father<Integer,T2>{}