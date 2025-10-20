package com.example.tictactoe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


//        Player representation
//        0 - X
//        1 - o
        int activePlayer = 0;
        int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
//        Sate meanings
//        0 - X
//        1 - o
//        2 - NULL
        int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                {0, 4, 8}, {2, 4, 6}};
        boolean gameActive = true;
    @SuppressLint("SetTextI18n")
    public void playerTap(View view) {

        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (!gameActive) {
            gameReset(view);
        }

        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
//            img.setTranslationZ(-1000f);

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn Tap To Play");
            }
            else {
                img.setImageResource(R.drawable.zero);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn Tap To Play");
            }
            img.setTranslationZ(-1000f);  // This moves the image to the "back" visually
            img.animate().translationYBy(0f).setDuration(300);
//            img.animate().translationYBy(1000f).setDuration(300);
        }

//        Check if any player won
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
//                Find out who win
                gameActive = false;
                String winnerStr;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won";
                }
                else  {
                    winnerStr = "O has won";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);

            }
        }
        boolean isDraw = true;
        for (int j : gameState) {
            if (j == 2) {
                isDraw = false;
                break;
            }
        }

        if (isDraw && gameActive) {
            gameActive = false;
            TextView status = findViewById(R.id.status);
            status.setText("It's a Draw!");
        }
    }

    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        Arrays.fill(gameState, 2);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}