package com.DreamInc.dream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FirstScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstscreen);
		final Button signin =(Button) findViewById(R.id.btnsignin);
		final Button signup =(Button) findViewById(R.id.btnsignup);
		Log.d(null, "first screen3");
		 


		


		signin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent screen2 = new Intent(getApplicationContext(), SecondScreenActivity.class);
				screen2.putExtra("action1", signin.getText().toString());
				Log.d(null, "first screen4"); 
                startActivity(screen2);
			}
		});
		signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent screen2 = new Intent(getApplicationContext(), SecondScreenActivity.class);
				screen2.putExtra("action1", signup.getText().toString());
				
				Log.d(null, "first screen"); 
                startActivity(screen2);
                Log.d(null, "first screen2");
			}
		});
		
	}

	

}

