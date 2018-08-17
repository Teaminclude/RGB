package com.inclus.kimdonggeun.rgb.rgb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.inclus.kimdonggeun.rgb.R;
import com.inclus.kimdonggeun.rgb.graph_data_Sqliteopenhelper;
import com.inclus.kimdonggeun.rgb.graph_gridview_adapter;
import com.inclus.kimdonggeun.rgb.graph_item_class;

import java.util.Calendar;


public class GraphActivity extends AppCompatActivity implements View.OnClickListener{

    SQLiteDatabase db;
    graph_data_Sqliteopenhelper data_sqliteopenhelper;

    ImageButton btn_exit;
    ImageButton btn_lastWeek, btn_nextWeek, btn_lastMonth, btn_nextMonth,
                btn_lastWeek2, btn_nextWeek2, btn_lastMonth2, btn_nextMonth2;

    ImageButton btn_delete_database;

    TextView weekTitle, monthTitle;

    GridView week_gridview;
    GridView month_gridview;
    graph_gridview_adapter week_adapter = new graph_gridview_adapter();
    graph_gridview_adapter database_adpater = new graph_gridview_adapter();
    graph_gridview_adapter month_adapter = new graph_gridview_adapter();
    int today_year, today_month, today_day, today_dayOfWeek;
    Calendar today;

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

        btn_lastWeek = findViewById(R.id.graph_calender_lastweek);
        btn_lastWeek2 = findViewById(R.id.graph_calender_lastweek2);//지난주
        btn_lastWeek.setOnClickListener(this);
        btn_lastWeek2.setOnClickListener(this);
        btn_nextWeek = findViewById(R.id.graph_calender_nextweek);
        btn_nextWeek2 = findViewById(R.id.graph_calender_nextweek2);//다음주
        btn_nextWeek.setOnClickListener(this);
        btn_nextWeek2.setOnClickListener(this);

        btn_lastMonth = findViewById(R.id.graph_calender_lastmonth);
        btn_lastMonth2 = findViewById(R.id.graph_calender_lastmonth2);//이전달
        btn_lastMonth.setOnClickListener(this);
        btn_lastMonth2.setOnClickListener(this);
        btn_nextMonth = findViewById(R.id.graph_calender_nextmonth);
        btn_nextMonth2 = findViewById(R.id.graph_calender_nextmonth2);//다음달
        btn_nextMonth.setOnClickListener(this);
        btn_nextMonth2.setOnClickListener(this);

        btn_delete_database=findViewById(R.id.graph_calender_delete_btn);

        btn_delete_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    db.execSQL("drop table if exists graph_data");
            }
        });


