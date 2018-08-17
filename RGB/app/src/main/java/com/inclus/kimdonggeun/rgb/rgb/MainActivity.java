package com.inclus.kimdonggeun.rgb.rgb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.inclus.kimdonggeun.rgb.GraphActivity;
import com.inclus.kimdonggeun.rgb.GuideActivity;
import com.inclus.kimdonggeun.rgb.GuideScreen;
import com.inclus.kimdonggeun.rgb.Main0_Start;
import com.inclus.kimdonggeun.rgb.Main1_Graph;
import com.inclus.kimdonggeun.rgb.Main_PagerAdapter;
import com.inclus.kimdonggeun.rgb.R;
import com.inclus.kimdonggeun.rgb.graph_data_Sqliteopenhelper;
import com.inclus.kimdonggeun.rgb.graph_gridview_adapter;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton btnGraph, btnGuide, btnNextL, btnNextR;
    ViewPager container;
    Boolean guideOn=true;

    public SharedPreferences prefs;


    @Override
    protected void onResume() {
        super.onResume();

        com.inclus.kimdonggeun.rgb.rgb.graph_data_Sqliteopenhelper data_sqliteopenhelper = new com.inclus.kimdonggeun.rgb.rgb.graph_data_Sqliteopenhelper(this, "graphdata1.db", null, 1);
        SQLiteDatabase db = data_sqliteopenhelper.getReadableDatabase();
        com.inclus.kimdonggeun.rgb.rgb.graph_gridview_adapter database_adpater = new com.inclus.kimdonggeun.rgb.rgb.graph_gridview_adapter();

        String SQL = "select * from " + "graph_data";
        Cursor c1 = db.rawQuery(SQL, null);

        for (int i = 0; i < c1.getCount(); i++) {
            c1.moveToNext();
            database_adpater.additem(c1.getInt(0), c1.getInt(1), c1.getInt(2), c1.getInt(3), c1.getInt(4), c1.getInt(5), c1.getInt(6), c1.getInt(7), c1.getString(8),false);
        }
        Calendar date = Calendar.getInstance();
        if(database_adpater.search_item(date.get(Calendar.YEAR),date.get(Calendar.MONTH)+1,date.get(Calendar.DAY_OF_MONTH))!=null){
            container.setCurrentItem(1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        prefs = getSharedPreferences("Pref", MODE_PRIVATE);
        checkFirstRun();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGraph = findViewById(R.id.btn_graph);
        btnGraph.setOnClickListener(this);
        btnGuide = findViewById(R.id.btn_guide);
        btnGuide.setOnClickListener(this);
        btnNextR = findViewById(R.id.btn_arrow_right);
        btnNextR.setOnClickListener(this);
        btnNextL = findViewById(R.id.btn_arrow_left);
        btnNextL.setOnClickListener(this);


        container = findViewById(R.id.content_frame);
        com.inclus.kimdonggeun.rgb.rgb.Main_PagerAdapter adapter = new com.inclus.kimdonggeun.rgb.rgb.Main_PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new com.inclus.kimdonggeun.rgb.rgb.Main0_Start());
        adapter.addFragment(new com.inclus.kimdonggeun.rgb.rgb.Main1_Graph());
        container.setAdapter(adapter);


        container.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if(position==0) {
                    btnNextR.setVisibility(View.VISIBLE);
                    btnNextL.setVisibility(View.GONE);
                }else {
                    btnNextR.setVisibility(View.GONE);
                    btnNextL.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        com.inclus.kimdonggeun.rgb.rgb.graph_data_Sqliteopenhelper data_sqliteopenhelper = new com.inclus.kimdonggeun.rgb.rgb.graph_data_Sqliteopenhelper(this, "graphdata1.db", null, 1);
        SQLiteDatabase db = data_sqliteopenhelper.getReadableDatabase();
        com.inclus.kimdonggeun.rgb.rgb.graph_gridview_adapter database_adpater = new com.inclus.kimdonggeun.rgb.rgb.graph_gridview_adapter();

        String SQL = "select * from " + "graph_data";
        Cursor c1 = db.rawQuery(SQL, null);

        for (int i = 0; i < c1.getCount(); i++) {
            c1.moveToNext();
            database_adpater.additem(c1.getInt(0), c1.getInt(1), c1.getInt(2), c1.getInt(3), c1.getInt(4), c1.getInt(5), c1.getInt(6), c1.getInt(7), c1.getString(8),false);
        }
        Calendar date = Calendar.getInstance();
        if(database_adpater.search_item(date.get(Calendar.YEAR),date.get(Calendar.MONTH)+1,date.get(Calendar.DAY_OF_MONTH))!=null){
           container.setCurrentItem(1);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_graph:
                Intent intent = new Intent(this, com.inclus.kimdonggeun.rgb.rgb.GraphActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_guide:
                if(container.getCurrentItem() == 0) showGuideScreen(0);
                else showGuideScreen(1);
                break;
            case R.id.btn_arrow_left:
                container.setCurrentItem(0);
                break;
            case R.id.btn_arrow_right:
                container.setCurrentItem(1);
                break;
            default:
                break;
        }
    }

    private void showGuideScreen(int image){

        com.inclus.kimdonggeun.rgb.rgb.GuideScreen dialog = new com.inclus.kimdonggeun.rgb.rgb.GuideScreen(this, android.R.style.Theme_Translucent_NoTitleBar);

        dialog.setGuidePage(image);
        dialog.show();

    }
    public void checkFirstRun(){
        boolean isFirstRun = prefs.getBoolean("isFirstRun",true);
        if(isFirstRun)
        {
            Intent intent = new Intent(this, com.inclus.kimdonggeun.rgb.rgb.GuideActivity.class);
            startActivity(intent);

            prefs.edit().putBoolean("isFirstRun",false).apply();
        }
    }

}
