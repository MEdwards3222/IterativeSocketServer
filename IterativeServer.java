/*
 * Name: Michael Edwards & Noah Sterling
 * Assignment: 
 */


import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Scanner;


public class IterativeServer {
	


	
	
	
//================================================================	

	public static void main(String[] args) throws IOException
	{
		if(args.length < 1)
			return;
		
		int port = Integer.parseInt(args[0]);
		ServerSocket serverSocket = new ServerSocket(port); //Establishes a new Server Socket on port
		
		
	try {
		
		
		
		while(true) //While loop keeps server running indefinitely
		{
			System.out.println("Server is listening on port: " + port );
			
			
			Socket socket = serverSocket.accept(); //When a connection is detected on the socket, accept connection
			System.out.println("Client connected");
			
			
			
			
			do 
			{
				InputStream input = socket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(input));
				StringBuilder sb = new StringBuilder();
				
				OutputStream output = socket.getOutputStream();
				PrintWriter writer = new PrintWriter(output, true);
				
				writer.println("Connected to Server " + socket.getRemoteSocketAddress());
				writer.flush();
				
				String text = " ";
				String cmd = " ";
				
			
			
			sb.append(br.readLine());
			
			text = sb.toString();
			
			
			
			if(text.equals("exit"))
			socket.close();
			
			
			//System.out.println("Troubleshoot: " + RunCommand(text));
			
			cmd = RunCommand(text);
			
			//System.out.println("Troubleshoot cmd: " + cmd);
			
			writer.println(cmd);
			
			
			
			}while(socket.isClosed() == false);
			
		}
	}
	
	catch(IOException e)
	{
		throw new IOException("Error!");
	}

	}//end main
		
//=========================================================
	public static String RunCommand(String cmd)
	{
		String s = null;
		
		   StringBuilder sb = new StringBuilder();

		   
		   
	    try {
	        
	        Process p = Runtime.getRuntime().exec(cmd);
	        
	        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
	     

	        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	        String line = " ";
	        
	        

	        // read the output from the command
	        
	        
	        System.out.println("Here is the standard output of the command:\n");
	        while ((s = stdInput.readLine()) != null) {
	            sb.append(s);
	            
	            System.out.println(sb.toString());
	            
	            
	            
	        }
	        
	        
	    }
	    catch (IOException e) {
	        System.out.println("Error! ");   
	        return null;
	    }
	    
	    catch (IllegalArgumentException e)
	    {
	    	System.out.println("Exception! Empty Character Detected!");
	    	return "Error! Empty Character Detected!";
	    }
	    
	  return sb.toString();
	}
//==================================================

	
}//End program
	



