package com.liang.argorithm.iopractice.niochatroom.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;

/**
 * 聊天室的客户端
 *
 * @author liangbingtian
 * @date 2022/01/16 下午7:09
 */
public class NioChatClient {

  private static final String DEFAULT_SERVER_IP = "127.0.0.1";
  private static final int DEFAULT_SERVER_PORT = 8888;
  public static final int BUFFER = 1024;
  private static final String QUIT = "quit";

  private SocketChannel client;
  private ByteBuffer wBuffer = ByteBuffer.allocate(1024);
  private ByteBuffer rBuffer = ByteBuffer.allocate(1024);
  private Selector selector;
  private Charset charset = StandardCharsets.UTF_8;
  private String host;
  private int port;

  public NioChatClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public NioChatClient() {
    this(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT);
  }

  public void send(String msg) throws IOException {
    if (msg.isEmpty()) {
      return;
    }
    wBuffer.clear();
    wBuffer.put(charset.encode(msg));
    wBuffer.flip();
    while (wBuffer.hasRemaining()) {
      client.write(wBuffer);
    }
    //退出客户端
    if (readyToQuit(msg)) {
      close(selector);
    }
  }

  public String receive(SocketChannel client) throws IOException {
    rBuffer.clear();
    while(client.read(rBuffer)>0);
    rBuffer.flip();
    return String.valueOf(charset.decode(rBuffer));
  }

  public boolean readyToQuit(String msg) {
    return QUIT.equals(msg);
  }

  public void close(Closeable closeable) {
    if (closeable!=null) {
      try {
        System.out.println("关闭socket");
        closeable.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void start() {
    try {
     client = SocketChannel.open();
     client.configureBlocking(false);
     selector = Selector.open();
     client.register(selector, SelectionKey.OP_CONNECT);
     //其实connect也是阻塞的方法
     client.connect(new InetSocketAddress(host, port));
     //阻塞调用select
      while(true) {
        selector.select();
        Set<SelectionKey> keys = selector.selectedKeys();
        for (SelectionKey key : keys) {
          handles(key);
        }
        keys.clear();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      close(selector);
    }
  }

  private void handles(SelectionKey key) throws IOException {
    //Connect 连接就绪事件
    if (key.isConnectable()) {
      SocketChannel client = (SocketChannel) key.channel();
      if (client.isConnectionPending()) {
        client.finishConnect();
        //处理用户输入还是要令开线程，因为用户输入必须得阻塞，而且不能让他阻塞住主线程的selector
        new Thread(new NioUserInputHandler(this)).start();
      }
      client.register(selector, SelectionKey.OP_READ);
    }
    //
    else if (key.isReadable()) {
      SocketChannel client = (SocketChannel) key.channel();
      String msg = receive(client);
      if (msg.isEmpty()) {
        //退出客户端
        close(selector);
      }else{
        System.out.println(msg);
      }
    }

  }

  public static void main(String[] args) {
    new NioChatClient().start();
  }
}
