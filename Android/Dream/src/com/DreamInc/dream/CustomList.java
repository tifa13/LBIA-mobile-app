package com.DreamInc.dream;


import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class CustomList extends ArrayAdapter<String>{
	
private final Activity context;
private final String[] devices;
private final String[] status;



public CustomList(Activity context,
String[] devices, String[] status) {
super(context, R.layout.list_single, devices);
this.context = context;
this.devices = devices;

this.status = status;

}
@Override
public View getView(int position, View view, ViewGroup parent) {
LayoutInflater inflater = context.getLayoutInflater();
View rowView= inflater.inflate(R.layout.list_single, null, true);
TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);


txtTitle.setText(devices[position]);
if (status[position].equals("49")){
	rowView.setBackgroundColor(Color.GREEN);
}
if (status[position].equals("48")){
	rowView.setBackgroundColor(Color.TRANSPARENT);
	
}
if (status[position].equals("51")){
	rowView.setBackgroundColor(Color.RED);
}
if(status[position].equals("50")){
	rowView.setBackgroundColor(Color.BLUE);
}


return rowView;
}
}
