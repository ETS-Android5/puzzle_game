package lv.marmog.androidpuzzlegame;

import static lv.marmog.androidpuzzlegame.database.DatabaseHelper.TABLE_TIMER;
import static lv.marmog.androidpuzzlegame.database.DatabaseHelper.TABLE_USERS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import lv.marmog.androidpuzzlegame.database.DatabaseHelper;
import lv.marmog.androidpuzzlegame.database.User;
import lv.marmog.androidpuzzlegame.database.UserDAO;

public class ScoreActivity extends AppCompatActivity {

    TextView congratulationText;
    TextView yourTimeText;
    TextView yourTime;
    TextView BestTimeText;
    TextView BestTime;
    Button next;
    Button exit;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    int userIdInteger, levelInteger;
    String receivedValue;
    private String [] allColumns = {
            DatabaseHelper.COLUMN_USER_ID,
            DatabaseHelper.COLUMN_TIMER_RESULT_FOR_2,
            DatabaseHelper.COLUMN_TIMER_RESULT_FOR_4,
            DatabaseHelper.COLUMN_TIMER_RESULT_FOR_6,
            DatabaseHelper.COLUMN_TIMER_RESULT_FOR_9,
            DatabaseHelper.COLUMN_TIMER_RESULT_FOR_12,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        dbHelper = new DatabaseHelper(ScoreActivity.this);
        database = dbHelper.getWritableDatabase();

        //showing seconds from puzzleActivity
        yourTime = (TextView) findViewById(R.id.your_time);
        Intent getTimeIntent = getIntent();
        receivedValue = getTimeIntent.getStringExtra("KEY_SEND");
        int userId = getTimeIntent.getIntExtra("userId", 0);
        int level = getTimeIntent.getIntExtra("level", 0);
        Log.w(ScoreActivity.class.getName(), "Level is " + level);
        Log.w(ScoreActivity.class.getName(), "Id is " + userId);
        yourTime.setText(receivedValue);
        userIdInteger = Integer.valueOf(userId);
        levelInteger = Integer.valueOf(level);
        insertResult();
    }
    public boolean insertResult(){
        ContentValues contentValues = new ContentValues();


        contentValues.put(DatabaseHelper.COLUMN_USER_ID, userIdInteger);
        long insertResult = database.insert(TABLE_TIMER, null, contentValues);

        Cursor cursor = database.query(TABLE_TIMER, allColumns,
                DatabaseHelper.COLUMN_USER_ID + " = " + insertResult, null, null, null, null);

        cursor.moveToLast();
        cursor.close();
        switch (levelInteger){
        case 4:
            contentValues.put(DatabaseHelper.COLUMN_TIMER_RESULT_FOR_4, receivedValue);
            long insertResult1 = database.insert(TABLE_TIMER, null, contentValues);
            Cursor cursor1 = database.query(TABLE_TIMER, allColumns,
                    DatabaseHelper.COLUMN_TIMER_RESULT_FOR_4 + " = " + insertResult1, null, null, null, null);
            cursor1.moveToLast();
            cursor1.close();
        break;

            case 9:
            contentValues.put(DatabaseHelper.COLUMN_TIMER_RESULT_FOR_9, receivedValue);
                long insertResult2 = database.insert(TABLE_TIMER, null, contentValues);
                Cursor cursor2 = database.query(TABLE_TIMER, allColumns,
                        DatabaseHelper.COLUMN_TIMER_RESULT_FOR_9 + " = " + insertResult2, null, null, null, null);
                cursor2.moveToLast();
                cursor2.close();
        break;

            case 12:
            contentValues.put(DatabaseHelper.COLUMN_TIMER_RESULT_FOR_12, receivedValue);
                long insertResult3 = database.insert(TABLE_TIMER, null, contentValues);
                Cursor cursor3 = database.query(TABLE_TIMER, allColumns,
                        DatabaseHelper.COLUMN_TIMER_RESULT_FOR_12 + " = " + insertResult3, null, null, null, null);
                cursor3.moveToLast();
                cursor3.close();
        break;
        }

        if(insertResult == -1) {
            Log.e(ScoreActivity.class.getName(), "Results are not saved");
            return false;
        }  else {
            Log.i(ScoreActivity.class.getName(), "Results are saved");
            return true;
        }
    }
    public void startNewGame(View view){
        Intent intent = new Intent(this, ComplexityActivity.class);// redirect from this page to MainActivity page- list of images
        startActivity(intent);
    }

    public void goToMenu(View view){
        // redirect from this activity to the first activity, for now it redirects to MianActivy!!!!!!!!!!!!!!!!
        Intent intent = new Intent(this, ComplexityActivity.class);
        startActivity(intent);
    }

}