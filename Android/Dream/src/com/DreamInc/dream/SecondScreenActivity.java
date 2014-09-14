
package com.DreamInc.dream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.DropBoxManager.Entry;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SecondScreenActivity extends Activity {
	public Server setupconnection = new Server();
	public String[] commands;
	

	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      setContentView(R.layout.secondscreen);
      Button btnaction1 = (Button) findViewById(R.id.btnaction1);
      String type;
      Intent i = getIntent();
      final String action = i.getStringExtra("action1");
      btnaction1.setText(action);
      if(action.equals("Sign Up"))
      {
    	   type = "su";
      }else{
    	  type = "si";
      }
      final String type2 = type;
      

      
     
        
      
    	  btnaction1.setOnClickListener(new View.OnClickListener() {
    		
  			@Override
  			public void onClick(View arg0) {
  				
  				
  				 final EditText username = (EditText) findViewById(R.id.inputusername);

  				 final EditText password = (EditText) findViewById(R.id.inputpassword);	


  				 String username2 = username.getText().toString();
  			       String password2 = password.getText().toString();
  			      Server.setupconnection();
  		       String assignedname = "ok";
  		       try{
  		    	   
  		     File myFile = new File("/sdcard/yano2.txt"); 
  			 if (myFile.exists()) {
  				    //File exists.
  				 String number;
  				if(type2.equals("su"))
  		      {
  		    	   number = "7";
  		      }else{
  		    	  number = "5";
  		      }
  					FileInputStream fIn = openFileInput("yano2.txt");
  				
                InputStreamReader isr = new InputStreamReader(fIn);
                char[] inputBuffer = new char[2];
                isr.read(inputBuffer);
                assignedname = new String(inputBuffer);
                String firstcommand = number+","+assignedname+",,,"+username2 +","+password2;
                int firstcommandi = firstcommand.length();
                String firstcommandl;
                if (firstcommandi<10)
                {
                	 firstcommandl = "0"+String.valueOf(firstcommand.length());
                }else 
                {
                 firstcommandl = String.valueOf(firstcommand.length());
                }
                firstcommand = firstcommandl+ firstcommand;
                Server.communicate(firstcommand);
	      	     

  			 }
  				 else {
  				    //File does not exist.
  					 String number1;
  					if(type2.equals("su"))
  	  		      {
  	  		    	   number1 = "6";
  	  		      }else{
  	  		    	  number1 = "4";
  	  		      }
  	                String firstcommand = number1+",,,,"+username2 +","+password2;
  	                int firstcommandi = firstcommand.length();
  	              String firstcommandl;
                  if (firstcommandi<10)
                  {
                  	 firstcommandl = "0"+String.valueOf(firstcommand.length());
                  }else 
                  {
                   firstcommandl = String.valueOf(firstcommand.length());
                  }
  	                firstcommand = firstcommandl+ firstcommand;
  	                Server.communicate(firstcommand);

  	      	       assignedname = Server.GetData(1);
  			

  					FileOutputStream fOut = openFileOutput("yano2.txt", 0);				
  					OutputStreamWriter osw = new OutputStreamWriter(fOut);	
  					osw.write(assignedname);
  					 osw.flush();
                     osw.close();
  				 }
  				
  		       }catch (IOException ioe) {
  	             ioe.printStackTrace();
  			 }
  	      
  		     String clear = Server.GetData(2);
  		    	   
  				
  				if(clear.equals("Y")){
  					
  				
  		      
  		        System.out.println(assignedname);
  		        
  		        String[] listofdevices = Server.GetCommands(1);
  		        String[] listofstatus  = Server.GetCommands(2);
  		        
  		   
  		     
  		     
  		     
  		        
  		        
  		        
  				Intent screen3 = new Intent(getApplicationContext(), ThirdScreenActivity.class);
  				screen3.putExtra("devicename", assignedname.toString());
  				screen3.putExtra("devices", listofdevices);
  				screen3.putExtra("status", listofstatus);
  				
  				
                startActivity(screen3);
                
  				}else{
  					new AlertDialog.Builder(SecondScreenActivity.this).setTitle("Error").setMessage("Wrong username or passowrd").setNeutralButton("Close", null).show();
  				}
  				}
  			
  		});
    	  
      }
	
	
	
      
      
      
}

