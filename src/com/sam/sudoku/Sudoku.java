package com.sam.sudoku;

import android.os.Bundle;
import com.apptimize.Apptimize;
import com.apptimize.ApptimizeExperiment;
import android.view.Menu;
import android.view.MenuInflater; 
import android.view.MenuItem;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.content.DialogInterface; 
import android.util.Log;
public class Sudoku extends Activity implements OnClickListener {
	private static final String TAG = "Sudoku";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Apptimize.setup(this, "caSlcs0977sDSKkcskas");
		setContentView(R.layout.activity_main);
		
		
		// Set up click listeners for all the buttons
		View continueButton = findViewById(R.id.continue_button); 
		continueButton.setOnClickListener(this);
		View newButton = findViewById(R.id.new_button); 
		newButton.setOnClickListener(this);
		View aboutButton = findViewById(R.id.about_button); 
		aboutButton.setOnClickListener(this);
		View exitButton = findViewById(R.id.exit_button); 
		exitButton.setOnClickListener(this);
	}
	
	public void onClick(View v) { 
		switch (v.getId()) {
			case R.id.about_button:
				Intent i = new Intent(this, About.class); 
				startActivity(i);
				break;
			case R.id.new_button: 
				Apptimize.goalReached(555);
				openNewGameDialog(); 
				break;
			case R.id.exit_button: 
				finish();
				break;
		} 
	}
	
	/** Ask the user what difficulty level they want */
	private void openNewGameDialog() { 
		new AlertDialog.Builder(this)
			.setTitle(R.string.new_game_title)
			.setItems(R.array.difficulty,
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialoginterface, int i) { 
					startGame(i);
				}
			})
			.show();
	}
	
	/** Start a new game with the given difficulty level */
	private void startGame(int i) { 
		Log.d(TAG, "clicked on " + i); 
		Intent intent = new Intent(Sudoku.this, Game.class); 
		intent.putExtra(Game.KEY_DIFFICULTY, i); 
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settings:
			startActivity(new Intent(this, Prefs.class));
			return true;
		}
		return false;
	}

}
