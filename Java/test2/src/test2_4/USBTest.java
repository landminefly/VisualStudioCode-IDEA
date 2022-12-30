package test2_4;

public class USBTest
{
    public static void main(String[] args)
    {
        Computer c = new Computer();
        c.transfer(new USBFlashDisk());//���ֶ�̬��

        //����ʵ�����ʵ������
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
        //����ʵ�������������
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
