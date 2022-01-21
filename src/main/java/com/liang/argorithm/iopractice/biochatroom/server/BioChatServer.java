package com.liang.argorithm.iopractice.biochatroom.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 聊天室服务端
 *
 * @author liangbingtian
 * @date 2022/01/16 下午7:07
 */
public class BioChatServer {

  private final int DEFAULT_PORT = 8888;
  private final String QUIT = "quit";

  private ServerSocket serverSocket = null;
  //用一个map来记录这个时间段在线的所有客户,key是客户端的端口号，value是Writer
  private final Map<Integer, Writer> connectedClients;
  private ExecutorService executorService;

  public BioChatServer() {
    executorService = Executors.newFixedThreadPool(10);
    connectedClients = new HashMap<>();
  }

  //将新上线的客户端加到map里面
  public synchronized void addClient(Socket socket) throws IOException {
    if (socket != null) {
      int port = socket.getPort();
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      connectedClients.put(port, writer);
      System.out.println("客户端[" + port + "]已经连接到了服务器");
    }
  }

  //处理要下线的客户端信息。
  public synchronized void removeClient(Socket socket) throws IOException {
    if (socket != null) {
      int port = socket.getPort();
      if (connectedClients.containsKey(port)) {
        connectedClients.get(port).close();
      }
      connectedClients.remove(port);
      System.out.println("客户端[" + port + "]已经断开连接");
    }
  }

  //转发消息
  public  void forwardMessage(Socket socket, String fwdMsg) throws IOException {
    for (Integer id : connectedClients.keySet()) {
      if (!id.equals(socket.getPort())) {
        Writer writer = connectedClients.get(id);
        writer.write(fwdMsg);
        writer.flush();
      }
    }
  }

  //服务端启动的主要方法
  public void start() {
    try {
      serverSocket = new ServerSocket(DEFAULT_PORT);
      System.out.println("服务器已经启动，正在监听端口:"+DEFAULT_PORT);
      while(true) {
        //阻塞
        Socket socket = serverSocket.accept();
        //为该连接的客户端创建一个线程,新建一个任务，提交给线程池
        executorService.execute(new BioChatHandler(this, socket));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      close();
    }
  }

  public void close() {
    if (serverSocket!=null) {
      try {
        serverSocket.close();
        System.out.println("关闭了ServerSocket");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  //检查用户是否要退出
  public boolean readyToQuit(String msg) {
    return msg.equals(QUIT);
  }

  public static void main(String[] args) {
    BioChatServer bioChatServer = new BioChatServer();
    bioChatServer.start();
  }
}
