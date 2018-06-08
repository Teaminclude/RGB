package com.example.kimdonggeun.rgb;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PopupWindow_viewpageradapter extends FragmentPagerAdapter {
        private int tapCount;


        public PopupWindow_viewpageradapter(FragmentManager fm){
            super(fm);
        }


        @Override
        public Fragment getItem(int position){
            switch(position){
                case 0:
                    return new PopupWindow_item("1번질문 배점 1,0,0",1,0,0);
                case 1:
                    return new PopupWindow_item("2번질문 배점 0,1,1",0,1,1);
                case 2:
                    return new PopupWindow_item("3번질문 배점 2,0,1",2,0,1);
                case 3:
                    return new PopupWindow_item("이번 질문은 이렇게하겠따.",3,3,3);

                    default:
                    return null;
            }

        }
        @Override
        public int getCount(){
            return 4;
        }



    }