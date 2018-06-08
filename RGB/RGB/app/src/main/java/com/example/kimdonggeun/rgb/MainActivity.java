package com.example.kimdonggeun.rgb;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton btnGraph, btnGuide;
    ViewPager container;
    View setting_DialogView;
    Boolean guideOn=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGraph = findViewById(R.id.btn_graph);
        btnGraph.setOnClickListener(this);

        if(guideOn){
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            guideOn = !guideOn;
        }

        container = findViewById(R.id.content_frame);
        Main_PagerAdapter adapter = new Main_PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Main0_Start());
        adapter.addFragment(new Main1_Graph());
        container.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_graph:
                Intent intent = new Intent(this, GraphActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}