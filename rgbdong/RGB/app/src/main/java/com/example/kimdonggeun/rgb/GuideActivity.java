package com.example.kimdonggeun.rgb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener{

    ViewPager container;
    Button btn_go, btn_arrow;


    //guide image
    int[] guideImages= {R.drawable.guide1,
                        R.drawable.guide2,
                        R.drawable.guide3,
                        R.drawable.guide4 };
    Guide_PagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_form);

        btn_arrow = findViewById(R.id.btn_arrow_guide);
        btn_arrow.setOnClickListener(this);
        btn_go = findViewById(R.id.btn_go_guide);
        btn_go.setOnClickListener(this);

        container = findViewById(R.id.guideContainer);
        adapter = new Guide_PagerAdapter(this, guideImages);
        container.setAdapter(adapter);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_arrow_guide:
                int position = container.getCurrentItem();
                container.setCurrentItem(position+1, true);
                break;
            case R.id.btn_go_guide:
                if(container.getCurrentItem() == 3) {
                    Intent intent = new Intent(this, PopupWindow_form.class);
                    startActivity(intent);
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
