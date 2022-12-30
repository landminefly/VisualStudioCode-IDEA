package test5_3;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.*;
import java.util.Arrays;

public class InterTest
{
    @Test
    public void test1() throws UnknownHostException
    {
        InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
        System.out.println(inetAddress);
        InetAddress inetAddress1 = InetAddress.getLocalHost();
        System.out.println(inetAddress1);
        InetAddress inetAddress2 = InetAddress.getByName("127.0.0.1");
        System.out.println(inetAddress2);
    }

    //�ͻ���
    @Test
    public void client() throws IOException
    {
        //1.����Socket����ָ���������˵�ip�Ͷ˿ں�
        InetAddress inetAddress = InetAddress.getLocalHost();
        Socket socket = new Socket(inetAddress, 8888);
        //2.��ȡһ������������������
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("�ͻ��ˣ����������������...".getBytes());
        //3.�ر����ݵ������������ն˵�read()��һֱ���ֵȴ����յ�״̬������ִ������Ĵ���
        socket.shutdownOutput();
        //4.���ս��ն˵Ĵ��������Ϣ
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        char[] buffer = new char[10];
        int length;
        while ((length = inputStreamReader.read(buffer)) != -1)
        {
            System.out.print(Arrays.copyOf(buffer, length));
        }
        //5.��Դ�ر�
        socket.close();
        outputStream.close();
    }

    //������
    @Test
    public void service() throws IOException
    {
        //1.�����������˵�ServerSocket��ָ���Լ��Ķ˿ں�,����ȴ��ͻ����������׽�������
        ServerSocket serverSocket = new ServerSocket(8888);
        //2.����accept()��ʾ���������ڿͻ��˵�socket
        Socket socket = serverSocket.accept();
        //3.��ȡ����������������
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        char[] buffer = new char[10];
        int length;
        while ((length = inputStreamReader.read(buffer)) != -1)
        {
            System.out.print(Arrays.copyOf(buffer, length));
        }
        //4.������˷��ʹ��������Ϣ
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("����������������ɣ�".getBytes());
        //5.��Դ�ر�
        inputStreamReader.close();
        socket.close();
        serverSocket.close();
    }

    @Test
    public void sender() throws IOException
    {
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress inetAddress = InetAddress.getLocalHost();
        byte[] data = "I am sender.".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(data, 0, data.length, inetAddress, 1145);
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }

    @Test
    public void receiver() throws IOException
    {
        DatagramSocket datagramSocket = new DatagramSocket(1145);
        byte[] data = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(data,0,data.length);
        datagramSocket.receive(datagramPacket);
        System.out.println(new String(datagramPacket.getData()));
        datagramSocket.close();
    }
}
