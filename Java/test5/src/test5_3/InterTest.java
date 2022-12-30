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

    //客户端
    @Test
    public void client() throws IOException
    {
        //1.创建Socket对象，指明服务器端的ip和端口号
        InetAddress inetAddress = InetAddress.getLocalHost();
        Socket socket = new Socket(inetAddress, 8888);
        //2.获取一个输出流，并输出数据
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("客户端：向服务器传输数据...".getBytes());
        //3.关闭数据的输出，否则接收端的read()会一直保持等待接收的状态，不会执行下面的代码
        socket.shutdownOutput();
        //4.接收接收端的传输完成信息
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        char[] buffer = new char[10];
        int length;
        while ((length = inputStreamReader.read(buffer)) != -1)
        {
            System.out.print(Arrays.copyOf(buffer, length));
        }
        //5.资源关闭
        socket.close();
        outputStream.close();
    }

    //服务器
    @Test
    public void service() throws IOException
    {
        //1.创建服务器端的ServerSocket，指明自己的端口号,负责等待客户端请求建立套接字连接
        ServerSocket serverSocket = new ServerSocket(8888);
        //2.调用accept()表示接收来自于客户端的socket
        Socket socket = serverSocket.accept();
        //3.获取输入流并接收数据
        InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
        char[] buffer = new char[10];
        int length;
        while ((length = inputStreamReader.read(buffer)) != -1)
        {
            System.out.print(Arrays.copyOf(buffer, length));
        }
        //4.向输出端发送传输完成信息
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("服务器：传输已完成！".getBytes());
        //5.资源关闭
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
