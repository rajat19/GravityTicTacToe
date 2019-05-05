package com.gravity.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class Home extends Activity implements OnClickListener {

	Button playnormal, playdirections, playgravity, about, settings;
	Intent in;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		playnormal = (Button) findViewById(R.id.play1);
		playdirections = (Button) findViewById(R.id.play2);
		playgravity = (Button) findViewById(R.id.play3);
		about = (Button) findViewById(R.id.about);
		settings = (Button) findViewById(R.id.settings);
		
		playnormal.setOnClickListener(this);
		playdirections.setOnClickListener(this);
		playgravity.setOnClickListener(this);
		about.setOnClickListener(this);
		settings.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
			case R.id.play1:
				in = new Intent(getApplicationContext(), Play.class);
				break;
			case R.id.play2:
				in = new Intent(getApplicationContext(), PlayDirections.class);
				break;
			case R.id.play3:
				in = new Intent(getApplicationContext(), PlayGravity.class);
				break;
			case R.id.about:
				in = new Intent(getApplicationContext(), About.class);
				break;
			case R.id.settings:
				
				break;
		}
		startActivity(in);
	}
}