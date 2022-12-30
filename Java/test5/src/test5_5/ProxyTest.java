package test5_5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest
{
    public static void main(String[] args)
    {
        Airplane airplane = new Airplane();
        Flyable proxyInstance1 = (Flyable) ProxyFactory.getProxyInstance(airplane);
        System.out.println(proxyInstance1.showSpeed());
        System.out.println(proxyInstance1.showHeight(10000));

        System.out.println();

        Bird bird = new Bird();
        Flyable proxyInstance2 = (Flyable) ProxyFactory.getProxyInstance(bird);
        System.out.println(proxyInstance2.showSpeed());
        System.out.println(proxyInstance2.showHeight(5000));

    }
}

//被代理类和代理类都要实现的接口
interface Flyable
{
    String showSpeed();
    String showHeight(int height);
}

//被代理类1
class Airplane implements Flyable
{
    @Override
    public String showSpeed()
    {
        return "Its flight speed can reach 1000 km/h.";
    }

    @Override
    public String showHeight(int height)
    {
        return "Its flight height can reach " + height + " m.";
    }
}

//被代理类2
class Bird implements Flyable
{
    @Override
    public String showSpeed()
    {
        return "Its flight speed can reach 80 km/h.";
    }

    @Override
    public String showHeight(int height)
    {
        return "Its flight height can reach " + height + " m.";
    }
}

//代理类的创建
class ProxyFactory
{
    public static Object getProxyInstance(Object targetObj)
    {
        MyInvocationHandler handler = new MyInvocationHandler(targetObj);
        //Proxy.newProxyInstance()能根据被代理类来创建一个代理类的对象
        //三个形参分别为：被代理类的类加载器，被代理类实现的接口，一个实现InvocationHandler接口的类的对象
        return Proxy.newProxyInstance(targetObj.getClass().getClassLoader(),
                                      targetObj.getClass().getInterfaces(),
                                      handler);
    }

}

//Proxy.newProxyInstance()会执行InvocationHandler接口中的invoke()方法，因此要实现这个接口
class MyInvocationHandler implements InvocationHandler
{
    //被实现类的对象
    private Object targetObj;

    public MyInvocationHandler(Object targetObj)
    {
        this.targetObj = targetObj;
    }

    @Override
    //proxy为代理类的对象（此时该对象已存在），method为要调用的方法，args为要调用的方法的形参
    //通过代理类的方法a来调用被代理类的同名方法a的逻辑就在invoke()内实现
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        AOP aop = new AOP();
        aop.method1();//通用方法1
        Object invokeValue = method.invoke(targetObj, args);
        aop.method2();//通用方法2
        return invokeValue;
    }
}

class AOP
{
    public void method1()
    {
        System.out.println("This is method1 in AOP class");
    }
    public void method2()
    {
        System.out.println("This is method2 in AOP class");
    }
}
