package com.wei.backstage.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by gongw on 2017/3/8.
 */
public class NioServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(NioServer.class);
    private final Selector selector;

    public NioServer(int port) throws IOException {
        this.selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), port);
        serverSocketChannel.socket().bind(inetSocketAddress);
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() throws Exception {
        System.out.println("服务端启动成功.....");
        while (!Thread.interrupted()) {
            LOGGER.info("正在扫描服务器端准备好的selector.....");
            int selectCount = selector.select();
            LOGGER.info("服务器准备好的选择器:" + selectCount);
            if (selectCount != 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey selectionKey = keyIterator.next();
                    keyIterator.remove();
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
//                    serverSocketChannel.configureBlocking(false);
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.write(ByteBuffer.wrap(new String("收到连接请求....").getBytes()));
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        LOGGER.info("服务端已发送数据....");
                    } else if (selectionKey.isReadable()) {
                        //有读取的权限
                        readMsg(selectionKey);
                    }
                }
            }
        }
    }

    public void readMsg(SelectionKey selectionKey) throws Exception {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //从管道中读取数据到buffer中
        socketChannel.read(byteBuffer);
        byte[] bytes = byteBuffer.array();
        String getMsg = new String(bytes).trim();
        System.out.println("服务器收到的消息是:" + getMsg);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入应答数据：");
        byteBuffer.clear();
        byteBuffer.flip();
        Thread.sleep(5000);
        socketChannel.write(ByteBuffer.wrap(br.readLine().getBytes()));
        socketChannel.register(selector, SelectionKey.OP_READ);
        LOGGER.info("服务器已发送应答请求.....");
    }

    public static void main(String[] args) throws Exception {
        NioServer nioServer = new NioServer(8080);
        nioServer.listen();
    }
}
