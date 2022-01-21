package com.liang.argorithm.iopractice.niochatroom.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 处理用户输入相关的处理类
 *
 * @author liangbingtian
 * @date 2022/01/16 下午7:09
 */
public class NioUserInputHandler implements Runnable {

  private final NioChatClient nioChatClient;

  public NioUserInputHandler(NioChatClient nioChatClient) {
    this.nioChatClient = nioChatClient;
  }

  @Override
  public void run() {
    try {
      String msg = null;
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      while ((msg = reader.readLine()) != null) {
        nioChatClient.send(msg);
        if (nioChatClient.readyToQuit(msg)) {
          break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
