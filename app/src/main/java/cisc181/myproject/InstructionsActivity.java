package cisc181.myproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class InstructionsActivity extends AppCompatActivity {

    Button play;
    VideoView videv;
    MediaController mediaC;

    public void back(){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        play = (Button) findViewById(R.id.playInstruct);
        videv = (VideoView) findViewById(R.id.videoinstruct);


        Button backButton = (Button) findViewById(R.id.backButtonInstruct);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }

        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPlay();
            }

        });
    }

    public void videoPlay(){
            videv = (VideoView) findViewById(R.id.videoinstruct);
            String videopath = "android.resource://" + getPackageName() + "/" + R.raw.instructions;
            Uri uri = Uri.parse(videopath);
            videv.setVideoURI(uri);
            MediaController mediaController = new MediaController(this);
            videv.setMediaController(mediaController);
            mediaController.setAnchorView(videv);
            videv.start();





    }
}
