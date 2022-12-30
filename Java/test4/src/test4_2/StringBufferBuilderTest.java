package test4_2;

import org.junit.Test;

/**
 * ����StringBuffer��StringBuilder��ʹ��
 *
 * @author shkstart
 * @create 2019 ���� 3:32
 */
public class StringBufferBuilderTest {
    /*
    �Ա�String��StringBuffer��StringBuilder���ߵ�Ч�ʣ�
    �Ӹߵ������У�StringBuilder > StringBuffer > String
     */
    @Test
    public void test3(){
        //��ʼ����
        long startTime = 0L;
        long endTime = 0L;
        String text = "";
        StringBuffer buffer = new StringBuffer("");
        StringBuilder builder = new StringBuilder("");
        //��ʼ�Ա�
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            buffer.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer��ִ��ʱ�䣺" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            builder.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder��ִ��ʱ�䣺" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 200000; i++) {
            text = text + i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("String��ִ��ʱ�䣺" + (endTime - startTime));

    }

    /*
    StringBuffer�ĳ��÷�����
StringBuffer append(xxx)���ṩ�˺ܶ��append()���������ڽ����ַ���ƴ��
StringBuffer delete(int start,int end)��ɾ��ָ��λ�õ�����
StringBuffer replace(int start, int end, String str)����[start,end)λ���滻Ϊstr
StringBuffer insert(int offset, xxx)����ָ��λ�ò���xxx
StringBuffer reverse() ���ѵ�ǰ�ַ�������ת
public int indexOf(String str)
public String substring(int start,int end):����һ����start��ʼ��end��������������ҿ���������ַ���
public int length()
public char charAt(int n )
public void setCharAt(int n ,char ch)

        �ܽ᣺
        ����append(xxx)
        ɾ��delete(int start,int end)
        �ģ�setCharAt(int n ,char ch) / replace(int start, int end, String str)
        �飺charAt(int n )
        �壺insert(int offset, xxx)
        ���ȣ�length();
        *������for() + charAt() / toString()
     */
    @Test
    public void test2(){
        StringBuffer s1 = new StringBuffer("abc");
        s1.append(1);
        s1.append('1');
        System.out.println(s1);
//        s1.delete(2,4);
//        s1.replace(2,4,"hello");
//        s1.insert(2,false);
//        s1.reverse();
        String s2 = s1.substring(1, 3);
        System.out.println(s1);
        System.out.println(s1.length());
        System.out.println(s2);
    }


    /*
    String��StringBuffer��StringBuilder���ߵ���ͬ��
    String:���ɱ���ַ����У��ײ�ʹ��char[]�洢
    StringBuffer:�ɱ���ַ����У��̰߳�ȫ�ģ�Ч�ʵͣ��ײ�ʹ��char[]�洢
    StringBuilder:�ɱ���ַ����У�jdk5.0�����ģ��̲߳���ȫ�ģ�Ч�ʸߣ��ײ�ʹ��char[]�洢

    Դ�������
    String str = new String();//char[] value = new char[0];
    String str1 = new String("abc");//char[] value = new char[]{'a','b','c'};

    StringBuffer sb1 = new StringBuffer();//char[] value = new char[16];�ײ㴴����һ��������16�����顣
    System.out.println(sb1.length());//
    sb1.append('a');//value[0] = 'a';
    sb1.append('b');//value[1] = 'b';

    StringBuffer sb2 = new StringBuffer("abc");//char[] value = new char["abc".length() + 16];

    //����1. System.out.println(sb2.length());//3
    //����2. ��������:���Ҫ��ӵ����ݵײ�����ʢ�����ˣ��Ǿ���Ҫ���ݵײ�����顣
             Ĭ������£�����Ϊԭ��������2�� + 2��ͬʱ��ԭ�������е�Ԫ�ظ��Ƶ��µ������С�

            ָ�����壺�����н�����ʹ�ã�StringBuffer(int capacity) �� StringBuilder(int capacity)


     */
    @Test
    public void test1(){
        StringBuffer sb1 = new StringBuffer("abc");
        sb1.setCharAt(0,'m');
        System.out.println(sb1);

        StringBuffer sb2 = new StringBuffer();
        System.out.println(sb2.length());//0
    }

}
