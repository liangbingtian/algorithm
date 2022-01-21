package com.liang.argorithm.iopractice.niochatroom.server;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import org.springframework.util.StringUtils;

/**
 * 聊天室服务端
 *
 * @author liangbingtian
 * @date 2022/01/16 下午7:07
 */
public class NioChatServer {

  private static final int DEFAULT_PORT = 8888;
  private final String QUIT = "quit";
  private static final int BUFFER = 1024;

  private ServerSocketChannel server;
  private Selector selector;
  private ByteBuffer rBuffer = ByteBuffer.allocate(BUFFER);
  private ByteBuffer wBuffer = ByteBuffer.allocate(BUFFER);
  private Charset charset = StandardCharsets.UTF_8;
  private int port;

  public NioChatServer() {
    this(DEFAULT_PORT);
  }

  public NioChatServer(int port) {
    this.port = port;
  }

  //服务端启动的主要方法
  public void start() {
    try {
      server = ServerSocketChannel.open();
      server.configureBlocking(false);
      server.socket().bind(new InetSocketAddress(port));

      selector = Selector.open();
      server.register(selector, SelectionKey.OP_ACCEPT);
      System.out.println("启动服务器，监听端口: " + port + "...");
      //调用select的时候是阻塞的
      while (true) {
        //无事件的话就是阻塞住的。如果触发了事件就继续走了，可以查找出触发的所有事件
        selector.select();
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        for (SelectionKey key : selectionKeys) {
          handles(key);
        }
        selectionKeys.clear();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      close(selector);
    }
  }

  private void handles(SelectionKey key) throws IOException {
    //ACCEPT事件-和客户端建立了链接
    if (key.isAcceptable()) {
      ServerSocketChannel server = (ServerSocketChannel) key.channel();
      SocketChannel client = server.accept();
      client.configureBlocking(false);
      client.register(selector, SelectionKey.OP_READ);
      System.out.println("客户端[" + client.socket().getPort() + "]已连接");
    }
    //READ事件-客户端发送过来了可读事件。
    else if (key.isReadable()) {
      SocketChannel client = (SocketChannel) key.channel();
      //读取信息
      String fwdMsg = receive(client);
      if (fwdMsg.isEmpty()) {
        key.cancel();
        //刷新一下
        selector.wakeup();
      } else {
        //转发数据
        forwardMessage(client, fwdMsg);
        if (readyToQuit(fwdMsg)) {
          key.cancel();
          selector.wakeup();
          System.out.println("客户端[" + client.socket().getPort() + "]已断开");
        }
      }
    }
  }

  private void forwardMessage(SocketChannel client, String fwdMsg) throws IOException {
    for (SelectionKey key : selector.keys()) {
      if (key.channel() instanceof ServerSocketChannel) {
        continue;
      }
      if (key.isValid() && !key.channel().equals(client)) {
        wBuffer.clear();
        wBuffer.put(charset.encode("客户端:["+client.socket().getPort() + ":" + fwdMsg));
        wBuffer.flip();
        while (wBuffer.hasRemaining()) {
          ((SocketChannel) key.channel()).write(wBuffer);
        }
      }
    }

  }

  private String receive(SocketChannel client) throws IOException {
    rBuffer.clear();
    //因为通道可能一直有数据所以判断不了哪个是末尾
    while (client.read(rBuffer) > 0);
    rBuffer.flip();
    return String.valueOf(charset.decode(rBuffer));
  }

  public void close(Closeable closeable) {
    if (closeable != null) {
      try {
        closeable.close();
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
    NioChatServer nioChatServer = new NioChatServer();
    nioChatServer.start();
  }
}
