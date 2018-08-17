package com.inclus.kimdonggeun.rgb.rgb;

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

import com.inclus.kimdonggeun.rgb.Guide_PagerAdapter;
import com.inclus.kimdonggeun.rgb.PopupWindow_form;
import com.inclus.kimdonggeun.rgb.R;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener{

    ViewPager container;
    ImageButton btn_go, btn_arrow;


    //guide image
    int[] guideImages= {R.drawable.guide1,
                        R.drawable.guide2,
                        R.drawable.guide3,
                        R.drawable.guide4 };
    com.inclus.kimdonggeun.rgb.rgb.Guide_PagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_form);

        btn_arrow = findViewById(R.id.btn_arrow_guide);
        btn_arrow.setOnClickListener(this);
        btn_go = findViewById(R.id.btn_go_guide);
        btn_go.setOnClickListener(this);

        container = findViewById(R.id.guideContainer);
        adapter = new com.inclus.kimdonggeun.rgb.rgb.Guide_PagerAdapter(this, guideImages);
        container.setAdapter(adapter);



        container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if(position==3) {
                    btn_go.setVisibility(View.VISIBLE);
                    btn_arrow.setVisibility(View.GONE);
                }
                else {
                    btn_go.setVisibility(View.GONE);
                    btn_arrow.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_arrow_guide:
                int position = container.getCurrentItem();
                container.setCurrentItem(position+1, true);


                break;
            case R.id.btn_go_guide:
                    Intent intent = new Intent(this, com.inclus.kimdonggeun.rgb.rgb.PopupWindow_form.class);
                    startActivity(intent);
                    finish();
                break;
            default:
                break;
        }
    }
}
