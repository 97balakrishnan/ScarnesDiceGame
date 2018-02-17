package com.example.user.scarnesdice;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    private int comptotal;
    private int usertotal;
    private int compturn;
    private int userturn;
    private Random random=new Random();

    public int RandomDice()
    {
        return (random.nextInt(6)+1);
    }
    public void fade(){
        ImageView image = (ImageView)findViewById(R.id.imageView);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        image.startAnimation(animation1);
    }
    public void SetImg(int a)
    {
        ImageView img=(ImageView)findViewById(R.id.imageView);
        switch(a)
        {
            case 1:img.setImageResource(R.drawable.dice1);break;
            case 2:img.setImageResource(R.drawable.dice2);break;
            case 3:img.setImageResource(R.drawable.dice3);break;
            case 4:img.setImageResource(R.drawable.dice4);break;
            case 5:img.setImageResource(R.drawable.dice5);break;
            case 6:img.setImageResource(R.drawable.dice6);break;
        }
        fade();
    }
    public void ComputerTurn(final View v)
    {

        new CountDownTimer(60000, 1000)
        {
            public void onFinish() {
                Button b=(Button)findViewById(R.id.broll);
                b.setEnabled(true);
                Button b1=(Button)findViewById(R.id.bhold);
                b1.setEnabled(true);

                TextView m=(TextView)findViewById(R.id.message);
                m.setText("Your Turn!!");


            }

            public void onTick(long millisUntilFinished)
            {
                int i=RandomDice();

                if(i==1)
                {
                    SetImg(i);
                    compturn=0;
                    updater(v);

                    Button b=(Button)findViewById(R.id.broll);
                    b.setEnabled(true);
                    Button b1=(Button)findViewById(R.id.bhold);
                    b1.setEnabled(true);
                    MediaPlayer sound=MediaPlayer.create(MainActivity.this,R.raw.notif);
                    sound.start();

                    TextView m=(TextView)findViewById(R.id.message);
                    m.setText("Your Turn!!");

                    cancel();
                }
                else if(compturn+i>20)
                { SetImg(i);
                    compturn+=i;
                        comptotal += compturn;
                        compturn = 0;
                        updater(v);
                    Button b=(Button)findViewById(R.id.broll);
                    b.setEnabled(true);
                    Button b1=(Button)findViewById(R.id.bhold);
                    b1.setEnabled(true);
                    MediaPlayer sound=MediaPlayer.create(MainActivity.this,R.raw.notif);
                    sound.start();

                    TextView m=(TextView)findViewById(R.id.message);
                    m.setText("Your Turn!!");

                    cancel();

                }

                else
                {    SetImg(i);
                        compturn += i;
                        updater(v);
                    MediaPlayer sound3 =MediaPlayer.create(MainActivity.this,R.raw.notif);
                    sound3.start();
                }
            if(comptotal>100)
            {
                TextView m=(TextView)findViewById(R.id.message);
                m.setText("Computer wins!!!!");
                updater(v);
                final Handler handler = new Handler();
                MediaPlayer sound=MediaPlayer.create(MainActivity.this,R.raw.comp);
                sound.start();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ResetClick(v);
                    }
                }, 5000);

                cancel();
            }


            }
        }.start();

    }

    public void HoldClick(final View v)
    {
        usertotal+=userturn;
        userturn=0;

        updater(v);
        MediaPlayer sound=MediaPlayer.create(MainActivity.this,R.raw.notif);
        sound.start();

        if(usertotal>100) {
            TextView t = (TextView) findViewById(R.id.message);
            t.setText("You win!!!");
            updater(v);
            final Handler handler = new Handler();
            MediaPlayer sound2=MediaPlayer.create(MainActivity.this,R.raw.comp);
            sound2.start();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    ResetClick(v);
                }
            }, 5000);
          sound2.stop();
        }
        else {
            MediaPlayer sound1 = MediaPlayer.create(MainActivity.this,R.raw.roller);
            sound1.start();
            Button b = (Button) findViewById(R.id.broll);
            b.setEnabled(false);
            Button b1 = (Button) findViewById(R.id.bhold);
            b1.setEnabled(false);
            TextView m = (TextView) findViewById(R.id.message);
            m.setText("Computer's Turn!!");

            ComputerTurn(v);
        }
        }
    public void ResetClick(View v) {
        comptotal = usertotal = compturn = userturn = 0;
        updater(v);
    }
    public void updater(View v)
    {
        TextView t1=(TextView)findViewById(R.id.pscore);
        t1.setText("Your Score:"+usertotal);
        TextView t2=(TextView)findViewById(R.id.cscore);
        t2.setText("Computer Score:"+comptotal);
        TextView t3=(TextView)findViewById(R.id.uturn);
        t3.setText("turnscore:"+userturn);
        TextView t4=(TextView)findViewById(R.id.cturn);
        t4.setText("turnscore:"+compturn);


    }

    public void RollClick(final View v)
    {
        MediaPlayer sound = MediaPlayer.create(MainActivity.this,R.raw.roller);
        sound.start();
        int r=RandomDice();

        TextView t=(TextView)findViewById(R.id.uturn);
       switch(r)
       {
           case 1:
           {
               userturn=0;
               SetImg(r);
               Button b=(Button)findViewById(R.id.broll);
               b.setEnabled(false);
               Button b1=(Button)findViewById(R.id.bhold);
               b1.setEnabled(false);
               TextView m=(TextView)findViewById(R.id.message);
               m.setText("Computer's Turn!!");

               final Handler handler = new Handler();

               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {

                       ComputerTurn(v);
                   }
               }, 4000);

                  break;
           }
           case 2:
           {      userturn+=2;
                   SetImg(r);
                   break;
           }
           case 3:
           {       userturn+=3;
                   SetImg(r);
                   break;
           }
           case 4:
           {       userturn+=4;
                   SetImg(r);
                   break;
           }
           case 5:
           {   userturn+=5;
               SetImg(r);
               break;
           }
           case 6:
           {   userturn+=6;
               SetImg(r);
               break;
           }
       }
        MediaPlayer sound3 =MediaPlayer.create(MainActivity.this,R.raw.notif);
        sound3.start();
       t.setText("turnscore:"+userturn);


    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
