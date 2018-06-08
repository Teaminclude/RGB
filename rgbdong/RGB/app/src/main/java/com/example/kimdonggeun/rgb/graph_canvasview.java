package com.example.kimdonggeun.rgb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

public class graph_canvasview extends View {


    public graph_canvasview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        graph_data_Sqliteopenhelper data_sqliteopenhelper = new graph_data_Sqliteopenhelper(getContext(), "graphdata1.db", null, 1);
        SQLiteDatabase db = data_sqliteopenhelper.getReadableDatabase();
        graph_gridview_adapter database_adpater = new graph_gridview_adapter();

        String SQL = "select * from " + "graph_data";
        Cursor c1 = db.rawQuery(SQL, null);

        for (int i = 0; i < c1.getCount(); i++) {
            c1.moveToNext();
            database_adpater.additem(c1.getInt(0), c1.getInt(1), c1.getInt(2), c1.getInt(3), c1.getInt(4), c1.getInt(5), c1.getInt(6), c1.getInt(7), c1.getString(8));
        }
        graph_item_class p = null;
        Calendar date = Calendar.getInstance();
        if(database_adpater.search_item(date.get(Calendar.YEAR),date.get(Calendar.MONTH)+1,date.get(Calendar.DAY_OF_MONTH))!=null){
            p = database_adpater.search_item(date.get(Calendar.YEAR),date.get(Calendar.MONTH)+1,date.get(Calendar.DAY_OF_MONTH));
        }


        super.onDraw(canvas);

        int num_R,num_B,num_G;
        num_R=num_B=num_G=0;
        if(p!=null) {
            num_R = p.getNum_r() ;
            num_G = p.getNum_g() ; //왼쪽 모서리
            num_B = p.getNum_b() ; //오른쪽 모서리
        }
            int centerX = this.getWidth()/2;
        int centerY = this.getHeight()/2;

        Paint mPaint = new Paint();
        if(p!=null)mPaint.setColor(Color.rgb(p.getNum_r(),p.getNum_g(),p.getNum_b()));
        else mPaint.setColor(Color.rgb(0,0,0));
        mPaint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        path.moveTo(centerX , centerY-num_R); //위쪽 모서리
        path.lineTo((float) (centerX+Math.sqrt(num_B*num_B/2)), (float) (centerY + Math.sqrt(num_B*num_B/2)));//오른쪽 모서리
        path.lineTo((float) (centerX -Math.sqrt(num_G*num_G/2)), (float) (centerY + Math.sqrt(num_G*num_G/2))); //왼쪽 모서리
        path.lineTo(centerX , centerY-num_R);//MoveTo랑 경로가 같아야됨

        path.close();

        canvas.drawPath(path,mPaint);
    }
}
