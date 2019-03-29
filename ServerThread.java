package socketTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * @author Scdd
 * 服务器线程处理类
 *
 */

public class ServerThread extends Thread {
	//和本线程相关的socket
	Socket socket = null;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	//多线程操作
	public void run() {
		InputStream is = null;
		InputStreamReader isReader = null;
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		try {
			//获取输入流，读取客户端发送登录信息
			is = socket.getInputStream();
			//将字节流转化为字符流
			isReader = new InputStreamReader(is);
			//获取输出流
			br = new BufferedReader(isReader);
			
			String info = null;
			while((info = br.readLine())!=null) {
				System.out.println("客户端信息："+info);
			}
			//关闭输入流
			socket.shutdownInput();
			
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write("收到邮件");
			pw.flush();
			socket.shutdownOutput();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//关闭客户端
				if(br != null)
					br.close();
				if(isReader != null)
					isReader.close();
				if(is != null)
					is.close();
				if(pw != null)
					pw.close();
				if(os != null)
					os.close();
				if(socket != null)
					socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
