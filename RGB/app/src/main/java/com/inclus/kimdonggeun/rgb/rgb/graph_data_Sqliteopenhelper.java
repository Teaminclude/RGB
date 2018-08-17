            package com.inclus.kimdonggeun.rgb.rgb;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;


import com.inclus.kimdonggeun.rgb.*;
import com.inclus.kimdonggeun.rgb.graph_item_class;

            public class graph_data_Sqliteopenhelper extends SQLiteOpenHelper {


                public graph_data_Sqliteopenhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
                    super(context, name, factory, version);

                    }

                public graph_data_Sqliteopenhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
                    super(context, name, factory, version, errorHandler);
                }

                @Override
                public void onCreate(SQLiteDatabase db) {

                    String TABLE_NAME = "graph_data";


                    //데이터 삭제 후
            //        try{
            //            String DROP_SQL = "drop table if exists "+TABLE_NAME;
            //            db.execSQL(DROP_SQL);
            //        }catch(Exception e){
            //            Log.e(TAG,"Exception in DROP_sql",e);
             //       }

                    String CREATE_SQL="create table if not exists "+TABLE_NAME+" ("
                            +"number integer PRIMARY KEY autoincrement, "
                            +"year integer, "
                            +"month integer, "
                            +"days integer,"
                            +"weekofdays integer,"
                            +"num_r integer,"
                            +"num_g integer,"
                            +"num_b integer,"
                            +"value text"
                            + ")";


                    //데이터 생성
                    try{
                        db.execSQL(CREATE_SQL);

                    }catch (Exception e){
                        Log.e(TAG,"Exception in CREATE_sql",e);
                    }
                    Log.d(TAG,"oncreate 잘작동");

                   // for(int i = 0;i<7; i++) {
                     //   graph_item_class test = new graph_item_class(0,2018,5,1,i,255,100,255,"hi");
                      //  dbinsert(db,test);
                        //week_adapter.additem(0, 2018, 5, 1, i, 255, 255, 255, "test");
                        //week 초기화
                    //}

                }



                @Override
                public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                }

                public void dbinsert(SQLiteDatabase db,graph_item_class item){

                    String TABLE_NAME = "graph_data";

                    int year, month,days,weekofdays,num_r,num_g,num_b;
                    String value;
                    year = item.getYear();
                    month = item.getMonth();
                    days = item.getDays();
                    weekofdays = item.getDay_of_the_week();
                    num_r=item.getNum_r();
                    num_g=item.getNum_g();
                    num_b=item.getNum_b();
                    value=item.getRgb_value();
                    try{
                        db.execSQL("insert into "+TABLE_NAME+"(year, month, days,weekofdays,num_r,num_g,num_b,value) values ('"+year+"','"+month+"','"+days+"','"+weekofdays+"','"+num_r+"','"+num_g+"','"+num_b+"','"+value+"')");
                    }catch(Exception e){
                        Log.e(TAG,"Exception in insert_db",e);
                    }
                }


            }
