package test4_3;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * һ��˵����Java�еĶ�����������£�ֻ�ܽ��бȽϣ�==  ��  != ������ʹ�� > �� < ��
 *          �����ڿ��������У�������Ҫ�Զ�����������������֮�⣬����Ҫ�Ƚ϶���Ĵ�С��
 *          ���ʵ�֣�ʹ�������ӿ��е��κ�һ����Comparable �� Comparator
 *
 * ����Comparable�ӿ���Comparator��ʹ�õĶԱȣ�
 *    Comparable�ӿڵķ�ʽһ��һ������֤Comparable�ӿ�ʵ����Ķ������κ�λ�ö����ԱȽϴ�С��
 *    Comparator�ӿ�������ʱ�ԵıȽϡ�
 *
 *thor shkstart
 * @create 2019 ���� 4:41
 */
public class CompareTest {
    /*
    Comparable�ӿڵ�ʹ�þ�����  ��Ȼ����
    1.��String����װ���ʵ����Comparable�ӿڣ���д��compareTo(obj)�����������˱Ƚ����������С�ķ�ʽ��
    2.��String����װ����дcompareTo()�����Ժ󣬽����˴�С���������
    3. ��дcompareTo(obj)�Ĺ���
        �����ǰ����this�����βζ���obj���򷵻���������
        �����ǰ����thisС���βζ���obj���򷵻ظ�������
        �����ǰ����this�����βζ���obj���򷵻��㡣
    4. �����Զ�������˵�������Ҫ�������ǿ������Զ�����ʵ��Comparable�ӿڣ���дcompareTo(obj)������
       ��compareTo(obj)������ָ���������
     */
    @Test
    public void test1(){
        String[] arr = new String[]{"AA","CC","KK","MM","GG","JJ","DD"};
        //
        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));

    }

    @Test
    public void test2(){
        Goods[] arr = new Goods[5];
        arr[0] = new Goods("lenovoMouse",34);
        arr[1] = new Goods("dellMouse",43);
        arr[2] = new Goods("xiaomiMouse",12);
        arr[3] = new Goods("huaweiMouse",65);
        arr[4] = new Goods("microsoftMouse",43);

        Arrays.sort(arr);

        System.out.println(Arrays.toString(arr));
    }

    /*
    Comparator�ӿڵ�ʹ�ã���������
    1.������
    ��Ԫ�ص�����û��ʵ��java.lang.Comparable�ӿڶ��ֲ������޸Ĵ��룬
    ����ʵ����java.lang.Comparable�ӿڵ���������ʺϵ�ǰ�Ĳ�����
    ��ô���Կ���ʹ�� Comparator �Ķ���������
    2.��дcompare(Object o1,Object o2)�������Ƚ�o1��o2�Ĵ�С��
    ����������������������ʾo1����o2��
    �������0����ʾ��ȣ�
    ���ظ���������ʾo1С��o2��

     */
    @Test
    public void test3(){
        String[] arr = new String[]{"AA","CC","KK","MM","GG","JJ","DD"};
        Arrays.sort(arr,new Comparator()
        {
            //�����ַ����Ӵ�С��˳������
            @Override
            public int compare(Object o1, Object o2)
            {
                if(o1 instanceof String && o2 instanceof String){
                    String s1 = (String) o1;
                    String s2 = (String) o2;
                    return -s1.compareTo(s2);
                }
                throw new RuntimeException("������������Ͳ�һ��");
            }
        });
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test4(){
        Goods[] arr = new Goods[6];
        arr[0] = new Goods("lenovoMouse",34);
        arr[1] = new Goods("dellMouse",43);
        arr[2] = new Goods("xiaomiMouse",12);
        arr[3] = new Goods("huaweiMouse",65);
        arr[4] = new Goods("huaweiMouse",224);
        arr[5] = new Goods("microsoftMouse",43);

        Arrays.sort(arr, new Comparator() {
            //ָ����Ʒ�Ƚϴ�С�ķ�ʽ:���ղ�Ʒ���ƴӵ͵�������,�ٰ��ռ۸�Ӹߵ�������
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Goods && o2 instanceof Goods){
                    Goods g1 = (Goods)o1;
                    Goods g2 = (Goods)o2;
                    if(g1.getName().equals(g2.getName())){
                        return -Double.compare(g1.getPrice(),g2.getPrice());
                    }else{
                        return g1.getName().compareTo(g2.getName());
                    }
                }
                throw new RuntimeException("������������Ͳ�һ��");
            }
        });

        System.out.println(Arrays.toString(arr));
    }

}
