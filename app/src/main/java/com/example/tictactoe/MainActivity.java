package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //0-X 1-O 2-Empty
    int activePlayer=0;
    int gameState[]={2,2,2,2,2,2,2,2,2};
    int winPos[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    String winner;
    ImageView cell[]=new ImageView[9];
    ImageView home;
    ImageView redo;
TextView endmsg;
int lastcell;
int wpos;
    public boolean checkWin(){
        wpos=0;
        for(int pos[]:winPos){
            if(gameState[pos[0]]==gameState[pos[1]] && gameState[pos[1]]==gameState[pos[2]] && gameState[pos[0]]!=2){
                return(true);
            }
            wpos++;
        }
        return(false);
    }
    public boolean checkDraw() {
        for(int a:gameState){
            if(a==2){
                return false;
            }
        }
        return true;
    }
    public void win(){
        for(ImageView img:cell){
            img.setVisibility(View.INVISIBLE);

        }
        for(int a:winPos[wpos]){
            cell[a].setVisibility(View.VISIBLE);
        }
        endmsg.setText(winner+" Won!");
        endmsg.setVisibility(View.VISIBLE);
        redo.setClickable(false);
        winner="";

    }
    public void draw(){
        endmsg.setText("XO Draw!");
        endmsg.setVisibility(View.VISIBLE);
        redo.setClickable(false);
    }
    public void Tap(View view){
        ImageView img=(ImageView) view;
        redo.setClickable(true);
        int tappedPos=Integer.parseInt(img.getTag().toString().substring(1));
        lastcell=tappedPos;
        if(gameState[tappedPos]==2){
            gameState[tappedPos] = activePlayer;
            img.setClickable(false);
            if (activePlayer == 0) {
                activePlayer = 1;
                img.setImageResource(R.drawable.x);
                img.setAlpha(0f);
                img.animate().alpha(1f).setDuration(200).start();
            } else {
                activePlayer = 0;
                img.setImageResource(R.drawable.o);
                img.setAlpha(0f);
                img.animate().alpha(1f).setDuration(200).start();
            }
            if (checkWin()) {
                winner = "X";
                if (activePlayer == 0) {
                    winner = "O";
                }
                win();
            }
            else if(checkDraw()){
                draw();

        }

    }
    }
    public void redofun(View view){
        activePlayer=(activePlayer==1)?0:1;
        cell[lastcell].setImageResource(0);
        cell[lastcell].setClickable(true);
        gameState[lastcell]=2;
        redo.setClickable(false);
    }
    public void homefun(View view){
        for(ImageView img:cell){
            img.setImageResource(0);
            img.setClickable(true);
            img.setVisibility(View.VISIBLE);
        }
        endmsg.setVisibility(View.INVISIBLE);
        gameState=new int[]{2,2,2,2,2,2,2,2,2};
        activePlayer=0;
        redo.setClickable(false);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        endmsg=findViewById(R.id.textView3);
        endmsg.setVisibility(View.INVISIBLE);
        cell[0]=findViewById(R.id.imageView1);
        cell[1]=findViewById(R.id.imageView2);
        cell[2]=findViewById(R.id.imageView3);
        cell[3]=findViewById(R.id.imageView4);
        cell[4]=findViewById(R.id.imageView5);
        cell[5]=findViewById(R.id.imageView6);
        cell[6]=findViewById(R.id.imageView7);
        cell[7]=findViewById(R.id.imageView8);
        cell[8]=findViewById(R.id.imageView9);
        home=findViewById(R.id.homeIcon);
        redo=findViewById(R.id.redoIcon);
        redo.setClickable(false);




    }
    }
