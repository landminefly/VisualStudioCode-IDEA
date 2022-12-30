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
//         //System.out.println(x);//对 'x' 的引用不明确，'B.x' 和 'A.x' 均匹配
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
//         //创建静态内部类的对象
//         A.B b = new A.B();
//         //创建非静态内部类的对象
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
//             System.out.println(name);//方法的形参
//             System.out.println(this.name);//内部类的属性
//             System.out.println(A.this.name);//外部类的属性
//         }
//     }
//     //返回一个实现了Comparable接口的类的对象
//     public Comparable getComparable()
//     {
//         //创建一个实现了Comparable接口的局部内部类
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

