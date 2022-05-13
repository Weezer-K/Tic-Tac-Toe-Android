package cisc181.myproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import cisc181.myproject.views.SquareView;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String player1Piece;
    String player2Piece;
    String specificBackground;
    String specificFont;
    String specificColor;
    String specificColor2;
    String specificFont2;
    String gridColor;
    String profileName;
    String musicChoice;
    String firstTurnDecision;
    public static final String PROFILES = "PROFILE";
    public static final String PLAYER1PIECE = "Player1Piece";
    public static final String PLAYER2PIECE = "PLAYER2PIECE";
    public static final String BACKGROUND = "Background";
    public static final String FONT1 = "Font 1";
    public static final String FONT2 = "Font 2";
    public static final String COLOR2 = "COLOR 2";
    public static final String COLOR1 = "Color 1";
    public static final String MUSIC = "Music";
    public static final String TURN = "Turn";
    String numPlayers;

    public void createProf(String file, String content){
        int i = 0;
        int j = 1;
        while(i == 0) {

            File f = new File("/data/data/cisc181.myproject/shared_prefs/Profile"+j+".xml");
            if (f.exists()) {
                j++;
            }else {
                i = 1;
            }
        }

        SharedPreferences prof = getApplicationContext().getSharedPreferences("Profile"+j, MODE_PRIVATE);
        SharedPreferences.Editor editor = prof.edit();
        editor.putString("Contents", content);
        editor.putString("Profile Name", file);
        editor.putString("Player1Piece", player1Piece);
        editor.putString("Player2Piece", player2Piece);
        editor.putString("Background", specificBackground);
        editor.putString("Font1", specificFont);
        editor.putString("Font2", specificFont2);
        editor.putString("Color1", specificColor);
        editor.putString("Color2", specificColor2);
        editor.putString("gridColor", gridColor);
        editor.putString("Music", musicChoice);
        editor.putString("Turn", firstTurnDecision);
        editor.apply();


    }

    public boolean checkIfNameIsTaken(String file){
        int i = 0;
        int j = 1;
        while(i == 0) {

            File f = new File("/data/data/cisc181.myproject/shared_prefs/Profile"+j+".xml");
            if (f.exists()) {
                SharedPreferences prof = getApplicationContext().getSharedPreferences("Profile"+j, MODE_PRIVATE);
                String profName = prof.getString("Profile Name", " ");
                if(profName.equalsIgnoreCase(file)){
                    return true;
                }
                j++;
            }else {
                i = 1;
            }
        }

        return false;
    }



    public void startGame(){
        if(numPlayers.equalsIgnoreCase("2")) {
            Intent intent = new Intent(this, gameActivity.class);
            intent.putExtra("Player1Piece", player1Piece);
            intent.putExtra("Player2Piece", player2Piece);
            intent.putExtra("background", specificBackground);
            intent.putExtra("color", specificColor);
            intent.putExtra("color2", specificColor2);
            intent.putExtra("font", specificFont);
            intent.putExtra("font2", specificFont2);
            intent.putExtra("gridColor", gridColor);
            intent.putExtra("turn", firstTurnDecision);
            intent.putExtra("music", musicChoice);
            intent.putExtra("profile name", profileName);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, SinglePlayerActivity.class);
            intent.putExtra("Player1Piece", player1Piece);
            intent.putExtra("Player2Piece", player2Piece);
            intent.putExtra("background", specificBackground);
            intent.putExtra("color", specificColor);
            intent.putExtra("color2", specificColor2);
            intent.putExtra("font", specificFont);
            intent.putExtra("font2", specificFont2);
            intent.putExtra("gridColor", gridColor);
            intent.putExtra("turn", firstTurnDecision);
            intent.putExtra("music", musicChoice);
            intent.putExtra("profile name", profileName);
            startActivity(intent);

        }




    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Spinner player1 = findViewById(R.id.player1PieceSpinner);
        final Spinner player2 = findViewById(R.id.player2PieceSpinner);
        final Spinner background = findViewById(R.id.backgroundSpinner);
        Spinner music = findViewById(R.id.musicSpinner);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        numPlayers = (String)b.get("Num Players");
        Spinner color = findViewById(R.id.colortextspinner1);
        Spinner color2 = findViewById(R.id.colorspinner2);
        Spinner gridColors = findViewById(R.id.gridColorSpinner);
        Spinner font = findViewById(R.id.fontspinner1);
        Spinner font2 = findViewById(R.id.fontspinner2);
        Spinner turn = findViewById(R.id.turnDeciderSpinner);
        final EditText profile = findViewById(R.id.profileName);

        ArrayList<String> playerPieces = new ArrayList<>();
        playerPieces.add("Square");
        playerPieces.add("Circle");
        playerPieces.add("A");
        playerPieces.add("B");
        playerPieces.add("C");
        playerPieces.add("D");
        playerPieces.add("E");
        playerPieces.add("F");
        playerPieces.add("G");
        playerPieces.add("H");
        playerPieces.add("I");
        playerPieces.add("J");
        playerPieces.add("K");
        playerPieces.add("L");
        playerPieces.add("M");
        playerPieces.add("N");
        playerPieces.add("O");
        playerPieces.add("P");
        playerPieces.add("Q");
        playerPieces.add("R");
        playerPieces.add("S");
        playerPieces.add("T");
        playerPieces.add("U");
        playerPieces.add("V");
        playerPieces.add("W");
        playerPieces.add("X");
        playerPieces.add("Y");
        playerPieces.add("Z");
        playerPieces.add("a");
        playerPieces.add("b");
        playerPieces.add("c");
        playerPieces.add("d");
        playerPieces.add("e");
        playerPieces.add("f");
        playerPieces.add("g");
        playerPieces.add("h");
        playerPieces.add("i");
        playerPieces.add("j");
        playerPieces.add("k");
        playerPieces.add("l");
        playerPieces.add("m");
        playerPieces.add("n");
        playerPieces.add("o");
        playerPieces.add("p");
        playerPieces.add("q");
        playerPieces.add("r");
        playerPieces.add("s");
        playerPieces.add("t");
        playerPieces.add("u");
        playerPieces.add("v");
        playerPieces.add("w");
        playerPieces.add("x");
        playerPieces.add("y");
        playerPieces.add("0");
        playerPieces.add("1");
        playerPieces.add("2");
        playerPieces.add("3");
        playerPieces.add("4");
        playerPieces.add("5");
        playerPieces.add("6");
        playerPieces.add("7");
        playerPieces.add("8");
        playerPieces.add("9");


        SpinnerAdapter playerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, playerPieces);
        //((ArrayAdapter) playerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        player1.setAdapter(playerAdapter);
        player2.setAdapter(playerAdapter);

        ArrayList<String> backgrounds = new ArrayList<>();
        backgrounds.add("City At Night");
        backgrounds.add("Iron Man");
        backgrounds.add("Spiderman");
        backgrounds.add("SpongeBob");
        backgrounds.add("Star Wars");
        backgrounds.add("Mass Effect");
        backgrounds.add("Mario");
        backgrounds.add("Order 66 Video");
        backgrounds.add("Nature Video");
        backgrounds.add("Sharks Video");
        //backgrounds.add("Iron Man Gulmira Video");
        SpinnerAdapter backgroundAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, backgrounds);
       // ((ArrayAdapter) backgroundAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        background.setAdapter(backgroundAdapter);

        ArrayList<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Magenta");
        colors.add("Cyan");
        colors.add("Black");
        colors.add("Gray");
        colors.add("Random Once");
        colors.add("Random Every Time");

        SpinnerAdapter colorsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, colors);

        color.setAdapter(colorsAdapter);
        color2.setAdapter(colorsAdapter);
        gridColors.setAdapter(colorsAdapter);


        ArrayList<String> fonts = new ArrayList<>();
        fonts.add("Serif");
        fonts.add("Monospace");
        fonts.add("Default Bold");
        fonts.add("Sans Serif");

        SpinnerAdapter fontsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fonts);
       // ((ArrayAdapter) fontsAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        font.setAdapter(fontsAdapter);
        font2.setAdapter(fontsAdapter);


        ArrayList<String> musics = new ArrayList<>();
        musics.add("None");
        musics.add("Aria Da Capo");
        musics.add("Spider-Man Theme");
        musics.add("Iron Man Theme");
        musics.add("Country Roads");
        musics.add("Jackie Moon");
        musics.add("Jelly Fish Jam");
        musics.add("Mario Theme");
        musics.add("Careless Whisper");
        SpinnerAdapter musicAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, musics);
       // ((ArrayAdapter) musicAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        music.setAdapter(musicAdapter);

        ArrayList<String> turns = new ArrayList<>();
        turns.add("Player 1");
        turns.add("Player 2");
        //turns.add("Random Once");
        turns.add("Random Every Time");
        SpinnerAdapter turnAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, turns);
        ((ArrayAdapter) turnAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        turn.setAdapter(turnAdapter);

        turn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                firstTurnDecision = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        music.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 musicChoice= parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });







        player1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                player1Piece = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        player2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                player2Piece = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        background.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                specificBackground = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        font.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                specificFont = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        font2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                specificFont2 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                specificColor = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        color2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                specificColor2 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        gridColors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gridColor = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button play = findViewById(R.id.endOptions);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (profile.getText().toString().length() <= 0) {
                    Toast.makeText(SettingsActivity.this, "Please Enter A Profile Name", Toast.LENGTH_SHORT).show();
                } else if (player1Piece.equalsIgnoreCase(player2Piece)) {
                    Toast.makeText(SettingsActivity.this, "Please Choose Different Pieces", Toast.LENGTH_SHORT).show();
                } else if (!(musicChoice.equalsIgnoreCase("none")) && (specificBackground.contains("Video"))) {
                    Toast.makeText(SettingsActivity.this, "You can't have music and a video", Toast.LENGTH_SHORT).show();
                }else  if(checkIfNameIsTaken(profile.getText().toString())){
                    Toast.makeText(SettingsActivity.this, "Profile Taken Please Choose\nPlease choose another name", Toast.LENGTH_SHORT).show();
                }else{
                    String contents = player1Piece+"\n"+player2Piece+"\n"+specificBackground+"\n"+specificFont+"\n"+
                            specificFont2+"\n"+specificColor+"\n"+specificColor2+"\n"+gridColor+"\n"+firstTurnDecision+"\n"+musicChoice;
                    String file = profile.getText().toString() ;

                    createProf(file, contents);
                    startGame();
                }
            }
        });










    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
