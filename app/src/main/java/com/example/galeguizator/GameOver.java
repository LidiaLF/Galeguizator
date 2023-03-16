package com.example.galeguizator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {
    TextView tvPoints;
    TextView tvHighest;
    SharedPreferences sharedPreferences;
    ImageView ivNewScore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        tvPoints = findViewById(R.id.tv_points);
        tvHighest = findViewById(R.id.tv_highest);
        ivNewScore = findViewById(R.id.iv_newScore);

        int points = getIntent().getExtras().getInt("points");
        tvPoints.setText(Integer.toString(points));

        sharedPreferences = getSharedPreferences("my_pref", 0);

        int highest = sharedPreferences.getInt("highest", 0);
        if(points > highest){
            ivNewScore.setVisibility(View.VISIBLE);
            highest = points;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("highest", highest);
            editor.commit();
        }
        tvHighest.setText("" + highest);
    }
    public void restart(View view){
        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void exit(View view){
        finish();
    }
}
