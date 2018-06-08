package com.example.kimdonggeun.rgb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;

@SuppressLint("ValidFragment")
public class PopupWindow_item extends Fragment {

    ImageButton truebutton;
    ImageButton falsebutton;

    int num_r;
    int num_g;
    int num_b;
    String question;

    ViewPager viewpager;
    @SuppressLint("ValidFragment")
    public PopupWindow_item(String string,int num_r, int num_g, int num_b) {
        question=string;
        this.num_r = num_r;
        this.num_g = num_g;
        this.num_b = num_b;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.popupwindow_item,container,false);

        TextView text= (TextView)root.findViewById(R.id.form_item_text);
        text.setText(question);
        truebutton =(ImageButton)root.findViewById(R.id.form_item_truebutton);
        falsebutton=(ImageButton)root.findViewById(R.id.form_item_falsebutton);


        truebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            Log.v("값",num_r+" "+num_g+" "+num_b);
                viewpager=((PopupWindow_form)getActivity()).viewpager;

                ((PopupWindow_form)getActivity()).addR_seekbar(num_r);

                ((PopupWindow_form)getActivity()).addB_seekbar(num_g);

                ((PopupWindow_form)getActivity()).addG_seekbar(num_b);

                if(viewpager.getCurrentItem()==viewpager.getAdapter().getCount()-1){
                    Log.d("이제 여기에","데이터가 들어갈꺼에요");
                    //일단 지저분하게 만들자.
                    graph_data_Sqliteopenhelper data_sqliteopenhelper= new graph_data_Sqliteopenhelper(getContext(), "graphdata1.db", null, 1);;
                    SQLiteDatabase db=data_sqliteopenhelper.getWritableDatabase();
                    Calendar date = Calendar.getInstance();


                    int r=((PopupWindow_form)getActivity()).R_seekbar.getProgress();
                    int g=((PopupWindow_form)getActivity()).G_seekbar.getProgress();
                    int b=((PopupWindow_form)getActivity()).B_seekbar.getProgress();


                    graph_item_class new_item=new graph_item_class(0,date.get(Calendar.YEAR),date.get((Calendar.MONTH))+1,date.get(Calendar.DAY_OF_MONTH),date.get(Calendar.DAY_OF_WEEK),r*50,g*50,b*50,"hi", false);
                    data_sqliteopenhelper.dbinsert(db,new_item);
                    Log.d("아우",new_item.getMonth()+"");

                    dialog();

                }else {
                    ((PopupWindow_form) getActivity()).next_viewpage();
                }
            }
        });

        falsebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(viewpager.getCurrentItem()==viewpager.getAdapter().getCount()-1) {
                    dialog();
                }
                else ((PopupWindow_form) getActivity()).next_viewpage();


            }
        });


        return root;

    }

    void dialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());

        // 제목셋팅
        alertDialogBuilder.setTitle("설문 제출");

        // AlertDialog 셋팅
        alertDialogBuilder
                .setMessage("오늘의 컬러를 확인해 볼까요?")
                .setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().finish();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // 다이얼로그 생성
        AlertDialog alertDialog = alertDialogBuilder.create();

        // 다이얼로그 보여주기
        alertDialog.show();
    }

}