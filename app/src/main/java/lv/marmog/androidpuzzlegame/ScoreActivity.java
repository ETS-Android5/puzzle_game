package lv.marmog.androidpuzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {


    TextView yourTime;
    TextView BestTime;
    Button next;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        //showing seconds from puzzleActivity
        yourTime = (TextView) findViewById(R.id.your_time);
        Intent getTimeIntent = getIntent();
        String receivedValue = getTimeIntent.getStringExtra("KEY_SEND");
        yourTime.setText(receivedValue);
    }
    public void startNewGame(View view){
        Intent intent = new Intent(this, ComplexityActivity.class);// redirect from this page to MainActivity page- list of images
        startActivity(intent);
    }


}