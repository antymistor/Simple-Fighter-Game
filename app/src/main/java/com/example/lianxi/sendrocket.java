package com.example.lianxi;

/**
 * Created by Administrator on 2018/8/8 0008.
 */
import android.animation.Animator;
import android.app.Activity;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.math.*;
import com.airbnb.lottie.LottieAnimationView;
public class sendrocket  {
    public ImageView jg=null;
    public GameActivity context=null;
    public Handler hdler=null;
    private int ph=0;
    private int xp=0;
    private int yp=0;
    private float xd=0;
    private float yd=0;
    public sendrocket(GameActivity con ,Handler handler)
    {
        hdler=handler;
        context =con;
       // Lay=Layout;
        jg =new ImageView(context);
        jg.setImageDrawable(context.getResources().getDrawable((R.drawable.jiguang)));
        jg.setRotation(270);
        //jg.setVisibility(View.INVISIBLE);
        context.beijing.addView(jg);
    }

    public void lancher()
    {
        jg.setRotation(context.ha+270);
        jg.setVisibility(View.VISIBLE);
        TranslateAnimation anim1 = new TranslateAnimation(
                Animation.ABSOLUTE,context.hx+520,
                Animation.ABSOLUTE, context.hx+520+1000*(float)Math.cos(context.ha*3.1416f/180),
                Animation.ABSOLUTE, context.hy+1000,
                Animation.ABSOLUTE, context.hy+1000+1000*(float)Math.sin(context.ha*3.1416f/180));
        anim1.setDuration(1000);
        anim1.setFillAfter(true);
        jg.startAnimation(anim1);
        xp=(int)(context.hx+520);
        yp=(int)(context.hy+1000);
        //jiance.start();
        new Thread(new Runnable() {
            int j=0;
            int i=10;
            int x=0;
            int y=0;
            @Override
            public void run() {
                while(i>0){
                    i--;
                    try {
                        Thread.sleep(100);
                        xp = xp + (int) (100 * Math.cos(context.ha * 3.1416f / 180));
                        yp = yp + (int) (100 * Math.sin(context.ha * 3.1416f / 180));

                        for (j = 0; j < context.targetcount; j++)
                        {
                            x = context.targetposition[j] / 10000;
                            y = context.targetposition[j] % 10000;
                            if (Math.abs(x - xp) < 50 & Math.abs(y - yp) < 50)
                            // if (i<6)
                            {
                                ph=j;
                                context.targetposition[ph]=0;
                                hdler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        //jg.setVisibility(View.INVISIBLE);
                                        jg.clearAnimation();
                                        //jg.setImageDrawable(context.getResources().getDrawable((R.drawable.blood)));
                                        context.beijing.removeView(jg);
                                        context.beijing.removeView(context.targetp[ph]);

                                    }
                                });
                            }
                        }
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                hdler.post(new Runnable() {
                    @Override
                    public void run() {
                        context.beijing.removeView(jg);
                    }
                });
            }
        }).start();

    }
  /*Thread jiance=new Thread(new Runnable() {
      int j=0;
      int i=10;
      int x=0;
      int y=0;
      @Override
      public void run() {
          while(i>0){
              i--;
              try {
                  Thread.sleep(100);
                  xp = xp + (int) (100 * Math.cos(context.ha * 3.1416f / 180));
                  yp = yp + (int) (100 * Math.sin(context.ha * 3.1416f / 180));

                  for (j = 0; j < context.targetcount; j++)
                  {
                      ph++;
                      x = context.targetposition[j] / 10000;
                      y = context.targetposition[j] % 10000;
                      if (Math.abs(x - xp) < 50 & Math.abs(y - yp) < 50)
                     // if (i<6)
                      {
                          hdler.post(new Runnable() {
                              @Override
                              public void run() {
                                  //jg.setVisibility(View.INVISIBLE);
                                  jg.setImageDrawable(context.getResources().getDrawable((R.drawable.blood)));
                                  //context.targetlist.get(j).setVisibility(View.INVISIBLE);
                                  //context.targetlist.get(j).setImageDrawable(context.getResources().getDrawable((R.drawable.blood)));
                                  //context.beijing.removeView(context.targetlist.get(j));
                                 // context.beijing.removeView(context.gj);
                                  //context.targetp[j].setVisibility(View.INVISIBLE);
                                  context.PE.setText("T"+ph);
                                  //context.beijing.removeView(context.targetp[j]);
                                  //context.targetp[j].setImageDrawable(context.getResources().getDrawable((R.drawable.blood)));
                              }
                          });
                      }
                  }
              }
              catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
         hdler.post(new Runnable() {
                        @Override
                        public void run() {
                            context.beijing.removeView(jg);
                        }
                    });
      }
  });*/
}
