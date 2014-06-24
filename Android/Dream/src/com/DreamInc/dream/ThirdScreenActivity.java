package com.DreamInc.dream;

//import com.DreamInc.dream.SecondScreenActivity.Server;

import android.app.Activity;
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

   
      
      
      
      
      /**List<String> values = Arrays.asList("Open Lamp", 
              "Close Lamp",
              "Open Door",
              "Close Door");*/
      /*final Integer[] color1 = {
  			0 ,0,0,0
  	};*/
      CustomList adapter = new
				CustomList(ThirdScreenActivity.this, devices, status );
       final ListView list =(ListView) findViewById(R.id.list);
				list.setAdapter(adapter);
				list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		            @Override
		            public void onItemClick(AdapterView<?> parent, View view,
		                                    int position, long id) {
		            	
		            	 z = status[position];
		            	
		            	if (z.equals("off")){
		            		
		            		 String commandon = name+","+devices[+position]+",On.";
		            		 Server.communicate(commandon);
		            		 String[] feedback = Server.GetCommandsfromData();
		            		 if (feedback.length>1){
		            			 String[] devicesstatus = feedback;
		           		        String devicesstatus1 =devicesstatus[0];
		           		        String[] devicesstatus2 = devicesstatus1.split(",");
		           		        int length2=(devicesstatus2.length)/2;
		           		      String[] listofdevices ;
		           		      listofdevices = new String[length2];
		           		      String[] listofstatus ;
		           		      listofstatus = new String[length2];
		           		      int f=0;
		           		      int d=1;
		           		        for(int i=0;i < length2;i++){
		           		        	 listofdevices[i] = devicesstatus2[f];
		           		        	 listofstatus[i] = devicesstatus2[d];
		           		        	 z=z+2;
		           		        	 d=d+2;
		           		        	 
		           		        }
		           		        devices = listofdevices;
		           		        status = listofstatus;
		            		 }
		            		 
		            		 String feedback1 =feedback[0];
		            		 if ( feedback1.equals(devices[position]+","+name+",On"))
		            		 {
		            			 list.getChildAt(position).setBackgroundColor(Color.GREEN);
			            		 status[+position] =  "on" ;
			            		 Toast.makeText(getApplicationContext(),
			                             feedback1, Toast.LENGTH_LONG)
			                          .show();
		            		 }
		            		 
		            		 
		            		 
		            	}else{
		            		
		            		String commandoff = name+","+devices[+position]+",OFF.";
		            		 Server.communicate(commandoff);
		            		 String feedback[] = Server.GetCommandsfromData();
		            		 if (feedback.length>1){
		            			 String[] devicesstatus = feedback;
		           		        String devicesstatus1 =devicesstatus[0];
		           		        String[] devicesstatus2 = devicesstatus1.split(",");
		           		        int length2=(devicesstatus2.length)/2;
		           		      String[] listofdevices ;
		           		      listofdevices = new String[length2];
		           		      String[] listofstatus ;
		           		      listofstatus = new String[length2];
		           		      int f=0;
		           		      int d=1;
		           		        for(int i=0;i < length2;i++){
		           		        	 listofdevices[i] = devicesstatus2[f];
		           		        	 listofstatus[i] = devicesstatus2[d];
		           		        	 z=z+2;
		           		        	 d=d+2;
		           		        	 
		           		        }
		           		        devices = listofdevices;
		           		        status = listofstatus;
		            		 }
		            		 String feedback1= feedback[0];
		            		 if ( feedback1.equals(devices[position]+","+name+",Off"))
		            		 {
		            			 list.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
			            		 status[+position] =  "off" ;
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
				
			//devices=Server.GetCommandsfromData();
				String[] feedback = Server.GetCommandsfromData();
       		 if (feedback.length>1){
       			 String[] devicesstatus = feedback;
      		        String devicesstatus1 =devicesstatus[0];
      		        String[] devicesstatus2 = devicesstatus1.split(",");
      		        int length2=(devicesstatus2.length)/2;
      		      String[] listofdevices ;
      		      listofdevices = new String[length2];
      		      String[] listofstatus ;
      		      listofstatus = new String[length2];
      		      int f=0;
      		      int d=1;
      		        for(int t=0;t < length2;t++){
      		        	 listofdevices[t] = devicesstatus2[f];
      		        	 listofstatus[t] = devicesstatus2[d];
      		        	 z=z+2;
      		        	 d=d+2;
      		        	 
      		        }
      		        devices = listofdevices;
      		        status = listofstatus;
       		 }
				
				
	}
      
      
      
      
	}

