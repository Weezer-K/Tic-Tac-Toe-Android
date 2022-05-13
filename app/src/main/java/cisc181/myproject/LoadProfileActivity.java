package cisc181.myproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LoadProfileActivity extends AppCompatActivity {

    String player1Piece;
    String player2Piece;
    String color1;
    String color2;
    String font1;
    String font2;
    String gridColor;
    String background;
    String turn;
    String music;
    String specificProfile;
    String numPlayers;

    public void startGame(){
        if(numPlayers.equalsIgnoreCase("2")) {
            Intent intent = new Intent(this, gameActivity.class);
            intent.putExtra("Player1Piece", player1Piece);
            intent.putExtra("Player2Piece", player2Piece);
            intent.putExtra("background", background);
            intent.putExtra("color", color1);
            intent.putExtra("color2", color2);
            intent.putExtra("font", font1);
            intent.putExtra("font2", font2);
            intent.putExtra("gridColor", gridColor);
            intent.putExtra("turn", turn);
            intent.putExtra("music", music);
            intent.putExtra("profile name", " ");


            startActivity(intent);
        }else{
            Intent intent = new Intent(this, SinglePlayerActivity.class);
            intent.putExtra("Player1Piece", player1Piece);
            intent.putExtra("Player2Piece", player2Piece);
            intent.putExtra("background", background);
            intent.putExtra("color", color1);
            intent.putExtra("color2", color2);
            intent.putExtra("font", font1);
            intent.putExtra("font2", font2);
            intent.putExtra("gridColor", gridColor);
            intent.putExtra("turn", turn);
            intent.putExtra("music", music);
            intent.putExtra("profile name", " ");


            startActivity(intent);
        }

    }

    public void startSettings(){
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("Num Players", numPlayers);
        startActivity(intent);
    }

    public ArrayList<String> loadProfNameForListView(){
        ArrayList<String> answer = new ArrayList<>();
        int i = 0;
        int j = 1;
        String fileName;
        while(i == 0) {
            File f = new File(
                    "/data/data/cisc181.myproject/shared_prefs/Profile"+j+".xml");
            if (f.exists()) {
                fileName = "Profile" + j;
                SharedPreferences prof = getSharedPreferences("Profile" + j, 0);
                answer.add( prof.getString("Profile Name", "Hello"));
                j++;
            }
            else
                i = 1;
        }
        return answer;
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_profile);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        numPlayers = (String)b.get("Num Players");

            ListView listView = findViewById(R.id.profileListView);
            ArrayList<String> profileNames = new ArrayList<>(loadProfNameForListView());
            if (profileNames != null) {
                ListAdapter choiceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, profileNames);
                listView.setAdapter(choiceAdapter);
            }

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int profileNum =(position) + 1;
                        SharedPreferences prof = getSharedPreferences("Profile" + profileNum, 0);
                        player1Piece = prof.getString("Player1Piece", "");
                        player2Piece = prof.getString("Player2Piece", "");
                        background = prof.getString("Background", "");
                        font1 = prof.getString("Font1","");
                        font2 = prof.getString("Font2","");
                        color1 = prof.getString("Color1","");
                        color2 = prof.getString("Color2","");
                        gridColor = prof.getString("gridColor","");
                        music = prof.getString("Music","");
                        turn = prof.getString("Turn","");
                        startGame();






                    }
                }
        );



        Button createProfile = (Button) findViewById(R.id.newProfile);

        createProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSettings();
            }

        });
    }
}
