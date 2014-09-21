
package com.DreamInc.dream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
  	      
  		     String[] rafaye3 = Server.GetCommands(3);
  		     assignedname = rafaye3[0];
  		     String clear = rafaye3[1];
  		    	   
  				
  				if(clear.equals("Y")){
  					
  				
  		      
  		        System.out.println(assignedname);
  		        
  		        	String listofdevices[] = Server.GetCommands(1);
  		        	String listofstatus[]  = Server.GetCommands(2);
  		      if (listofdevices == null )
  		     {
  		    	AlertDialog.Builder builder = new AlertDialog.Builder(SecondScreenActivity.this);
  		    	builder.setMessage("No devices are connected ")
  		    	       .setCancelable(false)
  		    	       .setPositiveButton("stop", new DialogInterface.OnClickListener() {
  		    	           public void onClick(DialogInterface dialog, int id) {
  		    	                System.exit(1);
  		    	           }
  		    	       });
  		    	AlertDialog alert = builder.create();
  		    	alert.show();
  		    	System.exit(1);
  		    	 // new AlertDialog.Builder(SecondScreenActivity.this).setTitle("Error").setMessage("No devices are connected").setNeutralButton("close", onCli))
  		    	
  		     }
  		   
  		     
  		     
  		     
  		        
  		        
  		        
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
    public static  PrintStream out = null;
    public static  BufferedReader in = null;
<<<<<<< HEAD
    public static String[] Commands2;
	
	public static String[] rafaye3 = new String[3] ;
	public static String[] modification = new String[3];
	public static String[] listofdevices = null;
	public static String[] listofstates = null;
	public static String[] list = null;
    public static String serverHostname = new String ("192.168.1.4");
=======
    public static String serverHostname = new String ("192.168.1.2");

>>>>>>> FETCH_HEAD
       
     
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

    	            out = new PrintStream(echoSocket.getOutputStream());
    	            
    	           
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
		out.print(firstcommand);
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
	}while (Commands.length==0);
		return Commands;
	}
	
	
	
	public static String[] GetCommands(int x)
	{
		String[] Commands = Server.GetCommandsfromData();
		
		int f = 0;
		if (Commands[0]!="")
		{
		for (int z = 0; z<Commands.length ; z++)
		{
			String checkcommand = Commands[z];
			if(checkcommand.matches(("^((1|4|5|6|7|8|9),.*,(Y|N|.*),.*,.*)")))
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

				 //assignedname = Commands2[1];
				rafaye3[0]  = Commands2[1];
					if (Commands2[0].equals("4")|Commands2[0].equals("5")|Commands2[0].equals("6")|Commands2[0].equals("7")){
						 //answer = Commands2[2];
						rafaye3[1]= Commands2[2];
						
					}
					if (Commands2[0].equals("8"))
					{
						//Confirmation = Commands2[2];
						rafaye3[2]= Commands2[2];
					}
					if (Commands2[0].equals("9"))
					{
						modification[0] = Commands2[2];
						modification[1] = Commands2[4];
						modification[2] = Commands2[5];
					}
			}
		}
		}
		
		if (x==1)
		{
			list = listofdevices;
		}
		if (x==2)
		{
			list = listofstates;
		}

		if (x==3)
		{
			list = rafaye3;
		}
		if (x==4)
		{
			list = modification;
		}

		return list;
	}
			
	}
	



	
