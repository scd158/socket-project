package socketTest;

import java.io.IOException;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		//指定服务器地址和端口
		Socket socket = new Socket("IP地址/本机默认localhost",6666);
		
		//向服务器端获取输入信息
		OutputStream os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os); //打印流
		pw.write("发送邮件");
		pw.flush();
		socket.shutdownOutput();	//关闭输出流
		
		InputStream is = socket.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String info = null;
		while((info = br.readLine())!=null) {
			System.out.println("服务器信息："+info);
		}
		
		br.close();
		is.close();
		pw.close();
		os.close();
		socket.close();
	}

}
