package application;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class ClientSocket {
	DatagramSocket s1;
	DatagramPacket dp1;
	DatagramPacket dp2;
	public ClientSocket()
	{
		 try{

			 //System.out.print("127.0.0.3".getBytes());
			 InetAddress clientAddress=InetAddress.getByName("127.0.0.3");
			 s1 = new DatagramSocket(9995,clientAddress);

		 }catch(IOException e){
			 		e.printStackTrace();
		 }
	}
	public void giveQues(String ques)
	{
		try
		{

			if(!s1.isConnected()){
				InetAddress serverAddress=InetAddress.getByName("127.0.0.1");
				s1.connect(serverAddress, 9999);
				byte[] byteQues="Connected".getBytes();
				//InetAddress serverAddress=InetAddress.getByName("127.0.0.1");
				dp1=new DatagramPacket(byteQues,byteQues.length,serverAddress,9999);
				s1.send(dp1);
				byteQues=ques.getBytes();
				serverAddress=InetAddress.getByName("127.0.0.1");
				dp1=new DatagramPacket(byteQues,byteQues.length,serverAddress,9999);
				s1.send(dp1);
			}
			else{
				byte[] byteQues=ques.getBytes();
				InetAddress serverAddress=InetAddress.getByName("127.0.0.1");
				dp1=new DatagramPacket(byteQues,byteQues.length,serverAddress,9999);
				s1.send(dp1);
			}

		 }catch(IOException e){
			 e.printStackTrace();
		 }
	}
	public String getAns()
	{
		 String st=null;
		 try{

			 byte[] byteAns=new byte[1024];
			 dp2=new DatagramPacket(byteAns,byteAns.length);
			 s1.receive(dp2);
			 st=new String(dp2.getData()).trim();
		 }catch(IOException e){
			 e.printStackTrace();
		}

		 return st;
	 }
	 public void close()
	 {
		 try{
			 giveQues("Disconnect");
			 s1.close();
		 }catch(Exception e){
			 e.printStackTrace();
		}
	 }
}
