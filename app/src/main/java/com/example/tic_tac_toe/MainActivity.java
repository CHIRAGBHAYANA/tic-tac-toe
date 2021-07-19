package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.number.IntegerWidth;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Player Representation
     // 0 - X
    //  1 - O
    int activePlayer = 1;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    // 0 - X
    // 1 - O
    // 2 -blank state
    int [][] winningState = {{0,1,2},{3,4,5},{6,7,8},
                             {0,3,6},{1,4,7},{2,5,8},
                                {0,4,8},{2,4,6}};
    boolean gameActive = true;

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i=0;i<gameState.length;i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("Tap to Play");

    }

    public void tapTap(View view){
        ImageView img = (ImageView)view;
        int taappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[taappedImage] == 2 && gameActive ){
            gameState[taappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 1){
                img.setImageResource(R.drawable.zero);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to Play ");
            }
            else{
                img.setImageResource(R.drawable.cross1);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("0's Turn - Tap to Play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        // checking if any player has won or not
              for(int[] winposition: winningState){
                  if(gameState[winposition[0]] == gameState[winposition[1]]  &&
                          gameState[winposition[1]] == gameState[winposition[2]] &&
                  gameState[winposition[0]]!=2){
                    // somebody is win
                      String winnerStr;
                      gameActive = false;
                    if(gameState[winposition[0]]== 0){
                        winnerStr = "X has Won";
                    }
                    else{
                        winnerStr = "O has won";
                    }
                    // Updating the status bar for winner announcemen
                      TextView  status = findViewById(R.id.status);
                    status.setText(winnerStr);
                  }

              }

        boolean emptySquare = false;
        for (int squareState : gameState) {
            if (squareState == 2) {
                emptySquare = true;
                break;
            }
        }
        if (!emptySquare && gameActive) {
            // Game is a draw
            gameActive = false;
            String winnerStr;
            winnerStr = "No one won";
            TextView status = findViewById(R.id.status);
            status.setText(winnerStr);
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}