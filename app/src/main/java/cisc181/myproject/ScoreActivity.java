package cisc181.myproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;

public class ScoreActivity extends AppCompatActivity {


    public void mainMenu()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        int player1score = (int) b.get("Player 1 score");
        int player2score = (int) b.get("Player 2 score");
        int total = (int) b.get("Total games");
        VideoView videv = (VideoView) findViewById(R.id.endcredits);
        videoPlay(videv);
        TextView player1Text = findViewById(R.id.player1finalscore);
        TextView player2Text = findViewById(R.id.player2finalscore);
        TextView totalNumberOfGames = findViewById(R.id.totalNumberOfGames);
        player1Text.setText("Player one won "+player1score+" times");
        player2Text.setText("Player two won "+player2score+" times");
        totalNumberOfGames.setText("You played "+total+" games!!!");
        Button quitApplication = (Button) findViewById(R.id.quitProgram);
        Button mainMenu = (Button) findViewById(R.id.mainMenu);
        int highScore = findHighScore(total);
        TextView highscoreText = (TextView) findViewById(R.id.highScore);
        highscoreText.setText("High Score : "+highScore+" games!!!");






        quitApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }

        });

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainMenu();
            }

        });






    }
    public void videoPlay(VideoView videv){

        String videopath = "android.resource://" + getPackageName() + "/" + R.raw.credits;
        Uri uri = Uri.parse(videopath);
        videv.setVideoURI(uri);
        videv.start();
        videv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

    }

    public int findHighScore(int total){
        File f = new File("/data/data/cisc181.myproject/shared_prefs/HighScore.xml");
        if (f.exists()) {
            SharedPreferences highScore = getSharedPreferences("HighScore", 0);
            int n = highScore.getInt("Score", 0);
            if (n >= total){
                return n;
            }else{
                return total;
            }

        }else {
            SharedPreferences prof = getApplicationContext().getSharedPreferences("HighScore", MODE_PRIVATE);
            SharedPreferences.Editor editor = prof.edit();
            editor.putInt("Score", total);
            editor.apply();
        }

        return total;
    }
}
