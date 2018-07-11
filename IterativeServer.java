/*
 * Name: Michael Edwards & Noah Sterling
 * Assignment: 
 */


import java.io.*;
import java.net.*;
import java.util.Date;


public class IterativeServer {

	public static void main(String[] args) throws IOException
	{
		if(args.length < 1)
			return;
		
		
	try {
		ServerSocket serverSocket = new ServerSocket(3333); //Establishes a new Server Socket on port 3333
		
		System.out.println("Server is listening on port 3333.");
		
		while(true) //While look keeps server running indefinitely
		{
			Socket socket = serverSocket.accept(); //When a connection is detected on the socket, accept connection
			System.out.println("New client connected");
			
			new ServerThread(socket).start(); //Start new socket, listening for additional clients. Client cap is defaulted to 50.
			
			
		}
	}
	
	catch(IOException e)
	{
		throw new IOException("Error!");
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
	            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	 
	            OutputStream output = socket.getOutputStream();
	            PrintWriter writer = new PrintWriter(output, true);
	 
	 
	            String text;
	 
	            do {
	                text = reader.readLine();
	                String reverseText = new StringBuilder(text).reverse().toString();
	                writer.println("Server: " + reverseText);
	 
	            } while (!text.equals("bye"));
	 
	            socket.close();
	        } catch (IOException ex) {
	            System.out.println("Server exception: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	    }
	}
	
}