class Server extends Activity{
	public static Socket echoSocket = null;
    public static  PrintWriter out = null;
    public static  BufferedReader in = null;
    public static String serverHostname = new String ("10.40.47.71");
       
     
	public static  void  setupconnection() 
	{
		

        System.out.println ("Attemping to connect to host " +
    			serverHostname + " on port 10008.");
        int x = 0;
    	         do
    	         {
    	        try {
    	            // echoSocket = new Socket("taranis", 7);
    	        	Log.d(null, "crash");
    	             echoSocket = new Socket(serverHostname, 14);
    	           

    	            out = new PrintWriter(echoSocket.getOutputStream(), true);
    	            in = new BufferedReader(new InputStreamReader(
                            echoSocket.getInputStream()));
    	            echoSocket.setSoTimeout(1000);
    	            Log.d(null, "msg73");
    	            
    	        } catch (UnknownHostException e) {
    	            System.err.println("Don't know about host: " + serverHostname);
    	            x = 5;
    	            System.exit(1);
    	        } catch (IOException e) {
    	            System.err.println("Couldn't get I/O for "
    	                               + "the connection to: " + serverHostname);
    	           
    	          
    	    		x++;
    	            
    	           
    	           if (x==5)
    	           {
    	        	   
    	            System.exit(1);
    	            
    	           }
    	        }
    	         }while(x!=0);
        
    	       System.out.println("connection established");
        Log.d(null, "msg83");
        
      
   	
   	 	
	}
	
	
	public  static void communicate(String firstcommand) {
		
	try {
		if(in.read()==-1){
			
			
			Server.setupconnection();
		}
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
			
		
		
   	if (firstcommand.equals("Bye.")) {
   		out.close(); 
   	    try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
   	    try {
			echoSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	  
		
	}else {
		out.println(firstcommand);
	}
	
	
   	
   	
	}
	
	public static String[] GetCommandsfromData()
	{
		String ReceivedData;
		String[] Commands;
		char[] buffer = new char[100];
		int i = 0;
		do{
			i++;
			if (i>5){
				Server.setupconnection();
	            i=0;
			}
		
		try {
			in.read(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReceivedData = new String(buffer).trim();
		Commands = ReceivedData.split(".!");
	}while (Commands== null);
		return Commands;
	}
	
	
	public static String GetData(int x)
	{
		String[] Commands = Server.GetCommandsfromData();
		String[] Commands2;
		String assignedname = "tanash";
		String answer = "tanash";
		String Confirmation = "tanash";
		for (int z = 0; z<Commands.length ; z++)
		{
			String checkcommand = Commands[z];
			if(checkcommand.matches(("^((1|4|5|6|7|8),\\d*,(Y|N|\\d*),\\d*,\\d*)")))
			{
				Commands2 = checkcommand.split(",");
				 assignedname = Commands2[1];
				if (Commands2[0].equals("4")|Commands2[0].equals("5")|Commands2[0].equals("6")|Commands2[0].equals("7")){
					 answer = Commands2[2];
				}
				if (Commands2[0].equals("8"))
				{
					Confirmation = Commands2[2];
				}
				
				
			}
		}
		String reply = "tanash" ;
		if ( x==1)
		{
			reply = assignedname;
		}
		if (x==2)
		{
			reply = answer;
		}
		if (x==3)
		{
			reply = Confirmation;
		}
		return reply;
	}
	
	/*public static String[] modifylist(int x , String[]devices , String[]status , String device , String status1 )
	{
		if (x ==1 )//add
		{
			//Dictionary<String, String> dic = new Dictionary<String, String>();
			Map<String,String> map = new HashMap<String,String>();
			//map.put(key, value)
			map.get("hi");
			
			String[] baha2;
			
			for (Map.Entry<String, String> entry : map.entrySet())
			{
			    System.out.println(entry.getKey() + "/" + entry.getValue());
			}
			
			baha2[0] =  + "------ " + map.get(devices[0]);
					
		}
		return status;
	}*/
	public static String[] GetCommands(int x)
	{
		String[] Commands = Server.GetCommandsfromData();
		String[] Commands2;
<<<<<<< HEAD
		String[] rafaye3 = null ;
		String[] modification = null;
=======
>>>>>>> parent of bae1fb2... new command code
		String[] listofdevices = null;
		String[] listofstates = null;
		String[] list = null;
		int f = 0;
		 do{
			 
		 
		for (int z = 0; z<Commands.length ; z++)
		{
			String checkcommand = Commands[z];
			if(checkcommand.matches(("^((1|4|5|6|7|8|9),\\d*,(Y|N|\\d*),\\d*,\\d*)")))
			{
				Commands2 = checkcommand.split(",");
				
				if (Commands2[0].equals("1")){
					int number = Integer.parseInt(Commands2[2]);
					String devicename = String.valueOf(Integer.parseInt(Commands2[3]));
					String states = String.valueOf(Integer.parseInt(Commands2[4]));
					listofdevices = new String[number];
					listofstates = new String[number];
					listofdevices[f] = devicename;
					listofstates[f] = states;
					f++;
				}
<<<<<<< HEAD
				 //assignedname = Commands2[1];
				rafaye3[0]  = Commands2[1];
					if (Commands2[0].equals("4")|Commands2[0].equals("5")|Commands2[0].equals("6")|Commands2[0].equals("7")){
						 //answer = Commands2[2];
						rafaye3[1]= Commands2[2];
						
					}
					if (Commands2[0].equals("8"))
					{
						//Confirmation = Commands2[2];
						rafaye3[1]= Commands2[2];
					}
					if (Commands2[0].equals("9"))
					{
						modification[0] = Commands2[2];
						modification[1] = Commands2[4];
						modification[2] = Commands2[5];
					}
=======
>>>>>>> parent of bae1fb2... new command code
				
				
			}
		}
		 }while(Commands!=null);
		if (x==1)
		{
			list = listofdevices;
		}
		if (x==2)
		{
			list = listofstates;
		}
<<<<<<< HEAD
		if (x==3)
		{
			list = rafaye3;
		}
		if (x==4)
		{
			list = modification;
		}
=======
>>>>>>> parent of bae1fb2... new command code
		return list;
	}
			
	}
	



	
