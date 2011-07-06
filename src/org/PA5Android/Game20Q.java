package org.PA5Android;

import java.util.*;

import android.util.Log;

public class Game20Q 
{
	private HashMap <String , LinkedList<String>> game;
	private String key;
	private boolean gameOver = false;
	
	public  Game20Q()
	{		
		game = getDefualtGame();
	}
	public  Game20Q(HashMap <String , LinkedList<String>> game)
	{
		this.game = game;
	}
	public HashMap<String, LinkedList<String>> getDefualtGame()
	{
		LinkedList <String> n = new LinkedList<String>();
		
		n.add("You Lost, play again?");
		n.add("You Won, what was your animal (.) and another question?");
		
		game = new HashMap <String,LinkedList<String>>();
						
		game.put("Is it a Zebra?", n);

		key = "Is it a Zebra?";
		
		return game;
	}
	//Adds questions to the game if the player lost
	
	public void addQ(String key, LinkedList<String> values, String animal, String clue)
	{
		
		Log.d("BeforeAddingMap", game.toString());

		Log.d("Adding", ""+key);
		//in charge of replacing the no question
		game.remove(key);
		LinkedList <String> Nvalue  = new LinkedList<String>();
		Nvalue.add(values.get(0));
		Nvalue.add(clue);
		
		game.put(key, Nvalue);
		
		//adds values to the new question
		Nvalue = new LinkedList<String>();
		Nvalue.add("Is it " +animal +"?");
		Nvalue.add("You Won, what was your animal (.) and another question?");
		
		game.put(clue, Nvalue);
		
		Nvalue = new LinkedList<String>();
		Nvalue.add("You Lost, play again?");
		Nvalue.add("You Won, what was your animal (.) and another question?");
		
		game.put("Is it " +animal +"?", Nvalue);
		
		Log.d("AfterAddingMap", game.toString());
	}
	public void play(String answer)
	{		
			//Continues yes
			if(answer.equalsIgnoreCase("yes"))
			{
				key = game.get(key).get(0);
			}
			//Win + add new Animal to DataBase
			else if(answer.equalsIgnoreCase("no") && game.get(key).get(1).equalsIgnoreCase("You Won, what was your animal (.) and another question?"))
			{
				//key = game.get(key).get(1);
				PA5Main.adding = true;
			}
			//Continues no
			else if(answer.equalsIgnoreCase("no"))
			{
				key = game.get(key).get(1);
			}	
	}
	public void restart()
	{
		gameOver = false;
		key = "Is it a Zebra?";
	}
	public HashMap<String, LinkedList <String>> getGame()
	{
		return game;
	}
	public String getKey()
	{
		return key;
	}
}
