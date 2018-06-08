package com.example.kimdonggeun.rgb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;


public class GraphActivity extends AppCompatActivity {

    SQLiteDatabase db;
    graph_data_Sqliteopenhelper data_sqliteopenhelper;

    ImageButton btn_exit;
    GridView week_gridview;
    GridView month_gridview;
    graph_gridview_adapter week_adapter = new graph_gridview_adapter();
    graph_gridview_adapter database_adpater = new graph_gridview_adapter();
    graph_gridview_adapter month_adapter = new graph_gridview_adapter();

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);

        data_sqliteopenhelper = new graph_data_Sqliteopenhelper(getApplicationContext(), "graphdata1.db", null, 1);
        db = data_sqliteopenhelper.getReadableDatabase();


        btn_exit = (ImageButton) findViewById(R.id.graph_calender_exit);
        btn_exit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


//        db=data_sqliteopenhelper.getReadableDatabase();
            String SQL = "select * from " + "graph_data";
            Cursor c1 = db.rawQuery(SQL, null);

        for (int i = 0; i < c1.getCount(); i++) {
            c1.moveToNext();
            database_adpater.additem(c1.getInt(0), c1.getInt(1), c1.getInt(2), c1.getInt(3), c1.getInt(4), c1.getInt(5), c1.getInt(6), c1.getInt(7), c1.getString(8));
        }

        for (int i = 0; i < 7; i++) {

            if(database_adpater.search_item(2018,6,i+1)==null){
                week_adapter.additem(0, 2018, 6, i+1, i+1, 255, 255, 255, "test");
            }else{
                graph_item_class p = database_adpater.search_item(2018,6,i+1);
                week_adapter.additem(p.getNumber(),p.getYear(),p.getMonth(),p.getDays(),p.getDay_of_the_week(),p.getNum_r(),p.getNum_g(),p.getNum_b(),p.getRgb_value());
            }
            //week 초기화
        }

            week_gridview = (GridView) findViewById(R.id.graph_calender_week_gridview);
            week_gridview.setAdapter(week_adapter);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 7; j++) {
                    if(database_adpater.search_item(2018,6,i*7+j+1)==null){
                    month_adapter.additem(0, 2018, 6, i*7+j+1, j+1, 255, 255, 255, "test");
                    //month 초기화
                        }
                        else{
                            graph_item_class p = database_adpater.search_item(2018,6,i*7+j+1);
                            month_adapter.additem(p.getNumber(),p.getYear(),p.getMonth(),p.getDays(),p.getDay_of_the_week(),p.getNum_r(),p.getNum_g(),p.getNum_b(),p.getRgb_value());
                        }
                }
            }
            month_gridview = (GridView) findViewById(R.id.graph_calender_month_gridview);
            month_gridview.setAdapter(month_adapter);


        }


    }
