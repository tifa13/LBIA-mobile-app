
package com.DreamInc.dream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SecondScreenActivity extends Activity {
	public Server setupconnection = new Server();
	public String[] commands;
	public static String[] intercommand = new String[50];
	
	
	public static String[] getdata1(){
		String data1[] = new String[10];
		int i =0;
		for(int z=0;z<50;z++){
			
			if((intercommand[z]!="")&&(intercommand[z]!=null))
			{
				data1[i]=intercommand[z];
				i++;
			}else{
				Log.d(null, "crash");
			}
			
		}
		
		return data1;
	}
	public static void cleardata(){
		intercommand= new String[50];
		
	}
	
	
	private static class networktask extends AsyncTask<Integer, String[], Void>{

    	
		@Override
		protected Void doInBackground(Integer... params) {
			Log.d("hii", "worked");
			Server.setupconnection();
			Log.d("hii", ";)");
    		do{
    		String[] results = Server.GetCommandsfromData();
    		if(results.length>0){
        		intercommand = Server.combine(results, intercommand);		
        	}
    		
    		publishProgress(results);
    		}while(true);
		}



		protected void onProgressUpdate(String values1[]) {
    		// TODO Auto-generated method stub
    		super.onProgressUpdate(values1);
    		String results[] = values1;
    		
    		
    		
    		
    	}



    	@Override
    	protected void onPostExecute(Void result) {
    		// TODO Auto-generated method stub
    		super.onPostExecute(result);
    	}
    	 

    	

    	
    	
	}
	

	

	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      setContentView(R.layout.secondscreen);
      
      Button btnaction1 = (Button) findViewById(R.id.btnaction1);
      String type;
      Intent i = getIntent();
      final String action = i.getStringExtra("action1");
      btnaction1.setText(action);
      networktask mytask = new networktask();
	       int z =1;
	       mytask.execute(z);
	       
	       Log.d("hi", "before entering !!!!!!!!!!!!");
	       try {
	    	   Log.d("hi", "one");
			Thread.sleep(2000);
			Log.d("hi", "two");
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
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
  			     Server.dostuff();
  			       
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
			    try {
			    	   Log.d("hi", "one");
					Thread.sleep(2000);
					Log.d("hi", "two");
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    String clear = "ok";
			   
  	     for (int i =0 ; i<4 ; i++){
  	    	 
  		     String[] rafaye3 = Server.GetCommands(3);
  		     assignedname = rafaye3[0];
  		     clear = rafaye3[1];
  		     if(clear!= null){
  		    	 i=12;
  		     }
  		     
  			}
  	     if (clear ==null){
  	    	 clear = "S";
  	     }
  	   String listofdevices[] = Server.GetCommands(1);
     	String listofstatus[]  = Server.GetCommands(2);
  	   SecondScreenActivity.cleardata();
  	   
  				if((clear.equals("Y"))&&(listofdevices!=null)&&(listofstatus!=null)){
  				Intent screen3 = new Intent(getApplicationContext(), ThirdScreenActivity.class);
  				screen3.putExtra("devicename", assignedname);
  				screen3.putExtra("devices", listofdevices);
  				screen3.putExtra("status", listofstatus);
  				
  				
                startActivity(screen3);
                
  				}
  				if (((listofdevices==null)||(listofstatus==null))&&(clear.equals("Y")))
  				{
  					new AlertDialog.Builder(SecondScreenActivity.this).setTitle("Error").setMessage("No devices are connected :( Plz try again later").setNeutralButton("Close", null).show();
  				}
  				if(clear.equals("N"))
  				{
  					new AlertDialog.Builder(SecondScreenActivity.this).setTitle("Error").setMessage("Wrong username or passowrd").setNeutralButton("Close", null).show();
  				}
  				if(clear.equals("S")){
  					new AlertDialog.Builder(SecondScreenActivity.this).setTitle("Error").setMessage("We have a Maintenance problem :( plz try again later").setNeutralButton("Close", null).show();
  				}
  				}
  			
  		});
    	  
      }
	
	
	
      
      
      
}

class Server extends Activity{
	
	



	public static Socket echoSocket = null;
    public static  PrintStream out = null;
    public static  BufferedReader in = null;
    

    public static String[] Commands2;
	
	public static String[] rafaye3 = new String[3] ;
	public static String[] modification = new String[3];
	public static String[] listofdevices = null;
	public static String[] listofstates = null;
	public static String[] list = null;
    public static String serverHostname = new String ("172.20.10.4");
    
    
    
    
    
    public static String[] combine(String[] a, String[] b){
        int length = a.length + b.length;
        String[] result = new String[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }



    
    	
    	
    
   
   
	
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
    	            Log.d("hiii", ":)");
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
		
	/*try {
		if(in.read()==-1){
			
			
			Server.setupconnection();
		}
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
			
		
		
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
		
		String intercommand[] = SecondScreenActivity.getdata1();
		
		 String[] modification = new String[3];
		int f = 0;
		int rr = 0;
		for (int z = 0; z<intercommand.length ; z++)
		{
		if ((intercommand[z]!="")&&(intercommand[z]!=null))
		{
			String checkcommand = intercommand[z];
			if(checkcommand.matches(("^((1|4|5|6|7|8|9),.*,(Y|N|.*),.*,.*)")))
			{
				Commands2 = checkcommand.split(",");
				
				
				if (Commands2[0].equals("1")){
					int number = Integer.parseInt(Commands2[2]);
					if (rr==0){
						listofdevices = new String[number];
						listofstates = new String[number];
						rr=10;
					}
					
					String devicename = Commands2[3];
					String states = Commands2[4];
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
						modification[1] = Commands2[3];
						modification[2] = Commands2[4];
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
			rafaye3 = new String[3];
		}
		if (x==4)
		{
			list = modification;
			
		}
		

		return list;
	}
	public static void dostuff(){
		int z = 0;
		for(int dd=0;dd<100000;dd++){
			z++;
		}
	}
			
	}
	



	
