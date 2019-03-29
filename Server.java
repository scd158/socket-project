package socketTest;

import java.io.IOException;
import java.io.*;
import java.net.*;

@SuppressWarnings("unused")
public class Server {
	
	private static int clientcount = 0;
	
	public static void main(String[] args) throws IOException {
		//创建serversocket实例,默认设置端口为6666
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(6666);
		
		System.out.println("***服务器即将启动，等待客户端连接***");
		Socket socket = null;
		
		//多线程处理
		while(true) {
			//调用accept()方法监听，等待客户端连接
			socket = serverSocket.accept();
			//启动新线程
			ServerThread serverThread = new ServerThread(socket);
			serverThread.start();
			clientcount++;
			System.out.println("客户端数量为:"+clientcount);
			
			InetAddress address = socket.getInetAddress();
			System.out.println("当前客户端IP地址:"+address.getHostAddress());
		}	
	}
}
