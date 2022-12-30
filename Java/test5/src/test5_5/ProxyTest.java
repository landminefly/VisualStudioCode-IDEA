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

//��������ʹ����඼Ҫʵ�ֵĽӿ�
interface Flyable
{
    String showSpeed();
    String showHeight(int height);
}

//��������1
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

//��������2
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

//������Ĵ���
class ProxyFactory
{
    public static Object getProxyInstance(Object targetObj)
    {
        MyInvocationHandler handler = new MyInvocationHandler(targetObj);
        //Proxy.newProxyInstance()�ܸ��ݱ�������������һ��������Ķ���
        //�����βηֱ�Ϊ����������������������������ʵ�ֵĽӿڣ�һ��ʵ��InvocationHandler�ӿڵ���Ķ���
        return Proxy.newProxyInstance(targetObj.getClass().getClassLoader(),
                                      targetObj.getClass().getInterfaces(),
                                      handler);
    }

}

//Proxy.newProxyInstance()��ִ��InvocationHandler�ӿ��е�invoke()���������Ҫʵ������ӿ�
class MyInvocationHandler implements InvocationHandler
{
    //��ʵ����Ķ���
    private Object targetObj;

    public MyInvocationHandler(Object targetObj)
    {
        this.targetObj = targetObj;
    }

    @Override
    //proxyΪ������Ķ��󣨴�ʱ�ö����Ѵ��ڣ���methodΪҪ���õķ�����argsΪҪ���õķ������β�
    //ͨ��������ķ���a�����ñ��������ͬ������a���߼�����invoke()��ʵ��
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        AOP aop = new AOP();
        aop.method1();//ͨ�÷���1
        Object invokeValue = method.invoke(targetObj, args);
        aop.method2();//ͨ�÷���2
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
