package test4_2;

import org.junit.Test;

/**
 * @author shkstart
 * @create 2019 ���� 11:52
 */
public class StringMethodTest {

    /*
�滻��
String replace(char oldChar, char newChar)������һ���µ��ַ���������ͨ���� newChar �滻���ַ����г��ֵ����� oldChar �õ��ġ�
String replace(CharSequence target, CharSequence replacement)��ʹ��ָ��������ֵ�滻�����滻���ַ�������ƥ������ֵĿ�����е����ַ�����
String replaceAll(String regex, String replacement)��ʹ�ø����� replacement �滻���ַ�������ƥ�������������ʽ�����ַ�����
String replaceFirst(String regex, String replacement)��ʹ�ø����� replacement �滻���ַ���ƥ�������������ʽ�ĵ�һ�����ַ�����
ƥ��:
boolean matches(String regex)����֪���ַ����Ƿ�ƥ�������������ʽ��
��Ƭ��
String[] split(String regex)�����ݸ���������ʽ��ƥ���ִ��ַ�����
String[] split(String regex, int limit)������ƥ�������������ʽ����ִ��ַ�������಻����limit������������ˣ�ʣ�µ�ȫ�����ŵ����һ��Ԫ���С�

     */
    @Test
    public void test4(){
        String str1 = "�����й�Ƚ�������";
        String str2 = str1.replace('��', '��');

        System.out.println(str1);
        System.out.println(str2);


        String str3 = str1.replace("����", "�Ϻ�");
        System.out.println(str3);

        System.out.println("*************************");
        String str = "12hello34world5java7891mysql456";
        //���ַ����е������滻��,���������п�ͷ�ͽ�β�У��Ļ�ȥ��
        String string = str.replaceAll("\\d+", ",").replaceAll("^,|,$", "");
        System.out.println(string);

        System.out.println("*************************");
        str = "12345";
        //�ж�str�ַ������Ƿ�ȫ����������ɣ�����1-n���������
        boolean matches = str.matches("\\d+");
        System.out.println(matches);
        String tel = "0571-4534289";
        //�ж����Ƿ���һ�����ݵĹ̶��绰
        boolean result = tel.matches("0571-\\d{7,8}");
        System.out.println(result);


        System.out.println("*************************");
        str = "hello|world|java";
        String[] strs = str.split("\\|");
        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }
        System.out.println();
        str2 = "hello.world.java";
        String[] strs2 = str2.split("\\.");
        for (int i = 0; i < strs2.length; i++) {
            System.out.println(strs2[i]);
        }

    }

    /*
boolean endsWith(String suffix)�����Դ��ַ����Ƿ���ָ���ĺ�׺����
boolean startsWith(String prefix)�����Դ��ַ����Ƿ���ָ����ǰ׺��ʼ
boolean startsWith(String prefix, int toffset)�����Դ��ַ�����ָ��������ʼ�����ַ����Ƿ���ָ��ǰ׺��ʼ

boolean contains(CharSequence s)�����ҽ������ַ�������ָ���� char ֵ����ʱ������ true
int indexOf(String str)������ָ�����ַ����ڴ��ַ����е�һ�γ��ִ�������
int indexOf(String str, int fromIndex)������ָ�����ַ����ڴ��ַ����е�һ�γ��ִ�����������ָ����������ʼ
int lastIndexOf(String str)������ָ�����ַ����ڴ��ַ��������ұ߳��ִ�������
int lastIndexOf(String str, int fromIndex)������ָ�����ַ����ڴ��ַ��������һ�γ��ִ�����������ָ����������ʼ��������

ע��indexOf��lastIndexOf�������δ�ҵ����Ƿ���-1

     */
    @Test
    public void test3(){
        String str1 = "hellowworld";
        boolean b1 = str1.endsWith("rld");
        System.out.println(b1);

        boolean b2 = str1.startsWith("He");
        System.out.println(b2);

        boolean b3 = str1.startsWith("ll",2);
        System.out.println(b3);

        String str2 = "wor";
        System.out.println(str1.contains(str2));

        System.out.println(str1.indexOf("lol"));

        System.out.println(str1.indexOf("lo",5));

        String str3 = "hellorworld";

        System.out.println(str3.lastIndexOf("or"));
        System.out.println(str3.lastIndexOf("or",6));

        //ʲô����£�indexOf(str)��lastIndexOf(str)����ֵ��ͬ��
        //���һ������Ψһ��һ��str���������������str
    }


    /*
int length()�������ַ����ĳ��ȣ� return value.length
char charAt(int index)�� ����ĳ���������ַ�return value[index]
boolean isEmpty()���ж��Ƿ��ǿ��ַ�����return value.length == 0
String toLowerCase()��ʹ��Ĭ�����Ի������� String �е������ַ�ת��ΪСд
String toUpperCase()��ʹ��Ĭ�����Ի������� String �е������ַ�ת��Ϊ��д
String trim()�������ַ����ĸ���������ǰ���հ׺�β���հ�
boolean equals(Object obj)���Ƚ��ַ����������Ƿ���ͬ
boolean equalsIgnoreCase(String anotherString)����equals�������ƣ����Դ�Сд
String concat(String str)����ָ���ַ������ӵ����ַ����Ľ�β�� �ȼ����á�+��
int compareTo(String anotherString)���Ƚ������ַ����Ĵ�С
String substring(int beginIndex)������һ���µ��ַ��������Ǵ��ַ����Ĵ�beginIndex��ʼ��ȡ������һ�����ַ�����
String substring(int beginIndex, int endIndex) ������һ�����ַ��������Ǵ��ַ�����beginIndex��ʼ��ȡ��endIndex(������)��һ�����ַ�����

     */
    @Test
    public void test2() {
        String s1 = "HelloWorld";
        String s2 = "helloworld";
        System.out.println(s1.equals(s2));
        System.out.println(s1.equalsIgnoreCase(s2));

        String s3 = "abc";
        String s4 = s3.concat("def");
        System.out.println(s4);

        String s5 = "abc";
        String s6 = new String("abe");
        System.out.println(s5.compareTo(s6));//�漰���ַ�������

        String s7 = "�����й�Ƚ���";
        String s8 = s7.substring(2);
        System.out.println(s7);
        System.out.println(s8);

        String s9 = s7.substring(2, 5);
        System.out.println(s9);
    }

    @Test
    public void test1() {
        String s1 = "HelloWorld";
        System.out.println(s1.length());
        System.out.println(s1.charAt(0));
        System.out.println(s1.charAt(9));
//        System.out.println(s1.charAt(10));
//        s1 = "";
        System.out.println(s1.isEmpty());

        String s2 = s1.toLowerCase();
        System.out.println(s1);//s1���ɱ�ģ���ȻΪԭ�����ַ���
        System.out.println(s2);//�ĳ�Сд�Ժ���ַ���

        String s3 = "   he  llo   world   ";
        String s4 = s3.trim();
        System.out.println("-----" + s3 + "-----");
        System.out.println("-----" + s4 + "-----");
    }
}
