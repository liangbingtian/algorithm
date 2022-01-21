package com.liang.argorithm.iopractice.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liangbingtian
 * @date 2022/01/14 上午10:11
 */
public class Server {

  public static void main(String[] args){
    final int DEFAULT_PORT = 8888;
    final String QUIT = "quit";
    ServerSocket serverSocket = null;
    //绑定监听端口
    try {
      serverSocket = new ServerSocket(DEFAULT_PORT);
      System.out.println("启动服务器，监听端口8888");
      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("客户端[" + socket.getPort() + "]已连接");
        //传输数据流
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String msg = null;
        while((msg = reader.readLine())!=null) {
          System.out.println("客户端[" + socket.getPort() + "]:" + msg);
          writer.write("服务器:" + msg + "\n");
          //BufferedWriter缓冲区需要清理缓冲。
          writer.flush();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (serverSocket!=null) {
        try {
          serverSocket.close();
          System.out.println("关闭ServerSocket");
        }catch (IOException e) {
          e. printStackTrace();
        }
      }
    }
  }
}
