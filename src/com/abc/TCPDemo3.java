package com.abc;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPDemo3 {

    @Test
    public void client() throws IOException {
        System.out.println("1");
        Socket socket = new Socket(InetAddress.getByName("localhost"), 9090);
        OutputStream os = socket.getOutputStream();
        FileInputStream fis = new FileInputStream(new File("phone.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while((len = fis.read(buffer)) != -1){
            os.write(buffer,0,len);
        }
        socket.shutdownOutput();
        InputStream is = socket.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[20];
        int len1;
        while((len1 = is.read(bytes)) != -1){
            baos.write(bytes,0,len1);
        }
        baos.close();
        os.close();
        fis.close();
        socket.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocket ss = new ServerSocket(9090);
        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream(new File("phone2.jpg"));
        byte[] buffer = new byte[1024];
        int len;
        while((len = is.read(buffer)) != -1){
            fos.write(buffer,0,len);
        }
        System.out.println("图片已收到");
        OutputStream os = socket.getOutputStream();
        os.write("发送成功".getBytes());

        socket.close();
        ss.close();
        is.close();
        fos.close();
        os.close();
    }

}
