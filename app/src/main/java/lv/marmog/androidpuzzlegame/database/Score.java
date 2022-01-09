package lv.marmog.androidpuzzlegame.database;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Score {
    Timer score;
    TimerDAO timerDataSource;

    public Score() {
    }

    public Score(Context c) {
        score = new Timer();
        timerDataSource = new TimerDAO();
        timerDataSource.open();
    }


    public Timer getScoreFromDb(int userId, int level, Context c){
        score = timerDataSource.getScore(userId, level, c);
        return score;
    }

}
