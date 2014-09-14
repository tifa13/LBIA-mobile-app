package com.example.test2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.io.*; 
import java.net.*; 
import android.widget.Toast;

public class ThirdScreenActivity extends Activity implements OnClickListener {

    
	public Server servercommand = new Server();
	
    /** Called when the activity is first created. */
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.screen2);
      
        final TextView output = (TextView) findViewById(R.id.output);
        TextView username1 = (TextView) findViewById(R.id.username);
        Button btnopenlamp = (Button) findViewById(R.id.btnopenlamp);
        Button btnopendoor = (Button) findViewById(R.id.btnopendoor);
        Button btncloselamp = (Button) findViewById(R.id.btncloselamp);
        Button btnclosedoor = (Button) findViewById(R.id.btnclosedoor);
        Button btnbye       = (Button) findViewById(R.id.btnbye);
        
        
        
        
        
        Intent i = getIntent();
        // Receiving the Data
        String password = i.getStringExtra("username");
        String username = i.getStringExtra("password");
        final String closedoor = username +","+"  Close Door";
        final String openlamp = username+","+"Lamp Opened";
        final String opendoor = username+"," +"Door Opened";
        final String closelamp = username+ "," +"Lamp Closed";
        final String closeconnection = "Bye.";
        Log.e("Second Screen", username + "." + password);
     // Displaying Received data
        username1.setText(username);
        Log.d(null, "msg43");
        
        servercommand.startconnection();
        
        
        btnopenlamp.setOnClickListener(new View.OnClickListener() {
        	 
            public void onClick(View arg0) {
            	output.setText(openlamp);
            	try {
					servercommand.servercommand(openlamp);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	Log.d(null, "msg22");
            	
            	
                
            }
        });
        btncloselamp.setOnClickListener(new View.OnClickListener() {
       	 
            public void onClick(View arg0) {
            	output.setText(closelamp);
            	
            	try {
					servercommand.servercommand(closelamp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	Log.d(null, "msg22");
            	
            	
                
            }
            
        });
        btnopendoor.setOnClickListener(new View.OnClickListener() {
       	 
            public void onClick(View arg0) {
            	output.setText(opendoor);
            	
            	try {
					servercommand.servercommand(opendoor);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	return;
            	
            	
            	
                
            }
        });
        btnbye.setOnClickListener(new View.OnClickListener() {
       	 
            public void onClick(View arg0) {
            	output.setText(closeconnection);
            	
				try {
					servercommand.servercommand(closeconnection);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
            	
            	
            	
                
            }
        });
        btnclosedoor.setOnClickListener(new View.OnClickListener() {
       	 
            public void onClick(View arg0) {
            	output.setText(closedoor);
            	
            	
            	try {
					
					servercommand.servercommand(closedoor);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	return;
				
                
            }
        });
        Log.d(null, "msg33");
        
    }
    
    public class Server{
    	public Socket echoSocket = null;
        public PrintWriter out = null;
        public BufferedReader in = null;
        public String serverHostname = new String ("10.211.55.3");
        
        public void startconnection()
        {      
	        System.out.println ("Attemping to connect to host " +
			serverHostname + " on port 10007.");
	               
	        try {
	            // echoSocket = new Socket("taranis", 7);
	            echoSocket = new Socket(serverHostname, 14);
	            out = new PrintWriter(echoSocket.getOutputStream(), true);
	            in = new BufferedReader(new InputStreamReader(
	                                        echoSocket.getInputStream()));
	            Log.d(null, "msg73");
	            
	        } catch (UnknownHostException e) {
	            System.err.println("Don't know about host: " + serverHostname);
	            System.exit(1);
	        } catch (IOException e) {
	            System.err.println("Couldn't get I/O for "
	                               + "the connection to: " + serverHostname);
	            System.exit(1);
	        }
	        
	        final TextView check = (TextView) findViewById(R.id.server);
	        System.out.println("connection established");
	        Log.d(null, "msg83");
        }
        
    	public void servercommand(String closedoor) throws IOException
    	{	        
	        out.println(closedoor);
	        Log.d(null, "msg93");
	        char[] serverinput2 = new char [100];
	        System.out.println(serverinput2);
	   	    //in.read(serverinput2);
	   	    Log.d(null, "msg63");
	   	    
	   	// String x = String.valueOf(serverinput2);
	   	//check.setText(x);
	   	if (closedoor.equals("Bye.")) {
	   		out.close(); 
	   	    in.close(); 
	   	    echoSocket.close(); 
			
		}
	    Log.d(null, "msg53");
	   	
	   	 	
    	}
    	
    	
    	
    	
    }

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	
	       
        	} 


	



	


