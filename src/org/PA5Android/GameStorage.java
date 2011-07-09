package org.PA5Android;

import static android.provider.BaseColumns._ID;
import static org.PA5Android.Constants.TABLE_NAME;

//For the date
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.PA5Android.Constants.GAME;

import android.app.ListActivity;
// ...

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;
import android.widget.SimpleCursorAdapter;
// ...



public class GameStorage extends ListActivity {
   // ...
   private static String[] FROM = { _ID, GAME,};
   private static String ORDER_BY = _ID + " DESC";
   
   private static int[] TO = {  R.id.rowid,R.id.game, };
   
   private EventsData events;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
      events = new EventsData(this);
      try {
         addEvent(PA5Main.getGame());
         
         Cursor cursor = getEvents();
         showEvents(cursor);
      } finally {
         events.close();
      }
   }

   private void addEvent(Game20Q game20q) {
      // Insert a new record into the Events data source.
      // You would do something similar for delete and update.
      SQLiteDatabase db = events.getWritableDatabase();
      ContentValues values = new ContentValues();
      
      values.put("Tag",game20q.toString());

      db.insertOrThrow(TABLE_NAME, null, values);
   }

   private Cursor getEvents() {
      // Perform a managed query. The Activity will handle closing
      // and re-querying the cursor when needed.
      SQLiteDatabase db = events.getReadableDatabase();
      Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null,
            null, ORDER_BY);
      startManagingCursor(cursor);
      return cursor;
   }

   
   private void showEvents(Cursor cursor) {
      // Set up data binding
      SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
            R.layout.item, cursor, FROM, TO);
      setListAdapter(adapter);
   }
   private String getDateTime() {
       DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
       Date date = new Date();
       return dateFormat.format(date);
   }
   
}
