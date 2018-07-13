import java.net.*;
import java.util.Scanner;
import java.io.*;


//Currently, Iterative Client connects, but since the connection immedately cease, it would cause a runtime error with Iterative Server. This is expected behavior

public class IterativeClient {
	
	static Scanner keyboard = new Scanner(System.in);
	static Socket[] socketArray = new Socket[50];//Creates a socket and connects to a specified host name and port
	   //When testing on a local machine, the hostname will be "localhost"
	   //When running program on specified Client server, the hostname will be "192.168.100.88" and port will be 22
	static int i = 0;

	public static void main(String[] args) {
		if(args.length < 2)
			return;
		
		int option = 1;
		String hostname = args[0];
		int port = Integer.parseInt(args[1]);
		int amount = 0;
		StringBuilder sb = new StringBuilder();
		String text = " ";
		Socket[] socketArray = new Socket[50];//Creates a socket and connects to a specified host name and port
		   //When testing on a local machine, the hostname will be "localhost"
		   //When running program on specified Client server, the hostname will be "192.168.100.88" and port will be 22
		
		System.out.println("Please specify the amount of connections up to 50: \n");
		
		amount = keyboard.nextInt();
		
		System.out.println("Please specify the command you wish to use: \n" + 
							"- Date and Time: date \n" +
							"- Uptime: uptime \n" +
							"- Memory Use: free \n" +
							"- Netstat: netstat \n" +
							"- Current Users: who OR users \n" +
							"- Running Processes: ps \n");
		text = keyboard.next();

	
		try 
		{
			
		for(i = 0; i < amount ; i++)
		{
			Socket socket = new Socket(hostname, port);
			socketArray[i]  = socket;
			int session = i + 1;
			
			new ServerThread(socketArray[i], text, hostname, port, session).start(); //Start new socket, listening for additional clients. Client cap is defaulted to 50.
			
		}
		}
		
		catch (UnknownHostException ex) {
			 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        } 
		
		
		
	}
	static public class ServerThread extends Thread {
	   
	    private String text;
	    private String hostname;
	    private int port;
	    private Socket socket;
	    private int session;
	 
	    public ServerThread(Socket socket, String text, String hostname, int port, int session) {
	 
	        this.text = text;
	        this.hostname = hostname;
	        this.port = port;
	        this.socket = socket;
	        this.session = session;
	    }
	    
	 
	 
	    public void run() {
	    	
	        try {
	         	
	        	
	            InputStream input = socket.getInputStream();
	            BufferedReader br = new BufferedReader(new InputStreamReader(input));
	 
	            OutputStream output = socket.getOutputStream();
	            PrintWriter writer = new PrintWriter(output, true);
	 
	 
	          
	           
	 
	            
	            	System.out.println(br.readLine());
	
	            	
	            		
					
					writer.println(text);
					long start = System.currentTimeMillis();
					System.out.println();
					
					System.out.println("For client #" + session + ": \n" + br.readLine() + "\n");
					long end = System.currentTimeMillis();
					System.out.println("Response time for client " + session + ": " + (end - start) + " m/s \n");
					
				
					
	          
	            
	            System.out.println("Terminating Connection for client "+session+  "! Good Bye!");
	            socket.close();
	            
	        } 
	        
	        catch (IOException ex) {
	            System.out.println("Server exception: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	    }
	}
}
