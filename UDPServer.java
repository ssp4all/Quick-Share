
import java.io.File;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketTimeoutException;
 
public class UDPServer 
{
      	private static FileOutputStream fileOS;
      	private static DatagramSocket serverSocket;
      	private static byte[] receiveData;
      	private static DatagramPacket receivePacket;
      	private static File file;
 
      	public static void main(String[] args) 
		{
               	try {
                         	serverSocket = new DatagramSocket(9876);
                         	serverSocket.setSoTimeout(10000);
                         	receiveData = new byte[1024];
                         	receivePacket = new DatagramPacket(receiveData, receiveData.length);
                         	file = new File("/home/ssp/data.txt");
                         	fileOS = new FileOutputStream(file);
                         	System.out.println("Receiving...\nPress Ctrl+C to exit");
                         	serverSocket.receive(receivePacket);
                         	while (true) 
						              {
                          	fileOS.write(receiveData);
                          	serverSocket.receive(receivePacket);
                         	}
               	} 
				catch (SocketTimeoutException e) 
				{
                         	System.out.println("...done");
                         	System.out.println("Total bytes received: " + file.length());
               	} 
				catch (Exception e) 
				{
					e.printStackTrace();
               	}
      	}
}
