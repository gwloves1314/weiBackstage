package com.wei.backstage.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by gongw on 2017/3/8.
 */
public class NioClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(NioClient.class);
    private final Selector selector;

    public NioClient(int port) throws Exception {
        this.selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(InetAddress.getLocalHost(), port));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    public void listen() throws Exception {
        System.out.println("正在建立连接.....");
        while (true) {
            LOGGER.info("正在扫描客户端准备好的selector.....");
            int selectCount = selector.select();
//            LOGGER.info("准备好的通道:"+selectCount);
            if (selectCount != 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
//                    LOGGER.info("begin doing......");
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    if (selectionKey.isConnectable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        socketChannel.finishConnect();
                        socketChannel.configureBlocking(false);
                        LOGGER.info("客户端准备发送数据....");
                        socketChannel.write(ByteBuffer.wrap(new String("这是客户端发送的你好数据....").getBytes()));
//                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        readServerMsg(selectionKey);
                    }
                }
            }
        }
    }

    public void readServerMsg(SelectionKey selectionKey) throws Exception {
        while (selectionKey.isReadable()) {
            int selectedCount = selector.select();
            if (selectedCount != 0) {
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                socketChannel.configureBlocking(false);
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                socketChannel.read(byteBuffer);
                byte[] msgByte = byteBuffer.array();
                String msg = new String(msgByte).trim();
                if (!"".equals(msg)) {
                    System.out.println("客户端收取的信息:" + msg);
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    System.out.println("请输入客户端应答信息：");
                    byteBuffer.clear();
                    byteBuffer.flip();
                    Thread.sleep(2000);
                    socketChannel.write(ByteBuffer.wrap(br.readLine().getBytes()));
                    socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                } else {
                    continue;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        NioClient nioClient = new NioClient(8080);
        nioClient.listen();
    }
}
