package org.PA5Android;

import org.PA5Android.GameStorage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class  PA5Main extends Activity {
		
/** Called when the activity is first created. */

	//Intent k=new Intent(this , GameStorage.class);
	static Game20Q g; 
	static boolean adding=false; //Sees if the game should be adding a question
@Override
public void onCreate(Bundle savedInstanceState) 
{
	super.onCreate(savedInstanceState);//TextView tv = new TextView(this);
	
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
	
	final Game20Q g = new Game20Q();
	EditText et = (EditText) findViewById(R.id.entry);
	et.setText(g.getKey());
	
	//Yes/Submit button
	final Button button = (Button) findViewById(R.id.yes);
	button.setOnClickListener(new View.OnClickListener() {
	public void onClick(View v) {
	// Perform action on click
	EditText et = (EditText) findViewById(R.id.entry);
	
	//Calls the add Method
	
	Log.d("Adding", ""+adding);

	if (adding == true )
	{
		String animal = et.getText().toString().substring(0,et.getText().toString().lastIndexOf("."));
		String clue = et.getText().toString().substring(et.getText().toString().lastIndexOf(".")+1);
		Log.d("Animal", "" + animal);
		Log.d("Clue", "" + clue);
		
		g.addQ(g.getKey(), g.getGame().get(g.getKey()), animal, clue);
		
		et.setText("Play again?");
		adding = false;
	}
	else if(et.getText().toString().equals("You Lost, play again?") || et.getText().toString().equals("Play again?"))
	{
		g.restart();
		et.setText(g.getKey());
	}

	else
	{
		g.play("yes");
		et.setText(g.getKey());
	}
}
});
//No Button
final Button no = (Button) findViewById(R.id.no);
no.setOnClickListener(new View.OnClickListener() {
public void onClick(View v) 
{
	EditText et = (EditText) findViewById(R.id.entry);
	
	if (et.getText().toString().equals("You Lost, play again?") || et.getText().toString().equals("Play again?"))
	{
		finish();
       // startActivity(k);
	}
	//Do Nothing when the game is adding a question
	else if(adding==true)
	{
		
	}
	else
	{
		//Deals with when key doesn't change, meaning the game is over and it should be adding
		String oldKey = g.getKey();
		g.play("no");
		if(oldKey.equalsIgnoreCase(g.getKey()))
		{
			et.setText(g.getGame().get(g.getKey()).get(1));
		}
		else
		{
			et.setText(g.getKey());
		}
	}
}
});
}

public static Game20Q getGame()
{
	return g;
}
}