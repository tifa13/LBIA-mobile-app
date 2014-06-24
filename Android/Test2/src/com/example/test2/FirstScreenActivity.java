package com.example.test2;

import java.net.Socket;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstScreenActivity extends Activity {
	 //public static String serverHostname = new String ("10.0.2.2");
	EditText username;
	EditText password;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen1);
		
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		Button Signup = (Button) findViewById(R.id.signupbutton);
		Signup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent screen2 = new Intent(getApplicationContext(), ThirdScreenActivity.class);
				screen2.putExtra("username", username.getText().toString());
                screen2.putExtra("password", password.getText().toString());
 
                Log.e("n", username.getText()+"."+ password.getText());
 
                startActivity(screen2);
			}
		});
	}

		
		
		
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first_screen, menu);
		return true;
	}

}
