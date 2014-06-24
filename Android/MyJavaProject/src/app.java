import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;



public class app {
	
	public static void login() throws IOException
	{
	String str;
	String newname;
	String password;
	int j = 0;
	int i = 0;
	int x = 0;
	String [] usernames = new String [5];
	String [] passwords = new String [5];
	while (x != 5)
	{
	BufferedReader br = new BufferedReader(new 
	        InputStreamReader(System.in));

	System.out.println("do you have a username or not.");


	    str = br.readLine();
	    if (str.equals("yes"))
	    {
	    	System.out.println("go to login page");
			System.out.println("enter username");
			String username3 = br.readLine();
	    	for ( i = 0;i<=j;i++)
	    	{
	    		 
	    		
	    		if (username3.equals(usernames[i]))
	    		{
	    			System.out.println("enter your password");
	    			String password3 = br.readLine();
	    			if (password3.equals(passwords[i]))
	    					{
	    				System.out.println("you are loged in :)");
	
	    				System.out.println(i);
	    				System.out.println(j);
	    				break;
	    					}
	    		}
	    		if (i==j)
	    		{
	    			System.out.println("username and password are incorrect");
	    		}
	    		
	    		
	    	}
	    	
	    	
	    	
	    	
	    }else
	    {
	    	System.out.println("enter new username");
	    	newname = br.readLine();
	    	usernames [j] = newname;
	    	System.out.println("enter password");
	    	password = br.readLine();
	    	passwords[j] = password;
	    	break;
	    	
	    }
	    j++;

	 /**System.out.println("if you want to exit enter 5 , if not enter any other number");
	 String y = br.readLine();
	 x = Integer.parseInt(y);
	 */



	}
		}
	

	/**
	 * @param args
	 * @throws IOException 
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		
		login();
		


	        String serverHostname = new String ("127.0.0.1");

	        if (args.length > 0)
	           serverHostname = args[0];
	        System.out.println ("Attemping to connect to host " +
			serverHostname + " on port 10004.");

	        Socket echoSocket = null;
	        PrintWriter out = null;
	        BufferedReader in = null;

	        try {
	            // echoSocket = new Socket("taranis", 7);
	            echoSocket = new Socket(serverHostname, 10007);
	            out = new PrintWriter(echoSocket.getOutputStream(), true);
	            in = new BufferedReader(new InputStreamReader(
	                                        echoSocket.getInputStream()));
	        } catch (UnknownHostException e) {
	            System.err.println("Don't know about host: " + serverHostname);
	            System.exit(1);
	        } catch (IOException e) {
	            System.err.println("Couldn't get I/O for "
	                               + "the connection to: " + serverHostname);
	            System.exit(1);
	        }
	        System.out.println("connection established");
	        
	        
	    		BufferedReader br = new BufferedReader(new 
	    		        InputStreamReader(System.in));
	    		   System.out.println("choose the command that you want to do");
	    		   System.out.println("open lamp" +
	    		   		"open door" +
	    		   		"close lamp" +
	    		   		"close door");
	    		   BufferedReader stdIn = new BufferedReader(
                           new InputStreamReader(System.in));
	    		   
	    		   String userInput;
	    		  

	    	

	    		   System.out.print ("input: ");

	    		    while ((userInput = stdIn.readLine()) != null) {



	    		       

	    		   	   if (userInput.equals("open lamp"))

	    		   	   {

	    		   	   out.println(userInput);
	    		   	    char[] serverinput = new char [100];
	    		   	    in.read(serverinput);
	    		   	    
	    		   	 String x = String.valueOf(serverinput);

	    		   	   System.out.println("server :"+ x);

	    		   	   System.out.print ("input: ");
	    		   	   

	    		   	   }else if (userInput.equals("open door"))

	    		   	   {

	    		   	   out.println(userInput);

	    		   	char[] serverinput = new char [100];
    		   	    in.read(serverinput);
    		   	    
    		   	 String x = String.valueOf(serverinput);

    		   	   System.out.println("server :"+ x);

    		   	   System.out.print ("input: ");
    		   	   

	    		   	   }else if (userInput.equals("close lamp"))

	    		   	   {

	    		   	   out.println(userInput);

	    		   	char[] serverinput = new char [100];
    		   	    in.read(serverinput);
    		   	    
    		   	 String x = String.valueOf(serverinput);

    		   	   System.out.println("server :"+ x);

    		   	   System.out.print ("input: ");
    		   	   

	    		   	   }else if (userInput.equals("close door"))

	    		   	   {

	    		   	   out.println(userInput);

	    		   	char[] serverinput = new char [100];
    		   	    in.read(serverinput);
    		   	    
    		   	 String x = String.valueOf(serverinput);

    		   	   System.out.println("server :"+ x);

    		   	   System.out.print ("input: ");
    		   	   

	    		   	   }else

	    		   	   {

	    		   	   out.println(userInput);

	    		   	   System.out.println("server: incorrect command");

	    		   	   System.out.print ("input: space to end");

	    		   	   }

	    		    }
	    		    out.close();
	    			in.close();
	    			stdIn.close();
	    			echoSocket.close();
	    		}

	    	
	    	    }
	    	
	    					
