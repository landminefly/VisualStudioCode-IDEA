package test2_3;

// public class test2_3_1
// {
//     @Test
//     public void test2_3_1_1()
//     {
//         // Object o = true ? Integer.valueOf(1) : Double.valueOf(2.0);
//         // System.out.println(o);
//
//         // Integer i = new Integer(1);
//         // Integer j = new Integer(1);
//         // System.out.println(i == j);
//         //
//         // Integer m = 1;
//         // Integer n = 1;
//         // System.out.println(m == n);
//         //
//         // Integer x = 128;
//         // Integer y = 128;
//         // System.out.println(x == y);
//
//         // Object o = new Integer(10);
//         // int num = (int)o;
//         // //�Զ�����plus
//     }
// }

// class test1
// {
//     public static void main(String[] args)
//     {
//         test2.main(new String[]{"123","456"});
//     }
//
// }
//
// class test2
// {
//     public static void main(String[] args)
//     {
//         for(int i = 0; i<args.length; i++)
//         {
//             System.out.println(args[i]);
//         }
//     }
// }

// public class test2_3_1
// {
//     final int LEFT;//��
//
//     final int RIGHT = 10;//��
//
//     final int UP;//��
//     {
//         UP = 20;
//     }
//
//     final int DOWN;//��
//     public test2_3_1()
//     {
//         DOWN = 30;
//     }
//
//     final int OUT;//��
//     public void setOUT()
//     {
//         OUT = 40;
//     }
//
// }

// class test1
// {
//     public static void main(String[] args)
//     {
//         Other o = new Other();
//         test1.add(o);
//     }
//     public static void add(final Other o)
//     {
//         o.i++;//��
//         // o = new Other();//��
//         //o��Ϊ�βα�final���Σ����������洢�ĵ�ֵַ���ܸı�
//         //���ǵ�ֵַ��ָ��Ķ�������ݻ��ǿ��Ըı��
//     }
// }
// class Other
// {
//     int i;
// }

public class test2_3_1
{
    public static void main(String[] args)
    {
        //���������ʵ������
        Creature c = new Creature()
        {
            @Override
            public void born()
            {
                System.out.println("̥��");
            }
        };
        test2_3_1.show(c);

        //�����������������
        test2_3_1.show(new Creature()
        {
            @Override
            public void born()
            {
                System.out.println("����");
            }
        });
    }
    public static void show(Creature c)
    {
        c.born();
    }
}

abstract class Creature
{
    public abstract void born();
}

