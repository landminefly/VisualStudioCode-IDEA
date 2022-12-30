package test2_4;

public class USBTest
{
    public static void main(String[] args)
    {
        Computer c = new Computer();
        c.transfer(new USBFlashDisk());//体现多态性

        //匿名实现类的实名对象
        USB usb = new USB()
        {
            @Override
            public void start()
            {
                System.out.println("starting work...");
            }
            @Override
            public void end()
            {
                System.out.println("ending work...");
            }
        };
        c.transfer(usb);
        //匿名实现类的匿名对象
        c.transfer(new USB()
        {
            @Override
            public void start()
            {
                System.out.println("starting work...");
            }
            @Override
            public void end()
            {
                System.out.println("ending work...");
            }
        });
    }
}
class Computer
{
    public void transfer(USB usb)
    {
        usb.start();
        System.out.println("transferring data...");
        usb.end();
    }
}
interface USB
{
    void start();
    void end();
}
class USBFlashDisk implements USB
{
    @Override
    public void start()
    {
        System.out.println("starting work...");
    }
    @Override
    public void end()
    {
        System.out.println("ending work...");
    }
}
