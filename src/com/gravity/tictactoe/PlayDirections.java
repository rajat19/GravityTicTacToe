package com.gravity.tictactoe;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayDirections extends Activity implements OnClickListener {

	Button x[][] = new Button[3][3];
	Button startPlay, leftRotate, rightRotate, downRotate;
	TextView turn;
	String s = "X";
	int wpos[][] = new int[3][2];
	String wplayer;
	int wpoint;
	boolean won = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_directions);
		x[0][0] = (Button) findViewById(R.id.b11);
		x[0][1] = (Button) findViewById(R.id.b12);
		x[0][2] = (Button) findViewById(R.id.b13);
		x[1][0] = (Button) findViewById(R.id.b21);
		x[1][1] = (Button) findViewById(R.id.b22);
		x[1][2] = (Button) findViewById(R.id.b23);
		x[2][0] = (Button) findViewById(R.id.b31);
		x[2][1] = (Button) findViewById(R.id.b32);
		x[2][2] = (Button) findViewById(R.id.b33);
		startPlay = (Button) findViewById(R.id.startPlay);
		leftRotate = (Button) findViewById(R.id.leftrotate);
		rightRotate = (Button) findViewById(R.id.rightrotate);
		downRotate = (Button) findViewById(R.id.downrotate);
		
		turn = (TextView) findViewById(R.id.turn);
		
		if(won == false) {
			x[0][0].setOnClickListener(this);
			x[0][1].setOnClickListener(this);
			x[0][2].setOnClickListener(this);
			x[1][0].setOnClickListener(this);
			x[1][1].setOnClickListener(this);
			x[1][2].setOnClickListener(this);
			x[2][0].setOnClickListener(this);
			x[2][1].setOnClickListener(this);
			x[2][2].setOnClickListener(this);
			leftRotate.setOnClickListener(this);
			rightRotate.setOnClickListener(this);
			downRotate.setOnClickListener(this);
			startPlay.setText("End game");
			
			if(startPlay.getText().toString().equals("End game")) {
				startPlay.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent in = new Intent(getApplicationContext(), Home.class);
						startActivity(in);
					}
				});
			}
		}
		else {
			stopPlaying();
			startPlay.setText("Start new game");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(s.equals("X"))
			turn.setText("O's turn");
		else if(s.equals("O"))
			turn.setText("X's turn");
		
		switch(v.getId()) {
			case R.id.b11:
				fill(x[0][0]);
				break;
			case R.id.b12:
				fill(x[0][1]);
				break;
			case R.id.b13:
				fill(x[0][2]);
				break;
			case R.id.b21:
				fill(x[1][0]);
				break;
			case R.id.b22:
				fill(x[1][1]);
				break;
			case R.id.b23:
				fill(x[1][2]);
				break;
			case R.id.b31:
				fill(x[2][0]);
				break;
			case R.id.b32:
				fill(x[2][1]);
				break;
			case R.id.b33:
				fill(x[2][2]);
				break;
			case R.id.leftrotate:
				swapleftXY();
				pushDown();
				break;
			case R.id.rightrotate:
				swaprightXY();
				pushDown();
				break;
			case R.id.downrotate:
				pushDown();
				break;
		}
		
		won = checkwinner(x);
		if(won) {
			for(int i = 0; i < 3; i ++) {
				x[wpos[i][0]][wpos[i][1]].setBackgroundColor(Color.RED);
			}
			startPlay.setText("Start new game");
			
		}
	}

	private void pushDown() {
		// TODO Auto-generated method stub
		if(!won) {
			for(int i = 1; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					String val = x[i][j].getText().toString();
					if(val.equals("")) {
						String newval = x[i - 1][j].getText().toString();
						x[i][j].setText(newval);
						x[i - 1][j].setText("");
					}
				}
			}
		}
	}

	private void swaprightXY() {
		// TODO Auto-generated method stub
		if(won) {
			stopPlaying();
		}
		else {
			String ar[][] = new String[3][3];
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					ar[i][j] = x[2 - j][i].getText().toString();
				}
			}
			
			//swap the values
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					x[i][j].setText(ar[i][j]);
				}
			}
		}
	}
	
	private void swapleftXY() {
		// TODO Auto-generated method stub
		if(won) {
			stopPlaying();
		}
		else {
			String ar[][] = new String[3][3];
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					ar[i][j] = x[j][2 - i].getText().toString();
				}
			}
			
			//swap the values
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					x[i][j].setText(ar[i][j]);
				}
			}
		}
	}

	private void fill(Button x) {
		// TODO Auto-generated method stub
		if(won) {
			stopPlaying();
		}
		else {
			String val = x.getText().toString();
			if(val.equals("")) {
				x.setText(s);
				if(s.equals("X"))
					s = "O";
				else if(s.equals("O"))
					s = "X";
			}
			else {
				Toast.makeText(PlayDirections.this, "This position is already filled",  2000).show();
			}
		}
	}
	
	private void stopPlaying() {
		// TODO Auto-generated method stub
		Toast.makeText(PlayDirections.this, "The game has ended", 2000).show();
	}

	private boolean checkwinner(Button[][] arr) {
		// TODO Auto-generated method stub
		String val[][] = new String[3][3];
		for(int i = 0; i<3; i++) {
			for(int j = 0; j < 3; j++) {
				val[i][j] = arr[i][j].getText().toString();
			}
		}
		
		for(int i = 0; i<3; i++) {
			if(val[i][1].equals(val[i][2]) && val[i][0].equals(val[i][2]) && !val[i][0].equals("")) {
				wpoint = i + 1;
				wplayer = val[i][0];
				int w[][] = {{i, 0}, {i, 1}, {i, 2}};
				wpos = w;
				return true;
			}
			else if(val[1][i].equals(val[2][i]) && val[0][i].equals(val[2][i]) && !val[0][i].equals("")) {
				wpoint = i + 4;
				wplayer = val[0][i];
				int w[][] = {{0, i}, {1, i}, {2, i}};
				wpos = w;
				return true;
			}
		}
		if(val[1][1].equals(val[2][2]) && val[0][0].equals(val[2][2]) && !val[1][1].equals("")) {
			wpoint = 7;
			wplayer = val[1][1];
			int w[][] = {{0, 0}, {1, 1}, {2, 2}};
			wpos = w;
			return true;
		}
		else if(val[0][2].equals(val[1][1]) && val[2][0].equals(val[1][1]) && !val[1][1].equals("")) {
			wpoint = 8;
			wplayer = val[2][2];
			int w[][] = {{0, 2}, {1, 1}, {2, 0}};
			wpos = w;
			return true;
		}
		return false;
	}
}