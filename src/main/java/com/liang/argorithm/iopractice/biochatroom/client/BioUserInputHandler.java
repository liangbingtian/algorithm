package com.liang.argorithm.iopractice.biochatroom.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 处理用户输入相关的处理类
 *
 * @author liangbingtian
 * @date 2022/01/16 下午7:09
 */
public class BioUserInputHandler implements Runnable {

  private final BioChatClient bioChatClient;

  public BioUserInputHandler(BioChatClient bioChatClient) {
    this.bioChatClient = bioChatClient;
  }

  @Override
  public void run() {
    try {
      String msg = null;
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      while ((msg = reader.readLine()) != null) {
        bioChatClient.send(msg);
        if (bioChatClient.readyToQuit(msg)) {
          break;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
