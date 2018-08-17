package com.inclus.kimdonggeun.rgb.rgb;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inclus.kimdonggeun.rgb.R;
import com.inclus.kimdonggeun.rgb.graph_draw_triangle;
import com.inclus.kimdonggeun.rgb.graph_item_class;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class graph_gridview_adapter extends BaseAdapter {

    private ArrayList<com.inclus.kimdonggeun.rgb.rgb.graph_item_class>  items = new ArrayList<com.inclus.kimdonggeun.rgb.rgb.graph_item_class>();

    Context context;
    LayoutInflater inf;


    public graph_gridview_adapter() {

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            context= parent.getContext();
            inf = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.graph_calender_item, parent, false);

        }
        com.inclus.kimdonggeun.rgb.rgb.graph_item_class graph_item = items.get(position);

        //LinearLayout background = (LinearLayout) convertView.findViewById(R.id.calender_color);
        com.inclus.kimdonggeun.rgb.rgb.graph_draw_triangle background = (com.inclus.kimdonggeun.rgb.rgb.graph_draw_triangle)convertView.findViewById(R.id.calender_color);
        TextView days = (TextView)convertView.findViewById(R.id.graph_calender_date);
        if(!graph_item.isThisMonth()) days.setTextColor(Color.parseColor("#aaaaaa"));
        else days.setTextColor(Color.parseColor("#333333"));

        background.setR(graph_item.getNum_r());
        background.setG(graph_item.getNum_g());
        background.setB(graph_item.getNum_b());
        //background.setBackgroundColor(Color.rgb(graph_item.getNum_r(),graph_item.getNum_g(),graph_item.getNum_b()));
        days.setText(Integer.toString(graph_item.getDays()));



        return convertView;
    }

    public String toDayofweek(int n){
        switch (n){
            case 1:
                return "일";
            case 2:
                return "월";
            case 3:
                return "화";
            case 4:
                return "수";
            case 5:
                return "목";
            case 6:
                return "금";
            case 7:
                return "토";
            default:
                return "";
        }
    }

    public void additem(int number,int year,int month,int day,int daysofweek,int r,int g,int b,String value, boolean thisMonth){
       com.inclus.kimdonggeun.rgb.rgb.graph_item_class tem = new com.inclus.kimdonggeun.rgb.rgb.graph_item_class(number,year,month,day,daysofweek,r,g,b,value, thisMonth);
       items.add(tem);
    }
    public void clearitem(){
        items.clear();
    }

    public com.inclus.kimdonggeun.rgb.rgb.graph_item_class search_item(int year, int month, int day){
            com.inclus.kimdonggeun.rgb.rgb.graph_item_class find_item=null;
        for(com.inclus.kimdonggeun.rgb.rgb.graph_item_class p : items){
                if(year==p.getYear()&&month==p.getMonth()&&day==p.getDays())
                {
                    find_item = p;
                    break;
                }
        }
        return find_item;
    }

}
