package lv.marmog.androidpuzzlegame.database;

import static lv.marmog.androidpuzzlegame.database.DatabaseHelper.COLUMN_TIMER_RESULT_FOR_12;
import static lv.marmog.androidpuzzlegame.database.DatabaseHelper.COLUMN_TIMER_RESULT_FOR_4;
import static lv.marmog.androidpuzzlegame.database.DatabaseHelper.COLUMN_TIMER_RESULT_FOR_9;
import static lv.marmog.androidpuzzlegame.database.DatabaseHelper.COLUMN_USER_ID;
import static lv.marmog.androidpuzzlegame.database.DatabaseHelper.TABLE_TIMER;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

public class TimerDAO {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String[] allColumns = {
//            DatabaseHelper.COLUMN_TIMER_RESULT_FOR_2,
            DatabaseHelper.COLUMN_TIMER_RESULT_FOR_4};
//            DatabaseHelper.COLUMN_TIMER_RESULT_FOR_6,
//            DatabaseHelper.COLUMN_TIMER_RESULT_FOR_9,
//            DatabaseHelper.COLUMN_TIMER_RESULT_FOR_12,
//            DatabaseHelper.COLUMN_USER_ID};

    //Constructor
    public TimerDAO() {
    }

    public TimerDAO(Context context) {
        dbHelper = new DatabaseHelper(context);

    }

    public void open() throws SQLException {
        database = dbHelper.getReadableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // method to get result from db
    public Timer getScore(int userId, int level, Context c) {
//        Cursor cursor = null;

        DatabaseHelper db = new DatabaseHelper(c);
        SQLiteDatabase database = db.getReadableDatabase();

        Timer score;
//        switch (level) {
//            case 4:
//            cursor = database.rawQuery("SELECT " + COLUMN_TIMER_RESULT_FOR_4 + " FROM " +
//                    TABLE_TIMER + " WHERE " + COLUMN_USER_ID + " = " + userId +
//                    " ORDER BY " + COLUMN_TIMER_RESULT_FOR_4 + " ASC LIMIT 1", null);
//            break;
//            case 9:
//                cursor = database.rawQuery("SELECT " + COLUMN_TIMER_RESULT_FOR_9 + " FROM " +
//                        TABLE_TIMER + " WHERE " + COLUMN_USER_ID + " = " + userId +
//                        " ORDER BY " + COLUMN_TIMER_RESULT_FOR_4 + " ASC LIMIT 1", null);
//                break;
//
//            case 12:
//                cursor = database.rawQuery("SELECT " + COLUMN_TIMER_RESULT_FOR_12 + " FROM " +
//                        TABLE_TIMER + " WHERE " + COLUMN_USER_ID + " = " + userId +
//                        " ORDER BY " + COLUMN_TIMER_RESULT_FOR_4 + " ASC LIMIT 1", null);
//                break;
//        }

        Cursor cursor = database.rawQuery("SELECT " + COLUMN_TIMER_RESULT_FOR_4 + " FROM " +
                    TABLE_TIMER + " WHERE " + COLUMN_USER_ID + " = " + userId + " AND " +
                        COLUMN_TIMER_RESULT_FOR_4 + " IS NOT NULL " +
                    " ORDER BY " + COLUMN_TIMER_RESULT_FOR_4 + " ASC LIMIT 1", null);

        if (!cursor.moveToFirst()) {
            cursor.moveToFirst();
        }

        score = cursorToScore(cursor);
        cursor.close();
        return score;
    }

    private Timer cursorToScore(Cursor cursor) {
//        int score_2 = cursor.getInt(cursor.getColumnIndexOrThrow("timer_result_for_2"));
        int score_4 = cursor.getInt(cursor.getColumnIndexOrThrow("timer_result_for_4"));
//        int score_6 = cursor.getInt(cursor.getColumnIndexOrThrow("timer_result_for_6"));
//        int score_9 = cursor.getInt(cursor.getColumnIndexOrThrow("timer_result_for_9"));
//        int score_12 = cursor.getInt(cursor.getColumnIndexOrThrow("timer_result_for_12"));
//        int userId = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));

        Timer scores = new Timer();
//        scores.setBestScore_2(score_2);
        scores.setBestScore_4(score_4);
//        scores.setBestScore_6(score_6);
//        scores.setBestScore_9(score_9);
//        scores.setBestScore_12(score_12);
//        scores.setUserId(userId);
        return scores;

    }


}
