package com.example.lianxi;

import android.animation.Animator;
import android.app.Activity;
import android.app.usage.UsageEvents;
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
import java.util.ArrayList;
import java.util.List;

import com.airbnb.lottie.LottieAnimationView;

public class GameActivity extends Activity {

    private float dx=0.f;
    private float dy=0.f;
    public float hx=0.f;
    public float hy=0.f;
    public float ha=0.f;
    public float px=0;
    public float py=0;
    private int jcflag=0;
    public int targetcount=0;
    public int targetposition[] =new int[20];
    public ImageView targetp[]=new ImageView[20];
    private  MotionEvent eventp;
    private static Handler handler =new Handler();
    ImageView huaqiu=null;
    ImageView fj=null;
    TextView PE=null;
    ImageView gj=null;
    RelativeLayout beijing=null;
    public ArrayList<ImageView> targetlist= new ArrayList<>();

    Thread jcwz =new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(50);
                    if(jcflag==1&eventp.getY(0)<600&eventp.getX(0)<600) {
                        //if(jcflag==1) {
                        float h=0;
                        px = (eventp.getX(0)-200)/1333;
                        py = (eventp.getY(0)-300)/2217;
                        h=(float)Math.sqrt(px*px+py*py);
                        if( h>0.085f)
                        {px=px*0.085f/h;py=py*0.085f/h;}
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //PE.setText("X" + px + "  " + "Y" + py);
                                //huaqiumove(px*1.5f,py);
                                float pp=(float)(Math.atan2(py,px)*180/3.1415926f);
                                if(pp<0){pp=360+pp;}
                                feijimove(hx+px*100.f,hy+py*100.f,pp);
                                //PE.setText("X"+ (int)(hx+px*100.f) +"Y"+(int)(hy+py*100.f) );
                                huaqiumove(px*1.5f,py);
                            }
                        });
                    }
                    else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                huaqiumove(0,0);
                            }
                        });

                        }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });


    Thread targetcreate=new Thread(new Runnable() {
        float x=0;
        float y=0;
        @Override
        public void run() {
            while (true){
                   try {
                    Thread.sleep(4000);
                    if(targetcount<20) {
                        targetcount++;
                        x = (float) (40+1000 * Math.random());
                        y = (float) (40+2080 * Math.random());
                        targetposition[targetcount-1]=(int)(x)*10000+(int)(y);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                setTarget(x,y);
                            }
                        });
                    }
                  }catch (InterruptedException e){
                       e.printStackTrace();
                   }
                }
            }
    });
    public void setTarget(float x,float y)
    {

        /*targetlist.add(new ImageView(this));
        targetlist.get(targetcount-1).setImageDrawable(getResources().getDrawable((R.drawable.icon2)));
        targetlist.get(targetcount-1).setRotation(270);
        beijing.addView(targetlist.get(targetcount-1));*/
        targetp[targetcount-1]=new ImageView(this);
        targetp[targetcount-1].setImageDrawable(getResources().getDrawable((R.drawable.icon2)));
        beijing.addView(targetp[targetcount-1]);


        //targetlist.get(targetcount-1).setVisibility(View.VISIBLE);
        TranslateAnimation anim1 = new TranslateAnimation(
                Animation.ABSOLUTE,540,
                Animation.ABSOLUTE,x,
                Animation.ABSOLUTE,1080,
                Animation.ABSOLUTE,y);
        anim1.setDuration(200);
        anim1.setFillAfter(true);
        targetp[targetcount-1].startAnimation(anim1);
    }
    public void huaqiumove(float tx ,float ty)
    {
        TranslateAnimation anim = new TranslateAnimation(
                                Animation.RELATIVE_TO_PARENT, dx,
                                Animation.RELATIVE_TO_PARENT, tx,
                                 Animation.RELATIVE_TO_PARENT, dy,
                                Animation.RELATIVE_TO_PARENT, ty);
                 anim.setDuration(50);
                 anim.setFillAfter(true);
                 dx=tx;
                 dy=ty;
                 huaqiu.startAnimation(anim);
    }
    public void feijimove(float tx,float ty,float angel)
    {
        AnimationSet animSet = new AnimationSet(true);
        TranslateAnimation anim1 = new TranslateAnimation(
                Animation.ABSOLUTE, hx,
                Animation.ABSOLUTE, tx,
                Animation.ABSOLUTE, hy,
                Animation.ABSOLUTE, ty);
        anim1.setDuration(50);
        anim1.setFillAfter(true);
        hx=tx;
        hy=ty;

        if(Math.abs(ha-angel)>340){
            if(ha>340) {ha=0;}else {ha=359;}
        }
        RotateAnimation anim2 = new RotateAnimation(ha, angel,
                             Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                            0.5f);
        ha=angel;
        anim2.setDuration(50);
        anim2.setFillAfter(true);
        animSet.addAnimation(anim2);
        animSet.addAnimation(anim1);
        animSet.setFillAfter(true);
        fj.startAnimation(animSet);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if(event.getY()<1000){
                if(jcflag==0)
                {
                jcflag=1;
                eventp=event;
                jcwz.start();}
                else
                {jcflag=1;eventp=event;}
                break;}
                else{
                    if(event.getX()<300&event.getX()>50&event.getY()>1800){
                        sendrocket jg=new sendrocket(this,handler);
                        jg.lancher();
                        gj.setImageDrawable(getResources().getDrawable((R.drawable.buton2)));}
                }
            case MotionEvent.ACTION_MOVE:
                eventp=event;
                break;
            case MotionEvent.ACTION_UP:
                //jcwz.stop();
                if(event.getY()<1000){
                jcflag=2;}
                else{
                    if(event.getX()<300&event.getX()>50&event.getY()>1800){
                        gj.setImageDrawable(getResources().getDrawable((R.drawable.buton1)));}
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if(event.getX(1)<300&event.getX(1)>50&event.getY(1)>1800){
                    sendrocket jg=new sendrocket(this,handler);
                    jg.lancher();
                gj.setImageDrawable(getResources().getDrawable((R.drawable.buton2)));}
                break;
            case MotionEvent.ACTION_POINTER_UP:
                if(event.getX(1)<300&event.getX(1)>50&event.getY(1)>1800){
                    gj.setImageDrawable(getResources().getDrawable((R.drawable.buton1)));}
                break;
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        huaqiu=findViewById(R.id.huaqiu);
        PE=findViewById(R.id.PE);
        fj=findViewById(R.id.fj);
        gj=findViewById(R.id.gj);
        beijing=findViewById(R.id.beijing);
        targetcreate.start();
    }

}