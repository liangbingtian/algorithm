package com.liang.argorithm.iopractice.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author liangbingtian
 * @date 2022/01/14 下午7:52
 */
public class Client {

  public static void main(String[] args) {
    final String DEFAULT_SERVER_HOST = "127.0.0.1";
    final int DEFAULT_SERVER_PORT = 8888;
    Socket socket = null;
    BufferedWriter writer = null;
    BufferedReader reader = null;
    final String QUIT = "quit";
    try {
      socket = new Socket(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);
      //从socket里读出和写入流
      writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      //从控制台的输入信息中读取信息流
      BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
      while (true) {
        String input = consoleReader.readLine();

        writer.write(input + "\n");
        writer.flush();
        if (input.equals(QUIT)) {
          System.out.println("客户端连接已经关闭");
          break;
        }
        String msg = reader.readLine();
        System.out.println(msg);
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (writer != null) {
        try {
          System.out.println("关闭socket");
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
