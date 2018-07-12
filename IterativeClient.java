import java.net.*;
import java.util.Scanner;
import java.io.*;


//Currently, Iterative Client connects, but since the connection immedately cease, it would cause a runtime error with Iterative Server. This is expected behavior

public class IterativeClient {
	
	static Scanner keyboard = new Scanner(System.in);

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
			
			new ServerThread(socket).start(); //Start new socket, listening for additional clients. Client cap is defaulted to 50.
			
		}
		
		catch (UnknownHostException ex) {
			 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }

	}
	static public class ServerThread extends Thread {
	    private Socket socket;
	 
	    public ServerThread(Socket socket) {
	        this.socket = socket;
	    }
	 
	    public void run() {
	        try {
	            InputStream input = socket.getInputStream();
	            BufferedReader br = new BufferedReader(new InputStreamReader(input));
	 
	            OutputStream output = socket.getOutputStream();
	            PrintWriter writer = new PrintWriter(output, true);
	 
	 
	            String text;
	            
	           
	 
	            do {
	            	System.out.println(br.readLine());
	            	System.out.println("Please input a command (type 'exit' to disconnect): ");
	            	
	            		
					text = keyboard.nextLine();
					writer.println(text);
					
					
					System.out.println(br.readLine() + "\n");
					
				
					
	            } while(!text.equals("exit"));
	            
	            System.out.println("Terminating Connection. Good Bye!");
	            socket.close();
	            
	        } 
	        
	        catch (IOException ex) {
	            System.out.println("Server exception: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	    }
	}
}
