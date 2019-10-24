import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocket1 {

	DatagramSocket s1;
	DatagramPacket dp1;
	DatagramPacket dp2;
	public ServerSocket1()
	{
		 try{
			 InetAddress serverAddress=InetAddress.getByName("127.0.0.1");
			 s1 = new DatagramSocket(9999,serverAddress);
		 }catch(IOException e){
			 		e.printStackTrace();
		 }
	}
	public String getQuestion()  {
		String st=null;

		try{

			 byte[] byteQues=new byte[1024];
			 dp2=new DatagramPacket(byteQues,byteQues.length);
			 s1.receive(dp2);
			 
			 st=new String(dp2.getData()).trim();
		 }catch(IOException e){
			 e.printStackTrace();
		}
		// Close the connection, but not the server socket
		return st;

	}
	public void giveAnswer(String answer)
	{
		try
		{
			byte[] byteAns=answer.getBytes();

			dp1=new DatagramPacket(byteAns,byteAns.length,dp2.getAddress(),dp2.getPort());
			s1.send(dp1);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	 public void close()
	 {
		 try{
			 s1.close();
		 }catch(Exception e){
			 e.printStackTrace();
		}
	 }
}
