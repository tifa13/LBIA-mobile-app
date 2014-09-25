package com.DreamInc.dream;

//import com.DreamInc.dream.SecondScreenActivity.Server;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
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
	String[] devices2 = new String[1];
	String[] status2 = new String[1];
	public String z;
	int ff = 0;
	
	
	
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.thirdscreen);
     
      
      
       Intent i = getIntent();
       devices = i.getStringArrayExtra("devices");
     status = i.getStringArrayExtra("status");
    
     final Map<String,String> map = new HashMap<String,String>();
          
     for (int x=0 ; x<devices.length ; x++)
     {
    	map.put(devices[x], status[x]); 
     }
     
     for ( String key : map.keySet() ) {
 	    System.out.println( key );
 	    System.out.println(map.get(key));
 	    String value = map.get(key);
 	    devices2[ff] = key;
 	    status2[ff] = value;
 	    ff++;
 	}
     ff=0;
      
      final String name = i.getStringExtra("devicename");
      Button btnsignout = (Button) findViewById(R.id.btnsignout);
      
   
      
      
      
      
     
      CustomList adapter = new
				CustomList(ThirdScreenActivity.this, devices2, status2 );
       final ListView list =(ListView) findViewById(R.id.list);
				list.setAdapter(adapter);
				
				list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					
					
		            @Override
		            public void onItemClick(AdapterView<?> parent, View view,
		                                    int position, long id) {
		            	
		            	
		            	z = status2[position];
		            	
		            	if ((z.equals("48"))|(z.equals("50"))|(z.equals("51"))){

		            		
		            		 String commandon = "8,"+name+","+devices2[+position]+",1,,";
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
		            		 Server.dostuff();
		            		 
		            		 for (int i = 0 ; i<5 ; i++){
		            		 String[] modification = Server.GetCommands(4);
				    		    if (modification[0]!=null)
				    		    {
				    		    	i = 10;
				    		    String mm = modification[0];
				    		    if (mm.equals("1")|(mm.equals("3")))
				    		    {
				    		    	map.put(modification[1], modification[2]);	
				    		    	status2[+position]="49";
				    		    }
				    		    if (mm.equals("2"))
				    		    {
				    		    	map.remove(modification[1]);
				    		    	list.invalidateViews();
				    		    }
				    		    if (map.isEmpty()==false){
				    		    for ( String key : map.keySet() ) {
				    		 	    System.out.println( key );
				    		 	    System.out.println(map.get(key));
				    		 	    String value = map.get(key);
				    		 	    //devices2[ff] = key;
				    		 	    //status2[ff] = value;
				    		 	    
				    		 	   int valuin = Integer.parseInt(value);
				    		 	   if (valuin==49){
				    		 	    	list.getChildAt(ff).setBackgroundColor(Color.GREEN);
				    		 	    }
				    		 	    if(valuin==48){
				    		 	    	list.getChildAt(ff).setBackgroundColor(Color.TRANSPARENT);
				    		 	    }
				    		 	   if(valuin==51){
				    		 	    	list.getChildAt(ff).setBackgroundColor(Color.RED);
				    		 	    }
				    		 	  if(valuin==50){
				    		 	    	list.getChildAt(ff).setBackgroundColor(Color.BLUE);
				    		 	    }
				    		 	   
				    		 	    ff++;
				    		 	}
				    		    ff=0;
				    		    }
				    		    }
		            		 }
		            		
		            		 if ((status2[+position]=="48")|(status2[+position]=="50")|(status2[+position]=="51"))
		            		 {
		            			 String feedback1 = "Command can't be executed. Please try again later";
		            			 Toast.makeText(getApplicationContext(),
			                             feedback1, Toast.LENGTH_LONG)
			                          .show();
		            		 }
		            		 }
		            		
		            		 
		            		 
		            		 
		            		 
		            		 
		            	else{
		            		
		            		String commandoff = "8,"+name+","+devices2[+position]+",0,,";
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
		            		 Server.dostuff();
		            		
		            		 for (int i = 0 ; i<5 ; i++){
		            			 String[] modification = Server.GetCommands(4); 
		            		
				    		    if (modification[0]!=null)
				    		    {
				    		    i = 10;	
				    		    
				    		    String mm = modification[0];
				    		    if (mm.equals("1")|(mm.equals("3")))
				    		    {
				    		    	map.put(modification[1], modification[2]);
				    		    	//list.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
				    		    	status2[+position]="48";
				    		    }
				    		    if (mm.equals("2"))
				    		    {
				    		    	map.remove(modification[1]);
				    		    	list.invalidateViews();
				    		    }
				    		    if (map.isEmpty()==false){
				    		    for ( String key : map.keySet() ) {
				    		 	    System.out.println( key );
				    		 	    System.out.println(map.get(key));
				    		 	    String value = map.get(key);
				    		 	    //devices2[ff] = key;
				    		 	    //status2[ff] = value;
				    		 	    
				    		 	   int valuin = Integer.parseInt(value);
				    		 	   if (valuin==49){
				    		 	    	list.getChildAt(ff).setBackgroundColor(Color.GREEN);
				    		 	    }
				    		 	    if(valuin==48){
				    		 	    	list.getChildAt(ff).setBackgroundColor(Color.TRANSPARENT);
				    		 	    }
				    		 	   if(valuin==51){
				    		 	    	list.getChildAt(ff).setBackgroundColor(Color.RED);
				    		 	    }
				    		 	  if(valuin==50){
				    		 	    	list.getChildAt(ff).setBackgroundColor(Color.BLUE);
				    		 	    }
				    		 	    ff++;
				    		 	}
				    		    ff=0;
				    		    }
				    		    }
		            		 }
				    		   
		            		 if (status2[+position]=="49")
		            		 {
		            			 String feedback1 = "Command can't be executed. Please try again later";
		            			 Toast.makeText(getApplicationContext(),
			                             feedback1, Toast.LENGTH_LONG)
			                          .show();
		            		 }
		            		 }
		            		 
		            	
		            	
		    		    
		    		    
		    	     
		    		    }
		            
		                

		            }
		        );
				btnsignout.setOnClickListener(new View.OnClickListener() {
		  			
		  			@Override
		  			public void onClick(View arg0) {
		  				String signout = "Bye.";
		  				Server.communicate(signout);
		  				Intent screen4 = new Intent(ThirdScreenActivity.this, FirstScreenActivity.class);
		  				startActivity(screen4);
		  			}
				});
				
				
				
	
       		 }
	
	
				
	}


	

      
      
      
      
	

