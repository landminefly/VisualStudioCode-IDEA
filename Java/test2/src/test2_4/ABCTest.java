package test2_4;

// interface A
// {
//     int x = 1;
// }
// class B
// {
//     int x = 2;
// }
// class C extends B implements A
// {
//     public void show()
//     {
//         //System.out.println(x);//�� 'x' �����ò���ȷ��'B.x' �� 'A.x' ��ƥ��
//         System.out.println(super.x);//2
//         System.out.println(A.x);//1
//     }
//     public static void main(String[] args)
//     {
//         new C().show();
//     }
// }
//
// interface A
// {
//     default void Method()
//     {
//         System.out.println("a");
//     }
// }
// class B
// {
//     public void Method()
//     {
//         System.out.println("b");
//     }
// }
// class C extends B implements A
// {
//     public void show()
//     {
//         Method();
//     }
//     public static void main(String[] args)
//     {
//         new C().show();
//     }
// }

// public class ABCTest
// {
//     public static void main(String[] args)
//     {
//         //������̬�ڲ���Ķ���
//         A.B b = new A.B();
//         //�����Ǿ�̬�ڲ���Ķ���
//         A a = new A();
//         A.C c = a.new C();
//
//     }
// }
// class A
// {
//     static class B
//     {
//         public void show()
//         {
//             System.out.println("b");
//         }
//     }
//     class C
//     {
//         public void show()
//         {
//             System.out.println("c");
//         }
//     }
// }

// public class ABCTest
// {
//     public static void main(String[] args)
//     {
//         A a = new A();
//         A.B b = a.new B();
//         b.show("c");
//     }
// }
// class A
// {
//     String name = "a";
//     class B
//     {
//         String name = "b";
//         public void show(String name)
//         {
//             System.out.println(name);//�������β�
//             System.out.println(this.name);//�ڲ��������
//             System.out.println(A.this.name);//�ⲿ�������
//         }
//     }
//     //����һ��ʵ����Comparable�ӿڵ���Ķ���
//     public Comparable getComparable()
//     {
//         //����һ��ʵ����Comparable�ӿڵľֲ��ڲ���
//         class MyComparable implements Comparable
//         {
//             @Override
//             public int compareTo(Object o)
//             {
//                 return 0;
//             }
//         }
//         return new MyComparable();
//     }
//
// }

class A
{
    public void B()
    {
        final int[] num = {10};
        class C
        {
            public void D()
            {
                num[0] = 20;
            }
        }
    }
}