//        db=data_sqliteopenhelper.getReadableDatabase();
            String SQL = "select * from " + "graph_data";
            Cursor c1 = db.rawQuery(SQL, null);

        for (int i = 0; i < c1.getCount(); i++) {
            c1.moveToNext();
            database_adpater.additem(c1.getInt(0), c1.getInt(1), c1.getInt(2), c1.getInt(3), c1.getInt(4), c1.getInt(5), c1.getInt(6), c1.getInt(7), c1.getString(8),false);
        }


        today = Calendar.getInstance();
        today_year = today.get(Calendar.YEAR);
        today_month = today.get(Calendar.MONTH)+1;
        today_day = today.get(Calendar.DAY_OF_MONTH);
        today_dayOfWeek = today.get(Calendar.DAY_OF_WEEK);


        weekTitle = findViewById(R.id.graph_calender_current_week);
        monthTitle = findViewById(R.id.graph_calender_current_month);

        weekTitle.setText(String.valueOf(today_month)+"월 "+weekName(today.get(Calendar.WEEK_OF_MONTH)));
        monthTitle.setText(String.valueOf(today_month)+"월");

        week_gridview = (GridView) findViewById(R.id.graph_calender_week_gridview);
        findWeek(today_year, today_month, today_day, today_dayOfWeek);
        week_gridview.setAdapter(week_adapter);

        month_gridview = (GridView) findViewById(R.id.graph_calender_month_gridview);
        findMonth(today_year, today_month);
        month_gridview.setAdapter(month_adapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.graph_calender_lastweek:
            case R.id.graph_calender_lastweek2:
                week_adapter.clearitem();
                settingCal(Calendar.DAY_OF_MONTH, -7);

                weekTitle.setText(String.valueOf(today_month)+"월 "+weekName(today.get(Calendar.WEEK_OF_MONTH)));
                findWeek(today_year, today_month, today_day, today_dayOfWeek);
                week_gridview.setAdapter(week_adapter);

                break;

            case R.id.graph_calender_nextweek:
            case R.id.graph_calender_nextweek2:
                week_adapter.clearitem();
                settingCal(Calendar.DAY_OF_MONTH, 7);

                weekTitle.setText(String.valueOf(today_month)+"월 "+weekName(today.get(Calendar.WEEK_OF_MONTH)));
                findWeek(today_year, today_month, today_day, today_dayOfWeek);
                week_gridview.setAdapter(week_adapter);

                break;
            case R.id.graph_calender_lastmonth:
            case R.id.graph_calender_lastmonth2:
                month_adapter.clearitem();
                settingCal(Calendar.MONTH, -1);

                monthTitle.setText(String.valueOf(today_month)+"월");
                findMonth(today_year, today_month);
                month_gridview.setAdapter(month_adapter);
                break;

            case R.id.graph_calender_nextmonth:
            case R.id.graph_calender_nextmonth2:
                month_adapter.clearitem();
                settingCal(Calendar.MONTH, 1);

                monthTitle.setText(String.valueOf(today_month)+"월");
                findMonth(today_year, today_month);
                month_gridview.setAdapter(month_adapter);
                break;

            default:
                break;
        }
    }

    String weekName(int weekNum){
        switch (weekNum){
            case 1:
                return "첫째주";
            case 2:
                return "둘째주";
            case 3:
                return "셋째주";
            case 4:
                return "넷째주";
            case 5:
                return "다섯째주";
            default:
                return null;
        }
    }

    void settingCal(int mode, int move){
        today.add(mode, move);

        today_year = today.get(Calendar.YEAR);
        today_month = today.get(Calendar.MONTH)+1;
        today_day = today.get(Calendar.DAY_OF_MONTH);
        today_dayOfWeek = today.get(Calendar.DAY_OF_WEEK);
    }

    public void findWeek(int year, int month, int day, int dayOfWeek){

        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, day);
        cal.add(Calendar.DAY_OF_MONTH, -(dayOfWeek-1));
        int setYear = cal.get(Calendar.YEAR);
        int setMonth = cal.get(Calendar.MONTH)+1;
        int setDate = cal.get(Calendar.DATE);
        int setDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        boolean thisMonth=false;

        for (int i = 0; i < 7; i++) {
            if(setMonth == month) thisMonth = true;
            else thisMonth = false;


            if(database_adpater.search_item(setYear,setMonth,setDate)==null){
                week_adapter.additem(0, setYear, setMonth, setDate, setDayOfWeek, 255, 255, 255, "test", thisMonth);
            }else{
                graph_item_class p = database_adpater.search_item(setYear,setMonth,setDate);
                week_adapter.additem(p.getNumber(),p.getYear(),p.getMonth(),p.getDays(),p.getDay_of_the_week(),p.getNum_r(),p.getNum_g(),p.getNum_b(),p.getRgb_value(),thisMonth);
            }
            //week 초기화

            cal.add(Calendar.DAY_OF_MONTH, 1);
            setYear = cal.get(Calendar.YEAR);
            setMonth = cal.get(Calendar.MONTH)+1;
            setDate = cal.get(Calendar.DATE);
            setDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        }
    }
    public void findMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();

        cal.set(year, month-1, 1); //월은 -1해줘야 해당월로 인식

        int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        cal.add(Calendar.DAY_OF_MONTH, -(firstDayOfWeek-1));
        int setYear = cal.get(Calendar.YEAR);
        int setMonth = cal.get(Calendar.MONTH)+1;
        int setDate = cal.get(Calendar.DATE);
        int setDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        boolean thisMonth=true;

        for (int i = 0; i < 7*5; i++) {
            if(setMonth == month) thisMonth = true;
            else thisMonth = false;

            if(database_adpater.search_item(setYear,setMonth,setDate)==null){
                month_adapter.additem(0, setYear, setMonth, setDate, setDayOfWeek, 255, 255, 255, "test", thisMonth);
                //month 초기화
            }
            else{
                graph_item_class p = database_adpater.search_item(setYear,setMonth,setDate);
                month_adapter.additem(p.getNumber(),p.getYear(),p.getMonth(),p.getDays(),p.getDay_of_the_week(),p.getNum_r(),p.getNum_g(),p.getNum_b(),p.getRgb_value(), thisMonth);
            }

            cal.add(Calendar.DAY_OF_MONTH, 1);
            setYear = cal.get(Calendar.YEAR);
            setMonth = cal.get(Calendar.MONTH)+1;
            setDate = cal.get(Calendar.DATE);
            setDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        }
    }

}
