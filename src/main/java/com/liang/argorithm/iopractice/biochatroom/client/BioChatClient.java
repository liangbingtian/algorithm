package com.liang.argorithm.iopractice.biochatroom.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 聊天室的客户端
 *
 * @author liangbingtian
 * @date 2022/01/16 下午7:09
 */
public class BioChatClient {

  private final String DEFAULT_SERVER_IP = "127.0.0.1";
  private final int DEFAULT_SERVER_PORT = 8888;
  private final String QUIT = "quit";

  private Socket socket;
  private BufferedReader reader;
  private BufferedWriter writer;

  public void send(String msg) throws IOException {
    if (!socket.isOutputShutdown()) {
      writer.write(msg+"\n");
      writer.flush();
    }
  }

  public String receive() throws IOException {
    String msg = null;
    if (!socket.isInputShutdown()) {
      msg = reader.readLine();
    }
    return msg;
  }

  public boolean readyToQuit(String msg) {
    return QUIT.equals(msg);
  }

  public void close() {
    if (writer!=null) {
      try {
        System.out.println("关闭socket");
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void start() {
    try {
      socket = new Socket(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT);
      reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      new Thread(new BioUserInputHandler(this)).start();
      String msg = null;
      while ((msg = receive())!=null) {
        System.out.println(msg);
      }

    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      close();
    }
  }

  public static void main(String[] args) {
    new BioChatClient().start();
  }
}
