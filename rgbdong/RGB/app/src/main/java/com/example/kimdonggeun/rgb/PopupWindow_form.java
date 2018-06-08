package com.example.kimdonggeun.rgb;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;


public class PopupWindow_form extends AppCompatActivity {

    ProgressBar R_seekbar;
    ProgressBar G_seekbar;
    ProgressBar B_seekbar;


    Custom_viewpager viewpager;

    boolean threaddead;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view= getLayoutInflater().inflate(R.layout.popupwindow_form,null);
        setContentView(view);





        ImageButton exit_btn = (ImageButton)findViewById(R.id.form_exit_btn);
        exit_btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
            threaddead = true;
            finish();

            }
        });
        //seek bar

        View.OnTouchListener no_touch_event = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        };

        R_seekbar = (SeekBar)findViewById((R.id.form_seekbar1));
        G_seekbar = (SeekBar)findViewById((R.id.form_seekbar2));
        B_seekbar = (SeekBar)findViewById((R.id.form_seekbar3));
        R_seekbar.setOnTouchListener(no_touch_event);
        G_seekbar.setOnTouchListener(no_touch_event);
        B_seekbar.setOnTouchListener(no_touch_event);




        //viewpager

        viewpager = (Custom_viewpager)view.findViewById(R.id.form_view_pager);
        viewpager.setAdapter(new PopupWindow_viewpageradapter(getSupportFragmentManager()));





    }

    public void addR_seekbar(int i){
        R_seekbar.setProgress(R_seekbar.getProgress()+i);
    }

    public void addG_seekbar(int i){
        G_seekbar.setProgress(G_seekbar.getProgress()+i);
    }
    public void addB_seekbar(int i){
        B_seekbar.setProgress(B_seekbar.getProgress()+i);
    }

    public void next_viewpage(){
        viewpager.setCurrentItem(viewpager.getCurrentItem()+1);
    }

    public void previous_viewpage(){
        viewpager.setCurrentItem(viewpager.getCurrentItem()-1);
    }



}
