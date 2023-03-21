package com.liang.argorithm.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liangbingtian
 * @date 2023/03/10 下午3:27
 */
public class HttpServer01 {

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(8801);
    Socket accept = serverSocket.accept();
    new Thread(()->{
      try {
        service(accept);
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
  }

  private static void service(Socket socket) throws IOException, InterruptedException {
    Thread.sleep(20);
    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
    printWriter.println("HTTP/1.1 200 OK");
    printWriter.println("Content-Type:test/html;charset=utf-8");
    printWriter.println();
    printWriter.write("hello, nio");
    printWriter.close();
    socket.close();
  }

}
