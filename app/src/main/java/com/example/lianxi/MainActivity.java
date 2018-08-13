package com.example.lianxi;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends Activity {
    LottieAnimationView start_animation=null;
    Button start_game=null;
    Button match_player=null;
    Button instruction=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        start_animation = findViewById(R.id.start_animation);
        start_game = findViewById(R.id.start_game);
        match_player = findViewById(R.id.match_player);
        instruction = findViewById(R.id.instruction);
        start_animation.addAnimatorListener(new Animator.AnimatorListener() {
                                               @Override
                                               public void onAnimationStart(Animator animator) {
                                                   Handler handler =new Handler();
                                                   handler.postDelayed(new Runnable() {
                                                       @Override
                                                       public void run() {
                                                           start_game.setVisibility(View.VISIBLE);
                                                       }
                                                   },500);
                                                   handler.postDelayed(new Runnable() {
                                                       @Override
                                                       public void run() {
                                                           match_player.setVisibility(View.VISIBLE);
                                                       }
                                                   },1000);
                                                   //match_player.setVisibility(View.VISIBLE);
                                                   handler.postDelayed(new Runnable() {
                                                       @Override
                                                       public void run() {
                                                           instruction.setVisibility(View.VISIBLE);
                                                       }
                                                   },1500);
                                                   //instruction.setVisibility(View.VISIBLE);
                                               }
                                               @Override
                                               public void onAnimationEnd(Animator animator) {}
                                               @Override
                                               public void onAnimationCancel(Animator animator) {}
                                               @Override
                                               public void onAnimationRepeat(Animator animator) {}
                                           });
        start_game.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GameActivity.class);
                startActivity(intent);

            }
        });

    }
}
