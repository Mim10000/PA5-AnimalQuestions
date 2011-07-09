/***
 * Excerpted from "Hello, Android!",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/eband for more book information.
***/
package org.PA5Android;

import static android.provider.BaseColumns._ID;
import static org.PA5Android.Constants.TABLE_NAME;
import static org.PA5Android.Constants.GAME;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventsData extends SQLiteOpenHelper {
	
	private static final String TAG = "GStorage_DB";

   private static final String DATABASE_NAME = "giraffe.db";
   private static final int DATABASE_VERSION = 1;

   /** Create a helper object for the Events database */
   public EventsData(Context ctx) {
      super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
   }

   @Override
   public void onCreate(SQLiteDatabase gdb) {
      gdb.execSQL("CREATE TABLE " + TABLE_NAME + " ("+_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				 + GAME + " TEXT NOT NULL);");
   }

   @Override
   public void onUpgrade(SQLiteDatabase gdb, int oldVersion,
         int newVersion) {
      gdb.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
      onCreate(gdb);
   }

}