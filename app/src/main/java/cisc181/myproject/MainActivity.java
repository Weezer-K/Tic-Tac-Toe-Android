package cisc181.myproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videv;
    Button instructions;
    Button quit;
    Button players2;
    Button players1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videv = (VideoView) findViewById(R.id.videoView);
        instructions = (Button) findViewById(R.id.instrutionsButton);
        playVideo();
        quit = (Button) findViewById(R.id.quitButton);


        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startInstrctionsActivity();
            }

        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }

        });

        players2 = findViewById(R.id.twoPlayer);
        players2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }

        });
        players1 = findViewById(R.id.singleplayer);
        players1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameCPU();
            }

        });




    }
    public void playVideo(){
        String videopath = "android.resource://" + getPackageName() + "/" + R.raw.strategy;
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
    public void startInstrctionsActivity(){
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
    }
    public void startGame(){
        Intent intent = new Intent(this, LoadProfileActivity.class);
        intent.putExtra("Num Players", "2");
        startActivity(intent);
    }
    public void startGameCPU(){
        Intent intent = new Intent(this, LoadProfileActivity.class);
        intent.putExtra("Num Players", "1");
        startActivity(intent);
    }

}
