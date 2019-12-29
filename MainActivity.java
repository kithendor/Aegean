package com.app.kitsos.tikitaki;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView im;
    LinearLayout ln;
    TableLayout tl;
    TextView wintxt;
    int activeplayer = 0; //0=yellow, 1=red
    boolean activegame = true;

    int gameState[] = {2,2,2,2,2,2,2,2,2}; //2 = keni eikona
    int winsit[][] = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{2,5,8},{1,4,7}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ln = findViewById(R.id.panel);
        ln.setVisibility(View.INVISIBLE);
        tl = findViewById(R.id.tablelo);
        wintxt = findViewById(R.id.textView);
    }

    public void addtile(View v)
    {
        im = (ImageView) v;

        int tap = Integer.parseInt(im.getTag().toString()); //System.out.println(im.getTag().toString());

        if(gameState[tap]==2 && activegame)
        {
            gameState[tap] = activeplayer;
            im.setTranslationY(-200f);
            if(activeplayer==0)
            {
                im.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            }
            else
            {
                im.setImageResource(R.drawable.red);
                activeplayer = 0;
            }

            im.animate().translationYBy(200f).rotation(300).setDuration(500);

            for (int i[] : winsit) {
                if (gameState[i[0]] == gameState[i[1]] && gameState[i[1]] == gameState[i[2]] &&
                        gameState[i[0]] != 2) {
                    wingame(1);
                }
                else
                {
                    boolean over = true;

                    for(int z:gameState)
                    {
                        if(z==2){
                            over = false;
                        }
                    }

                    if(over){
                        wingame(0);
                    }
                }
            }
        }

    }

    private void wingame(int x)
    {
        if(x==0)
        {
            wintxt.setText("isopalia");
        }
        else
        {
            String winner;
            if(activeplayer==0)
                winner = "Red";
            else
                winner = "Yellow";
            wintxt.setText(winner);
        }



        ln.setVisibility(View.VISIBLE);
        activegame = false;
    }

    public void resetgame(View v)
    {
        ln.setVisibility(View.INVISIBLE);
        activeplayer = 0;
        activegame = true;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }

        for(int i=0;i<3;i++)
        {
            TableRow row = (TableRow)tl.getChildAt(i);
            for(int j=0;j<3;j++)
            {
                ((ImageView)row.getChildAt(j)).setImageResource(0);
            }
        }
    }


}
