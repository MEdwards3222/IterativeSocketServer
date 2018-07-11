import java.net.*;
import java.io.*;


public class IterativeClient {

	public static void main(String[] args) {
		if(args.length < 2)
			return;
		
		
		String hostname = args[0];
		int port = Integer.parseInt(args[1]);
		
		try 
		{
			Socket socket = new Socket(hostname, port); //Creates a socket and connects to a specified host name and port
													   //When testing on a local machine, the hostname will be "localhost"
													   //When running program on specified Client server, the hostname will be "192.168.100.88" and port will be 22
			
			socket.close();
		}
		
		catch (UnknownHostException ex) {
			 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }

	}

}
