package com.liang.argorithm.iopractice.biochatroom.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 服务端阻塞时候处理其它情形的处理类
 *
 * @author liangbingtian
 * @date 2022/01/16 下午7:08
 */
public class BioChatHandler implements Runnable {

  private final BioChatServer bioChatServer;
  private final Socket socket;

  public BioChatHandler(BioChatServer bioChatServer, Socket socket) {
    this.bioChatServer = bioChatServer;
    this.socket = socket;
  }

  @Override
  public void run() {
    try {
      bioChatServer.addClient(socket);
      BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String msg = null;
      //客户端退出了，reader读取的就是null,也自然而然退出了
      while ((msg = reader.readLine()) != null) {
        String fwdMsg = "客户端[" + socket.getPort() + "]:" + msg +"\n";
        //转发消息
        bioChatServer.forwardMessage(socket, fwdMsg);
        //如果发送了退出的话，那么用户下线，结束运行
        if (bioChatServer.readyToQuit(msg)) {
          break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        bioChatServer.removeClient(socket);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
