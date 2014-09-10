package com.DreamInc.dream;

//import com.DreamInc.dream.SecondScreenActivity.Server;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ThirdScreenActivity extends Activity {
	ListView listView;
	String[] devices;
	String[] status;
	public String z;
	
	
	
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.thirdscreen);
     
      
      
       Intent i = getIntent();
       devices = i.getStringArrayExtra("devices");
     status = i.getStringArrayExtra("status");
   
      
      final String name = i.getStringExtra("devicename");
      Button btnsignout = (Button) findViewById(R.id.btnsignout);

   
      
      
      
      
     
      CustomList adapter = new
				CustomList(ThirdScreenActivity.this, devices, status );
       final ListView list =(ListView) findViewById(R.id.list);
				list.setAdapter(adapter);
				list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		            @Override
		            public void onItemClick(AdapterView<?> parent, View view,
		                                    int position, long id) {
		            	
		            	 z = status[position];
		            	
		            	if (z.equals("0")){
		            		
		            		 String commandon = "8,"+name+","+devices[+position]+",On,   ,   ";
		            		 int commandoni = commandon.length();
		     	              String commandonl;
		                     if (commandoni<10)
		                     {
		                     	 commandonl = "0"+String.valueOf(commandon.length());
		                     }else 
		                     {
		                      commandonl = String.valueOf(commandon.length());
		                     }
		            		 commandon = commandonl + commandon;
		            		 Server.communicate(commandon);
		            		 String[] info = Server.GetCommands(3);
		            		 String Confirmation = info[1];
		            		 if (Confirmation.equals("Y"))
		            		 {
		            		    String[] listofdevices = Server.GetCommands(1);
		            		    String[] listofstatus  = Server.GetCommands(2);
		           		        devices = listofdevices;
		           		        status = listofstatus;
		           		        list.getChildAt(position).setBackgroundColor(Color.GREEN);	
		           		     	status[+position] =  "255" ;
		            		 }
		            		 if (Confirmation.equals("N"))
		            		 {
		            			 String feedback1 = "Command can't be executed. Please try again later";
		            			 Toast.makeText(getApplicationContext(),
			                             feedback1, Toast.LENGTH_LONG)
			                          .show();
		            		 }
		            		 
		            		 
		            		 
		            		 
		            		 
		            	}else{
		            		
		            		String commandoff = "8,"+name+","+devices[+position]+",Off,   ,   ";
		            		int commandoffi = commandoff.length();
		     	              String commandoffl;
		                     if (commandoffi<10)
		                     {
		                     	 commandoffl = "0"+String.valueOf(commandoff.length());
		                     }else 
		                     {
		                      commandoffl = String.valueOf(commandoff.length());
		                     }
		            		commandoff = commandoffl + commandoff;
		            		 Server.communicate(commandoff);
		            		 String[] info = Server.GetCommands(3);
		            		 String Confirmation = info[1];
		            		 if (Confirmation.equals("Y"))
		            		 {
		            		    String[] listofdevices = Server.GetCommands(1);
		            		    String[] listofstatus  = Server.GetCommands(2);
		           		        devices = listofdevices;
		           		        status = listofstatus;
		           		        list.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
		           		        
		           		     	status[+position] =  "0" ;
		            		 }
		            		 if (Confirmation.equals("N"))
		            		 {
		            			 String feedback1 = "Command can't be executed. Please try again later";
		            			 Toast.makeText(getApplicationContext(),
			                             feedback1, Toast.LENGTH_LONG)
			                          .show();
		            		 }
		            	}
		                

		            }
		        });
				btnsignout.setOnClickListener(new View.OnClickListener() {
		  			
		  			@Override
		  			public void onClick(View arg0) {
		  				String signout = "Bye.";
		  				Server.communicate(signout);
		  				Intent screen4 = new Intent(ThirdScreenActivity.this, FirstScreenActivity.class);
		  				startActivity(screen4);
		  			}
				});
				
				String[] listofdevices = Server.GetCommands(1);
    		    String[] listofstatus  = Server.GetCommands(2);
   		        devices = listofdevices;
   		        status = listofstatus;
       		 }
				
				
	}
      
      
      
      
	

