package cisc181.myproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.lang.reflect.Array;
import java.util.Random;

import cisc181.myproject.views.CircleView;
import cisc181.myproject.views.CustomView;
import cisc181.myproject.views.SquareView;


public class gameActivity extends AppCompatActivity implements View.OnTouchListener, Animation.AnimationListener {
    //Player one goes first and is X
    String player1Piece;
    int stretchSound;
    int bellSound;
    String turn;
    int roundOver;
    String color;
    MediaPlayer player;
    Boolean randomOnce;
    String color2;
    String font;
    String player2Piece;
    String background;
    String font2;
    boolean player1Turn = true;
    int score1 = 0;
    int score2 = 0;
    SoundPool soundPool;
    int numberGames = 0;
    VideoView videv;
    VideoView vide16by9;
    boolean g00 = false;
    boolean g01 = false;
    boolean g02 = false;
    boolean g10 = false;
    boolean g11 = false;
    boolean g12 = false;
    boolean g20 = false;
    boolean g21 = false;
    boolean g22 = false;
    String[][] boardStatus = new String[3][3];


    public void initBoard(){
        Button quit = findViewById(R.id.quit);
        TextView grid00 = findViewById(R.id.grid00);
        TextView grid01 = findViewById(R.id.grid01);
        TextView grid02 = findViewById(R.id.grid02);
        TextView grid10 = findViewById(R.id.grid10);
        TextView grid11 = findViewById(R.id.grid11);
        TextView grid12 = findViewById(R.id.grid12);
        TextView grid20 = findViewById(R.id.grid20);
        TextView grid21 = findViewById(R.id.grid21);
        TextView grid22 = findViewById(R.id.grid22);
        Button nextGame = findViewById(R.id.nextGame);
        SquareView s00 = findViewById(R.id.square00);
        SquareView s01 = findViewById(R.id.square01);
        SquareView s02 = findViewById(R.id.square02);
        SquareView s10 = findViewById(R.id.square10);
        SquareView s11 = findViewById(R.id.square11);
        SquareView s12 = findViewById(R.id.square12);
        SquareView s20 = findViewById(R.id.square20);
        SquareView s21 = findViewById(R.id.square21);
        SquareView s22 = findViewById(R.id.square22);
        CircleView c00 = findViewById(R.id.circle00);
        CircleView c01 = findViewById(R.id.circle01);
        CircleView c02 = findViewById(R.id.circle02);
        CircleView c10 = findViewById(R.id.circle10);
        CircleView c11 = findViewById(R.id.circle11);
        CircleView c12 = findViewById(R.id.circle12);
        CircleView c20 = findViewById(R.id.circle20);
        CircleView c21 = findViewById(R.id.circle21);
        CircleView c22 = findViewById(R.id.circle22);
        CustomView grid= findViewById(R.id.gridView);
        grid.clearAnimation();
        boardStatus[0][0] = "1";
        boardStatus[0][1] = "2";
        boardStatus[0][2] = "3";
        boardStatus[1][0] = "4";
        boardStatus[1][1] = "5";
        boardStatus[1][2] = "6";
        boardStatus[2][0] = "7";
        boardStatus[2][1] = "8";
        boardStatus[2][2] = "9";
        grid00.setText("");
        grid01.setText("");
        grid02.setText("");
        grid10.setText("");
        grid11.setText("");
        grid12.setText("");
        grid20.setText("");
        grid21.setText("");
        grid22.setText("");
        s00.clearAnimation();
        s01.clearAnimation();
        s02.clearAnimation();
        s10.clearAnimation();
        s11.clearAnimation();
        s12.clearAnimation();
        s20.clearAnimation();
        s21.clearAnimation();
        s22.clearAnimation();
        s00.setVisibility(View.INVISIBLE);
        s01.setVisibility(View.INVISIBLE);
        s02.setVisibility(View.INVISIBLE);
        s10.setVisibility(View.INVISIBLE);
        s11.setVisibility(View.INVISIBLE);
        s12.setVisibility(View.INVISIBLE);
        s20.setVisibility(View.INVISIBLE);
        s21.setVisibility(View.INVISIBLE);
        s22.setVisibility(View.INVISIBLE);
        c00.clearAnimation();
        c01.clearAnimation();
        c02.clearAnimation();
        c10.clearAnimation();
        c11.clearAnimation();
        c12.clearAnimation();
        c20.clearAnimation();
        c21.clearAnimation();
        c22.clearAnimation();
        c00.setVisibility(View.INVISIBLE);
        c01.setVisibility(View.INVISIBLE);
        c02.setVisibility(View.INVISIBLE);
        c10.setVisibility(View.INVISIBLE);
        c11.setVisibility(View.INVISIBLE);
        c12.setVisibility(View.INVISIBLE);
        c20.setVisibility(View.INVISIBLE);
        c21.setVisibility(View.INVISIBLE);
        c22.setVisibility(View.INVISIBLE);
        quit.setEnabled(false);
        quit.setVisibility(View.INVISIBLE);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }

        });

        g00 = false;
        g01 = false;
        g02 = false;
        g10 = false;
        g11 = false;
        g12 = false;
        g20 = false;
        g21 = false;
        g22 = false;
        if(turn.equalsIgnoreCase("Player 2")){
            player1Turn = false;
        }else if ((turn.equalsIgnoreCase("Random Every Time"))) {
            Random rand = new Random();
            int n = rand.nextInt(2);
            if (n == 1) {
                player1Turn = false;
                Toast.makeText(gameActivity.this, "Player 2's Turn", Toast.LENGTH_SHORT).show();

            } else {
                player1Turn = true;
                Toast.makeText(gameActivity.this, "Player 1's Turn", Toast.LENGTH_SHORT).show();
            }
        }else if ((turn.equalsIgnoreCase("Random Once"))) {
            player1Turn = randomOnce;
            if(randomOnce != true){
                Toast.makeText(gameActivity.this, "Player 1's Turn", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(gameActivity.this, "Player 2's Turn", Toast.LENGTH_SHORT).show();
            }

        }else{
            player1Turn = true;
            Toast.makeText(gameActivity.this, "Player 1's Turn", Toast.LENGTH_SHORT).show();
        }

        nextGame.setEnabled(false);
        nextGame.setVisibility(View.INVISIBLE);


    }

    public void setSquareColor(String type){
        SquareView square00 = findViewById(R.id.square00);
        SquareView square01 = findViewById(R.id.square01);
        SquareView square02 = findViewById(R.id.square02);
        SquareView square10 = findViewById(R.id.square10);
        SquareView square11 = findViewById(R.id.square11);
        SquareView square12 = findViewById(R.id.square12);
        SquareView square20 = findViewById(R.id.square20);
        SquareView square21 = findViewById(R.id.square21);
        SquareView square22 = findViewById(R.id.square22);
            square00.setPaint(type);
            square01.setPaint(type);
            square02.setPaint(type);
            square10.setPaint(type);
            square11.setPaint(type);
            square12.setPaint(type);
            square20.setPaint(type);
            square21.setPaint(type);
            square22.setPaint(type);

    }

    public void setCircleColor(String type){
        CircleView square00 = findViewById(R.id.circle00);
        CircleView square01 = findViewById(R.id.circle01);
        CircleView square02 = findViewById(R.id.circle02);
        CircleView square10 = findViewById(R.id.circle10);
        CircleView square11 = findViewById(R.id.circle11);
        CircleView square12 = findViewById(R.id.circle12);
        CircleView square20 = findViewById(R.id.circle20);
        CircleView square21 = findViewById(R.id.circle21);
        CircleView square22 = findViewById(R.id.circle22);
        square00.setPaint(type);
        square01.setPaint(type);
        square02.setPaint(type);
        square10.setPaint(type);
        square11.setPaint(type);
        square12.setPaint(type);
        square20.setPaint(type);
        square21.setPaint(type);
        square22.setPaint(type);

    }
    public void scoreScreen(){
        if(player != null) {
            player.release();
        }
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra("Player 1 score", score1);
        intent.putExtra("Player 2 score", score2);
        intent.putExtra("Total games", numberGames);


        startActivity(intent);

    }

    public void tieCheck() {
        Button quit = findViewById(R.id.quit);
       if (!check()) {
            if (g00 && g01 && g02 && g10 && g11 && g12 && g20 && g21 && g22) {
                numberGames += 1;
                String total = "Total Games : " + numberGames;
                TextView totalGamesText = findViewById(R.id.totalGames);
                Button nextGame = findViewById(R.id.nextGame);
                totalGamesText.setText(total);
                Toast.makeText(gameActivity.this, "Tie", Toast.LENGTH_SHORT).show();
                soundPool.play(bellSound,1,1,0,0,1);
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });

            }
        }

    }
    public boolean check() {
        Button quit = findViewById(R.id.quit);
        TextView player1Text = findViewById(R.id.player1Score);
        TextView player2Text = findViewById(R.id.player2Score);
        TextView totalGamesText = findViewById(R.id.totalGames);
        String answer = "";
        String games = "";

        Button nextGame = findViewById(R.id.nextGame);
        //Tie checking

        //Vertical checks
        if (((boardStatus[0][0]).equals((boardStatus[0][1]))) && ((boardStatus[0][0]).equals((boardStatus[0][2])))) {
            if (boardStatus[0][0].equalsIgnoreCase("X")) {
                score1 += 1;
                numberGames += 1;
                answer = "Player 1: " + Integer.toString(score1);
                player1Text.setText(answer);
                games = "Total games: " + Integer.toString(numberGames);
                totalGamesText.setText(games);
                g00 = true;
                g01 = true;
                g02 = true;
                g10 = true;
                g11 = true;
                g12 = true;
                g20 = true;
                g21 = true;
                g22 = true;
                soundPool.play(bellSound,1,1,0,0,1);

                Toast.makeText(gameActivity.this, "Player 1 won", Toast.LENGTH_SHORT).show();
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                return true;

            } else if (boardStatus[0][0].equalsIgnoreCase("O")) {
                score2 += 1;
                numberGames += 1;
                answer = "Player 2: " + Integer.toString(score2);
                player2Text.setText(answer);
                games = "Total games: " + Integer.toString(numberGames);
                totalGamesText.setText(games);
                g00 = true;
                g01 = true;
                g02 = true;
                g10 = true;
                g11 = true;
                g12 = true;
                g20 = true;
                g21 = true;
                g22 = true;
                soundPool.play(bellSound,1,1,0,0,1);
                Toast.makeText(gameActivity.this, "Player 2 won", Toast.LENGTH_SHORT).show();
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                return true;
            }
        } else if (((boardStatus[1][0]).equals((boardStatus[1][1]))) && ((boardStatus[1][0]).equals((boardStatus[1][2])))) {
            if (boardStatus[1][0].equalsIgnoreCase("X")) {
                score1 += 1;
                numberGames += 1;
                answer = "Player 1: " + Integer.toString(score1);
                player1Text.setText(answer);
                games = "Total games: " + Integer.toString(numberGames);
                totalGamesText.setText(games);
                g00 = true;
                g01 = true;
                g02 = true;
                g10 = true;
                g11 = true;
                g12 = true;
                g20 = true;
                g21 = true;
                g22 = true;
                soundPool.play(bellSound,1,1,0,0,1);
                Toast.makeText(gameActivity.this, "Player 1 won", Toast.LENGTH_SHORT).show();
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                return true;
            } else if (boardStatus[1][0].equalsIgnoreCase("O")) {
                score2 += 1;
                numberGames += 1;
                answer = "Player 2: " + Integer.toString(score2);
                player2Text.setText(answer);
                games = "Total games: " + Integer.toString(numberGames);
                totalGamesText.setText(games);
                g00 = true;
                g01 = true;
                g02 = true;
                g10 = true;
                g11 = true;
                g12 = true;
                g20 = true;
                g21 = true;
                soundPool.play(bellSound,1,1,0,0,1);
                g22 = true;
                Toast.makeText(gameActivity.this, "Player 2 won", Toast.LENGTH_SHORT).show();
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                return true;
            }
        } else if (((boardStatus[2][0]).equals((boardStatus[2][1]))) && ((boardStatus[2][0]).equals((boardStatus[2][2])))) {
            if (boardStatus[2][0].equalsIgnoreCase("X")) {
                score1 += 1;
                numberGames += 1;
                answer = "Player 1: " + Integer.toString(score1);
                player1Text.setText(answer);
                games = "Total games: " + Integer.toString(numberGames);
                totalGamesText.setText(games);
                g00 = true;
                g01 = true;
                g02 = true;
                g10 = true;
                g11 = true;
                g12 = true;
                g20 = true;
                soundPool.play(bellSound,1,1,0,0,1);
                g21 = true;
                g22 = true;
                Toast.makeText(gameActivity.this, "Player 1 won", Toast.LENGTH_SHORT).show();
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                return true;
            } else if (boardStatus[2][0].equalsIgnoreCase("O")) {
                score2 += 1;
                numberGames += 1;
                answer = "Player 2: " + Integer.toString(score2);
                player2Text.setText(answer);
                games = "Total games: " + Integer.toString(numberGames);
                totalGamesText.setText(games);
                g00 = true;
                g01 = true;
                g02 = true;
                g10 = true;
                g11 = true;
                g12 = true;
                soundPool.play(bellSound,1,1,0,0,1);
                g20 = true;
                g21 = true;
                g22 = true;
                Toast.makeText(gameActivity.this, "Player 2 won", Toast.LENGTH_SHORT).show();
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                return true;
            }
        }
        //Horizontal checks
        if (((boardStatus[0][0]).equals((boardStatus[1][0]))) && ((boardStatus[0][0]).equals((boardStatus[2][0])))) {
            if (boardStatus[0][0].equalsIgnoreCase("X")) {
                score1 += 1;
                numberGames += 1;
                answer = "Player 1: " + Integer.toString(score1);
                player1Text.setText(answer);
                games = "Total games: " + Integer.toString(numberGames);
                totalGamesText.setText(games);
                g00 = true;
                g01 = true;
                g02 = true;
                g10 = true;
                g11 = true;
                soundPool.play(bellSound,1,1,0,0,1);
                g12 = true;
                g20 = true;
                g21 = true;
                g22 = true;
                Toast.makeText(gameActivity.this, "Player 1 won", Toast.LENGTH_SHORT).show();
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                return true;
            } else if (boardStatus[0][0].equalsIgnoreCase("O")) {
                score2 += 1;
                numberGames += 1;
                answer = "Player 2: " + Integer.toString(score2);
                player2Text.setText(answer);
                games = "Total games: " + Integer.toString(numberGames);
                totalGamesText.setText(games);
                g00 = true;
                g01 = true;
                g02 = true;
                g10 = true;
                g11 = true;
                g12 = true;
                soundPool.play(bellSound,1,1,0,0,1);
                g20 = true;
                g21 = true;
                g22 = true;
                Toast.makeText(gameActivity.this, "Player 2 won", Toast.LENGTH_SHORT).show();
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                return true;
            }
        } else if (((boardStatus[0][1]).equals((boardStatus[1][1]))) && ((boardStatus[0][1]).equals((boardStatus[2][1])))) {
            if (boardStatus[0][1].equalsIgnoreCase("X")) {
                score1 += 1;
                numberGames += 1;
                answer = "Player 1: " + Integer.toString(score1);
                player1Text.setText(answer);
                games = "Total games: " + Integer.toString(numberGames);
                totalGamesText.setText(games);
                g00 = true;
                g01 = true;
                g02 = true;
                g10 = true;
                soundPool.play(bellSound,1,1,0,0,1);
                g11 = true;
                g12 = true;
                g20 = true;
                g21 = true;
                g22 = true;
                Toast.makeText(gameActivity.this, "Player 1 won", Toast.LENGTH_SHORT).show();
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                return true;
            } else if (boardStatus[0][1].equalsIgnoreCase("O")) {
                score2 += 1;
                numberGames += 1;
                answer = "Player 2: " + Integer.toString(score2);
                player2Text.setText(answer);
                games = "Total games: " + Integer.toString(numberGames);
                totalGamesText.setText(games);
                g00 = true;
                g01 = true;
                g02 = true;
                soundPool.play(bellSound,1,1,0,0,1);
                g10 = true;
                g11 = true;
                g12 = true;
                g20 = true;
                g21 = true;
                g22 = true;
                Toast.makeText(gameActivity.this, "Player 2 won", Toast.LENGTH_SHORT).show();
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                return true;
            }
        } else if (((boardStatus[0][2]).equals((boardStatus[1][2]))) && ((boardStatus[0][2]).equals((boardStatus[2][2])))) {
            if (boardStatus[0][2].equalsIgnoreCase("X")) {
                score1 += 1;
                numberGames += 1;
                answer = "Player 1: " + Integer.toString(score1);
                player1Text.setText(answer);
                games = "Total games: " + Integer.toString(numberGames);
                totalGamesText.setText(games);
                g00 = true;
                g01 = true;
                g02 = true;
                soundPool.play(bellSound,1,1,0,0,1);
                g10 = true;
                g11 = true;
                g12 = true;
                g20 = true;
                g21 = true;
                g22 = true;
                Toast.makeText(gameActivity.this, "Player 1 won", Toast.LENGTH_SHORT).show();
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                return true;
            } else if (boardStatus[0][2].equalsIgnoreCase("O")) {
                score2 += 1;
                numberGames += 1;
                answer = "Player 2: " + Integer.toString(score2);
                player2Text.setText(answer);
                games = "Total games: " + Integer.toString(numberGames);
                totalGamesText.setText(games);
                g00 = true;
                g01 = true;
                g02 = true;
                g10 = true;
                g11 = true;
                soundPool.play(bellSound,1,1,0,0,1);
                g12 = true;
                g20 = true;
                g21 = true;
                g22 = true;
                Toast.makeText(gameActivity.this, "Player 2 won", Toast.LENGTH_SHORT).show();
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                return true;
            }
        } else if (((boardStatus[0][0]).equals((boardStatus[1][1]))) && ((boardStatus[0][0]).equals((boardStatus[2][2])))) {
            if (boardStatus[0][0].equalsIgnoreCase("X")) {
                score1 += 1;
                numberGames += 1;
                answer = "Player 1: " + Integer.toString(score1);
                player1Text.setText(answer);
                games = "Total games: " + Integer.toString(numberGames);
                totalGamesText.setText(games);
                g00 = true;
                g01 = true;
                g02 = true;
                g10 = true;
                g11 = true;
                g12 = true;
                g20 = true;
                soundPool.play(bellSound,1,1,0,0,1);
                g21 = true;
                g22 = true;
                Toast.makeText(gameActivity.this, "Player 1 won", Toast.LENGTH_SHORT).show();
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                return true;
            } else if (boardStatus[0][0].equalsIgnoreCase("O")) {
                score2 += 1;
                numberGames += 1;
                answer = "Player 2: " + Integer.toString(score2);
                player2Text.setText(answer);
                games = "Total games: " + Integer.toString(numberGames);
                totalGamesText.setText(games);
                g00 = true;
                g01 = true;
                g02 = true;
                g10 = true;
                soundPool.play(bellSound,1,1,0,0,1);
                g11 = true;
                g12 = true;
                g20 = true;
                g21 = true;
                g22 = true;
                Toast.makeText(gameActivity.this, "Player 2 won", Toast.LENGTH_SHORT).show();
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                return true;
            }
        } else if (((boardStatus[0][2]).equals((boardStatus[1][1]))) && ((boardStatus[0][2]).equals((boardStatus[2][0])))) {
            if (boardStatus[0][2].equalsIgnoreCase("X")) {
                score1 += 1;
                numberGames += 1;
                answer = "Player 1: " + Integer.toString(score1);
                player1Text.setText(answer);
                games = "Total games: " + Integer.toString(numberGames);
                totalGamesText.setText(games);
                g00 = true;
                g01 = true;
                g02 = true;
                g10 = true;
                soundPool.play(bellSound,1,1,0,0,1);
                g11 = true;
                g12 = true;
                g20 = true;
                g21 = true;
                g22 = true;
                Toast.makeText(gameActivity.this, "Player 1 won", Toast.LENGTH_SHORT).show();
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                return true;
            } else if (boardStatus[0][2].equalsIgnoreCase("O")) {
                score2 += 1;
                numberGames += 1;
                answer = "Player 2: " + Integer.toString(score2);
                player2Text.setText(answer);
                games = "Total games: " + Integer.toString(numberGames);
                totalGamesText.setText(games);
                g00 = true;
                g01 = true;
                g02 = true;
                soundPool.play(bellSound,1,1,0,0,1);
                g10 = true;
                g11 = true;
                g12 = true;
                g20 = true;
                g21 = true;
                g22 = true;
                Toast.makeText(gameActivity.this, "Player 2 won", Toast.LENGTH_SHORT).show();
                nextGame.setEnabled(true);
                nextGame.setVisibility(View.VISIBLE);
                nextGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initBoard();
                    }

                });
                quit.setEnabled(true);
                quit.setVisibility(View.VISIBLE);
                quit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scoreScreen();
                    }

                });
                return true;
            }

        }
        return false;
    }
    public void playVideo(VideoView videv, String videopath){

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



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_game_background);
        View myView = this.findViewById(R.id.currentLayout);
        CustomView surface = findViewById(R.id.gridView);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        player1Piece = (String)b.get("Player1Piece");
        player2Piece = (String) b.get("Player2Piece");
        background = (String) b.get("background");
        font = (String) b.get("font");
        font2 = (String) b.get("font2");
        turn = (String) b.get("turn");
        String music = (String)b.get("music");
        String gridColor = (String) b.get("gridColor");
        surface.setPaint(gridColor);
        color = (String) b.get("color");
        TextView text = findViewById(R.id.player1Score);
        color2 = (String) b.get("color2");
        initBoard();
        if(player1Piece.equalsIgnoreCase("Square")) {
            setSquareColor(color);
        }else if(player2Piece.equalsIgnoreCase("Square")) {
            setSquareColor(color2);
        }else{
            setSquareColor("Green");
        }

        if(player1Piece.equalsIgnoreCase("Circle")) {
            setCircleColor(color);
        }else if(player2Piece.equalsIgnoreCase("Circle")) {
            setCircleColor(color2);
        }else{
            setCircleColor("Green");
        }
        if(turn.equals("Player 2")){
            player1Turn = false;
            Toast.makeText(gameActivity.this, "Player 2's Turn", Toast.LENGTH_SHORT).show();
        }else if((turn.equals("Random Once"))||(turn.equals("Random Every Time"))){
            Random rand = new Random();
            int n = rand.nextInt(2);
            if(n == 1){
                player1Turn = true;
                randomOnce = true;
                Toast.makeText(gameActivity.this, "Player 1's Turn", Toast.LENGTH_SHORT).show();
            }else
            {
                player1Turn = false;
                randomOnce = false;
                Toast.makeText(gameActivity.this, "Player 2's Turn", Toast.LENGTH_SHORT).show();
            }

        }





        if(music.equalsIgnoreCase("Aria Da Capo")){
           player = MediaPlayer.create(this, R.raw.aria);
            player.setLooping(true);
            player.start();
        }else if(music.equalsIgnoreCase("Spider-Man Theme")){
            player = MediaPlayer.create(this, R.raw.spidermantheme);
            player.setLooping(true);
            player.start();
        }else if(music.equalsIgnoreCase("Iron Man Theme")){
            player = MediaPlayer.create(this, R.raw.blacksabeth);
            player.setLooping(true);
            player.start();
        }else if(music.equalsIgnoreCase("Jackie Moon")){
            player = MediaPlayer.create(this, R.raw.semipro);
            player.setLooping(true);
            player.start();
        }else if(music.equalsIgnoreCase("Jelly Fish Jam")){
            player = MediaPlayer.create(this, R.raw.jellyfishjam);
            player.setLooping(true);
            player.start();
        }else if(music.equalsIgnoreCase("Mario Theme")){
            player = MediaPlayer.create(this, R.raw.mariotheme);
            player.setLooping(true);
            player.start();
        }else if(music.equalsIgnoreCase("Careless Whisper")){
            player = MediaPlayer.create(this, R.raw.careless);
            player.setLooping(true);
            player.start();
        }else if(music.equalsIgnoreCase("Country Roads")){
            player = MediaPlayer.create(this, R.raw.countryroads);
            player.setLooping(true);
            player.start();
        }

        soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC,0);
        stretchSound = soundPool.load(this, R.raw.stretch, 1);
        bellSound = soundPool.load(this, R.raw.boxing, 1);
        if(background.equalsIgnoreCase("Lake")){
            videv = findViewById(R.id.gameBackgroundVideo);
            videv.setVisibility(View.INVISIBLE);
            myView.setBackgroundResource(R.drawable.lake);

        }else  if(background.equalsIgnoreCase("City At Night")) {
            videv = findViewById(R.id.gameBackgroundVideo);
            videv.setVisibility(View.INVISIBLE);
            myView.setBackgroundResource(R.drawable.cityatnight);
        }else  if(background.equalsIgnoreCase("Iron Man")) {
            videv = findViewById(R.id.gameBackgroundVideo);
            videv.setVisibility(View.INVISIBLE);
            myView.setBackgroundResource(R.drawable.ironman);
        }else  if(background.equalsIgnoreCase("Spiderman")) {
            videv = findViewById(R.id.gameBackgroundVideo);
            videv.setVisibility(View.INVISIBLE);
            myView.setBackgroundResource(R.drawable.spiderman);
        }else  if(background.equalsIgnoreCase("SpongeBob")) {
            videv = findViewById(R.id.gameBackgroundVideo);
            videv.setVisibility(View.INVISIBLE);
            myView.setBackgroundResource(R.drawable.spongebob);
        }else  if(background.equalsIgnoreCase("Star Wars")) {
            videv = findViewById(R.id.gameBackgroundVideo);
            videv.setVisibility(View.INVISIBLE);
            myView.setBackgroundResource(R.drawable.starwars);
        }else  if(background.equalsIgnoreCase("Mass Effect")) {
            videv = findViewById(R.id.gameBackgroundVideo);
            videv.setVisibility(View.INVISIBLE);
            myView.setBackgroundResource(R.drawable.masseffect3);
        }else  if(background.equalsIgnoreCase("Mario")) {
            videv = findViewById(R.id.gameBackgroundVideo);
            videv.setVisibility(View.INVISIBLE);
            myView.setBackgroundResource(R.drawable.mario);
        }else  if(background.equalsIgnoreCase("Nature Video")) {
            videv = findViewById(R.id.gameBackgroundVideo);
            String videopath = "android.resource://" + getPackageName() + "/" + R.raw.backgroundgame;
            playVideo(videv, videopath);

        }else  if(background.equalsIgnoreCase("Order 66 Video")) {
            videv = findViewById(R.id.gameBackgroundVideo);
            videv.setVisibility(View.INVISIBLE);
            vide16by9 = findViewById(R.id.aspect16by9);
            vide16by9.setVisibility(View.VISIBLE);
            String videopath = "android.resource://" + getPackageName() + "/" + R.raw.order66;
            playVideo(vide16by9, videopath);
            myView.setBackgroundColor(Color.BLACK);
            myView.setBackgroundResource(R.drawable.ros);

        }else  if(background.equalsIgnoreCase("Iron Man Gulmira")) {
            videv = findViewById(R.id.gameBackgroundVideo);
            videv.setVisibility(View.INVISIBLE);
            vide16by9 = findViewById(R.id.aspect16by9);
            vide16by9.setVisibility(View.VISIBLE);
            String videopath = "android.resource://" + getPackageName() + "/" + R.raw.iromangulmere;
            playVideo(vide16by9, videopath);
            myView.setBackgroundColor(Color.BLACK);
        }else  if(background.equalsIgnoreCase("Sharks Video")) {
            videv = findViewById(R.id.gameBackgroundVideo);
            String videopath = "android.resource://" + getPackageName() + "/" + R.raw.shark;
            playVideo(videv, videopath);
        }






        surface.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                CustomView surface = findViewById(R.id.gridView);
                soundPool.play(stretchSound,1,1,0,0,1);
                TextView grid00 = findViewById(R.id.grid00);
                TextView grid01 = findViewById(R.id.grid01);
                TextView grid02 = findViewById(R.id.grid02);
                TextView grid10 = findViewById(R.id.grid10);
                TextView grid11 = findViewById(R.id.grid11);
                TextView grid12 = findViewById(R.id.grid12);
                TextView grid20 = findViewById(R.id.grid20);
                TextView grid21 = findViewById(R.id.grid21);
                TextView grid22 = findViewById(R.id.grid22);
                SquareView square00 = findViewById(R.id.square00);
                SquareView square01 = findViewById(R.id.square01);
                SquareView square02 = findViewById(R.id.square02);
                SquareView square10 = findViewById(R.id.square10);
                SquareView square11 = findViewById(R.id.square11);
                SquareView square12 = findViewById(R.id.square12);
                SquareView square20 = findViewById(R.id.square20);
                SquareView square21 = findViewById(R.id.square21);
                SquareView square22 = findViewById(R.id.square22);
                CircleView circle00 = findViewById(R.id.circle00);
                CircleView circle01 = findViewById(R.id.circle01);
                CircleView circle02 = findViewById(R.id.circle02);
                CircleView circle10 = findViewById(R.id.circle10);
                CircleView circle11 = findViewById(R.id.circle11);
                CircleView circle12 = findViewById(R.id.circle12);
                CircleView circle20 = findViewById(R.id.circle20);
                CircleView circle21 = findViewById(R.id.circle21);
                CircleView circle22 = findViewById(R.id.circle22);




                double x = event.getX();
                //String answer = Double.toString(x);
                double y = event.getY();
                //String answer2 = Double.toString(y);
                //Toast.makeText(gameActivity.this, "x ="+answer+" y= "+answer2, Toast.LENGTH_LONG).show();
                if((x < surface.getWidth()/3)&&(y < surface.getHeight()/3)){
                    if(!g00){
                        g00 = true;
                        if(player1Turn){
                            if(player1Piece.equalsIgnoreCase("Square")){
                                square00.setVisibility(View.VISIBLE);
                                startAnimation(square00);
                            }else if(player1Piece.equalsIgnoreCase("Circle")){
                                circle00.setVisibility(View.VISIBLE);
                                startAnimation(circle00);
                            }else{
                                grid00.setText(player1Piece);
                                startAnimation(grid00, font, color);
                            }
                            boardStatus[0][0] = "X";
                            player1Turn = false;
                        }else{
                            if(player2Piece.equalsIgnoreCase("Square")){
                                square00.setVisibility(View.VISIBLE);
                                startAnimation(square00);
                            }else if(player2Piece.equalsIgnoreCase("Circle")) {
                                circle00.setVisibility(View.VISIBLE);
                                startAnimation(circle00);
                            }else {
                                grid00.setText(player2Piece);
                                startAnimation(grid00, font2, color2);
                            }
                            boardStatus[0][0] = "O";
                            player1Turn = true;
                        }
                        if(!check()) {
                            tieCheck();
                        }

                        }
                    }

                if((x < (surface.getWidth()*2)/3)&&(y < surface.getHeight()/3)&& (x > (surface.getWidth()/3)) ){
                    if(!g01){
                        g01 = true;
                        if(player1Turn){
                            if(player1Piece.equalsIgnoreCase("Square")){
                                square01.setVisibility(View.VISIBLE);
                                startAnimation(square01);
                            }else if(player1Piece.equalsIgnoreCase("Circle")){
                                circle01.setVisibility(View.VISIBLE);
                                startAnimation(circle01);
                            }else {
                                grid01.setText(player1Piece);
                                startAnimation(grid01, font, color);
                            }
                            boardStatus[1][0] = "X";
                            player1Turn = false;
                        }else{
                            if(player2Piece.equalsIgnoreCase("Square")){
                                square01.setVisibility(View.VISIBLE);
                                startAnimation(square01);
                            }else if(player2Piece.equalsIgnoreCase("Circle")){
                                circle01.setVisibility(View.VISIBLE);
                                startAnimation(circle01);
                            }else {
                                grid01.setText(player2Piece);
                                startAnimation(grid01, font2, color2);
                            }
                            boardStatus[1][0] = "O";
                            player1Turn = true;
                        }
                        if(!check()) {
                            tieCheck();
                        }
                    }
                }
                if((x > (surface.getWidth()*2)/3)&&(y < surface.getHeight()/3)&& (x < surface.getWidth())){
                    if(!g02){
                        g02 = true;
                        if(player1Turn){
                            if(player1Piece.equalsIgnoreCase("Square")){
                                square02.setVisibility(View.VISIBLE);
                                startAnimation(square02);
                            }else if(player1Piece.equalsIgnoreCase("Circle")){
                                circle02.setVisibility(View.VISIBLE);
                                startAnimation(circle02);
                            }else {
                                grid02.setText(player1Piece);
                                startAnimation(grid02, font, color);
                            }
                            boardStatus[2][0] = "X";
                            player1Turn = false;
                        }else{
                            if(player2Piece.equalsIgnoreCase("Square")){
                                square02.setVisibility(View.VISIBLE);
                                startAnimation(square02);
                            }else if(player2Piece.equalsIgnoreCase("Circle")){
                                circle02.setVisibility(View.VISIBLE);
                                startAnimation(circle02);
                            }else{
                                grid02.setText(player2Piece);
                                startAnimation(grid02, font2, color2);
                            }
                            boardStatus[2][0] = "O";
                            player1Turn = true;
                        }
                        if(!check()) {
                            tieCheck();
                        }
                    }
                }

                //Second row
                if((x < surface.getWidth()/3)&&(y > surface.getHeight()/3)&&(y < (surface.getHeight()*2)/3)){
                    if(!g10){
                        g10 = true;
                        if(player1Turn){
                            if(player1Piece.equalsIgnoreCase("Square")){
                                square10.setVisibility(View.VISIBLE);
                                startAnimation(square10);
                            }else if(player1Piece.equalsIgnoreCase("Circle")){
                                circle10.setVisibility(View.VISIBLE);
                                startAnimation(circle10);
                            }else {
                                grid10.setText(player1Piece);
                                startAnimation(grid10,font,color);
                            }
                            boardStatus[0][1] = "X";
                            player1Turn = false;
                        }else{
                            if(player2Piece.equalsIgnoreCase("Square")){
                                square10.setVisibility(View.VISIBLE);
                                startAnimation(square10);
                            }else if(player2Piece.equalsIgnoreCase("Circle")){
                                circle10.setVisibility(View.VISIBLE);
                                startAnimation(circle10);
                            }else {
                                grid10.setText(player2Piece);
                                startAnimation(grid10, font2, color2);
                            }
                            boardStatus[0][1] = "O";
                            player1Turn = true;
                        }

                        if(!check()) {
                            tieCheck();
                        }
                    }
                }
                if((x < (surface.getWidth()*2)/3)&&(y > surface.getHeight()/3)&&(y < (surface.getHeight()*2)/3)&& (x > (surface.getWidth()/3)) ){
                    if(!g11){
                        g11 = true;
                        if(player1Turn){
                            if(player1Piece.equalsIgnoreCase("Square")){
                                square11.setVisibility(View.VISIBLE);
                                startAnimation(square11);
                            }else  if(player1Piece.equalsIgnoreCase("Circle")){
                                circle11.setVisibility(View.VISIBLE);
                                startAnimation(circle11);
                            }else {
                                grid11.setText(player1Piece);
                                startAnimation(grid11, font, color);
                            }
                            boardStatus[1][1] = "X";
                            player1Turn = false;
                        }else{
                            if(player2Piece.equalsIgnoreCase("Square")){
                                square11.setVisibility(View.VISIBLE);
                                startAnimation(square11);
                            }else  if(player2Piece.equalsIgnoreCase("Circle")){
                                circle11.setVisibility(View.VISIBLE);
                                startAnimation(circle11);
                            }else {
                                grid11.setText(player2Piece);
                                startAnimation(grid11, font2, color2);
                            }
                            boardStatus[1][1] = "O";
                            player1Turn = true;
                        }
                        if(!check()) {
                            tieCheck();
                        }

                    }
                }
                if((x > (surface.getWidth()*2)/3)&&(y > surface.getHeight()/3)&&(y < (surface.getHeight()*2)/3)&& (x < surface.getWidth())){
                    if(!g12){
                        g12 = true;
                        if(player1Turn){
                            if(player1Piece.equalsIgnoreCase("Square")){
                                square12.setVisibility(View.VISIBLE);
                                startAnimation(square12);
                            }else  if(player1Piece.equalsIgnoreCase("Circle")){
                                circle12.setVisibility(View.VISIBLE);
                                startAnimation(circle12);
                            }else {
                                grid12.setText(player1Piece);
                                startAnimation(grid12,  font, color );
                            }
                            boardStatus[2][1] = "X";
                            player1Turn = false;
                        }else{
                            if(player2Piece.equalsIgnoreCase("Square")){
                                square12.setVisibility(View.VISIBLE);
                                startAnimation(square12);
                            }else if(player2Piece.equalsIgnoreCase("Circle")){
                                circle12.setVisibility(View.VISIBLE);
                                startAnimation(circle12);
                            }else {
                                grid12.setText(player2Piece);
                                startAnimation(grid12, font2, color2);
                            }
                            boardStatus[2][1] = "O";
                            player1Turn = true;
                        }
                        if(!check()) {
                            tieCheck();
                        }
                    }
                }


                // Third Row
                if((x < surface.getWidth()/3)&&(y < surface.getHeight())&&(y > (surface.getHeight()*2)/3)){
                    if(!g20){
                        g20 = true;
                        if(player1Turn){
                            if(player1Piece.equalsIgnoreCase("Square")) {
                                square20.setVisibility(View.VISIBLE);
                                startAnimation(square20);
                            }else if(player1Piece.equalsIgnoreCase("Circle")) {
                                circle20.setVisibility(View.VISIBLE);
                                startAnimation(circle20);
                            }else {
                                grid20.setText(player1Piece);
                                startAnimation(grid20, font, color);
                            }
                            boardStatus[0][2] = "X";
                            player1Turn = false;
                        }else{
                            if(player2Piece.equalsIgnoreCase("Square")){
                                square20.setVisibility(View.VISIBLE);
                                startAnimation(square20);
                            }else if(player2Piece.equalsIgnoreCase("Circle")){
                                circle20.setVisibility(View.VISIBLE);
                                startAnimation(circle20);
                            }else {
                                grid20.setText(player2Piece);
                                startAnimation(grid20, font2, color2);
                            }
                            boardStatus[0][2] = "O";
                            player1Turn = true;
                        }
                        if(!check()) {
                            tieCheck();
                        }
                    }
                }
                if((x < (surface.getWidth()*2)/3)&&(y < surface.getHeight())&&(y > (surface.getHeight()*2)/3)&& (x > (surface.getWidth()/3)) ){
                    if(!g21){
                        g21 = true;
                        if(player1Turn){
                            if(player1Piece.equalsIgnoreCase("Square")){
                                square21.setVisibility(View.VISIBLE);
                                startAnimation(square21);
                            }else if(player1Piece.equalsIgnoreCase("Circle")){
                                circle21.setVisibility(View.VISIBLE);
                                startAnimation(circle21);
                            }else {
                                grid21.setText(player1Piece);
                                startAnimation(grid21, font, color);
                            }
                            boardStatus[1][2] = "X";
                            player1Turn = false;
                        }else{
                            if(player2Piece.equalsIgnoreCase("Square")){
                                square21.setVisibility(View.VISIBLE);
                                startAnimation(square21);
                            }else  if(player2Piece.equalsIgnoreCase("Circle")){
                                circle21.setVisibility(View.VISIBLE);
                                startAnimation(circle21);
                            }else {
                                grid21.setText(player2Piece);
                                startAnimation(grid21, font2, color2);
                            }
                            boardStatus[1][2] = "O";
                            player1Turn = true;
                        }
                        if(!check()) {
                            tieCheck();
                        }
                    }
                }
                if((x > (surface.getWidth()*2)/3)&&(y < surface.getHeight())&&(y > (surface.getHeight()*2)/3)&& (x < surface.getWidth())){
                    if(!g22){
                        g22 = true;
                        if(player1Turn){
                            if(player1Piece.equalsIgnoreCase("Square")){
                                square22.setVisibility(View.VISIBLE);
                                startAnimation(square22);
                            }else if(player1Piece.equalsIgnoreCase("Circle")){
                                circle22.setVisibility(View.VISIBLE);
                                startAnimation(circle22);
                            }else {
                                grid22.setText(player1Piece);
                                startAnimation(grid22, font, color);
                            }
                            boardStatus[2][2] = "X";
                            player1Turn = false;
                        }else{
                            if(player2Piece.equalsIgnoreCase("Square")){
                                square22.setVisibility(View.VISIBLE);
                                startAnimation(square22);
                            }else if(player2Piece.equalsIgnoreCase("Circle")){
                                circle22.setVisibility(View.VISIBLE);
                                startAnimation(circle22);
                            }else {
                                grid22.setText(player2Piece);
                                startAnimation(grid22, font2, color2);
                            }
                            boardStatus[2][2] = "O";
                            player1Turn = true;
                        }
                        if(!check()) {
                            tieCheck();
                        }
                    }
                }
                return true;
            }
        });




    }


    TransitionDrawable transitionDrawable;
    public void startAnimation(TextView textView, String font , String color){
        if(font.equalsIgnoreCase("serif")){
            textView.setTypeface(Typeface.SERIF);
        }else if(font.equalsIgnoreCase("Monospace")){
            textView.setTypeface(Typeface.MONOSPACE);
        }else if(font.equalsIgnoreCase("Default Bold")){
            textView.setTypeface(Typeface.DEFAULT_BOLD);
        }else if(font.equals("Sans Serif")){
            textView.setTypeface(Typeface.SANS_SERIF);
        }
        if(color.equalsIgnoreCase("red")){
            textView.setTextColor(Color.RED);
        }else if(color.equalsIgnoreCase("blue")){
            textView.setTextColor(Color.BLUE);
        }else if(color.equalsIgnoreCase("green")){
            textView.setTextColor(Color.GREEN);
        }else if(color.equals("Magenta")){
            textView.setTextColor(Color.MAGENTA);
        }else if(color.equals("Cyan")){
            textView.setTextColor(Color.CYAN);
        }else if(color.equals("Yellow")){
            textView.setTextColor(Color.YELLOW);
        }else if(color.equals("Black")){
            textView.setTextColor(Color.BLACK);
        }else if(color.equals("Gray")){
            textView.setTextColor(Color.GRAY);
        }else if(color.equalsIgnoreCase("Random Once")){
            Random rand = new Random();
            int n = rand.nextInt(7)+1;
            if(n == 1){
                textView.setTextColor(Color.RED);
            }else if(n==2){
                textView.setTextColor(Color.BLUE);
            }else if(n==3){
                textView.setTextColor(Color.GREEN);
            }else if(n==4){
                textView.setTextColor(Color.MAGENTA);
            }else if(n==5){
                textView.setTextColor(Color.CYAN);
            }else if(n==6){
                textView.setTextColor(Color.BLACK);
            }else if(n==7){
                textView.setTextColor(Color.GRAY);
            }

        }else if(color.equalsIgnoreCase("Random Every Time")) {
            Random rand = new Random();
            int colo = Color.argb(255, rand.nextInt(256),rand.nextInt(10),rand.nextInt(256));
            textView.setTextColor(colo);
        }


        Animation animate = AnimationUtils.loadAnimation(this, R.anim.animation);
        animate.setAnimationListener(this);


        textView.startAnimation(animate);





    }
    public void startAnimation(View view){
        Animation animate = AnimationUtils.loadAnimation(this, R.anim.animation);
        animate.setAnimationListener(this);

        view.startAnimation(animate);


    }


    @Override
    public void onAnimationStart(Animation animation) {




    }

    @Override
    public void onAnimationEnd(Animation animation) {


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }

}
