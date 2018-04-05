
import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
 
public class UDPClient 
{
      	private static FileInputStream fileIS;
      	private static DatagramSocket clientSocket;
      	private static InetAddress ipAddress;
      	private static byte[] sendData;
      	private static DatagramPacket sendPacket;
 
      	public static void main(String[] args) 
        {
            try 
            {
                // String fileName = args[0];
                // URL url = UDPClient.class.getResource("/home/ssp/lol.png");
                File file = new File("/home/ssp/nature.txt");
                fileIS = new FileInputStream("/home/ssp/nature.txt");
                clientSocket = new DatagramSocket();
                ipAddress = InetAddress.getByName("localhost");
                sendData = new byte[1024];
                System.out.println("Sending...");
                int bytesRead = fileIS.read(sendData);
                while (bytesRead != -1) 
                {
                    sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 9876);
                    clientSocket.send(sendPacket);
                    bytesRead = fileIS.read(sendData);
                }
                System.out.println(file.length() + " total bytes sent.");
                System.out.println("...done");
                fileIS.close();
                clientSocket.close();
                } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
}
