package com.inclus.kimdonggeun.rgb.rgb;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.inclus.kimdonggeun.rgb.PopupWindow_item;

public class PopupWindow_viewpageradapter extends FragmentPagerAdapter {
    private int tapCount;


    public PopupWindow_viewpageradapter(FragmentManager fm){
        super(fm);
    }


    @Override
    public Fragment getItem(int position){
        switch(position){
            case 0:
                return new com.inclus.kimdonggeun.rgb.rgb.PopupWindow_item(1,"오늘 내이야기를 다른 사람에게 잘 표현했나요?",2,0,0);
            case 1:
                return new com.inclus.kimdonggeun.rgb.rgb.PopupWindow_item(2,"오늘 평안한 마음으로 보낸 시간이 있나요?",0,0,2);
            case 2:
                return new com.inclus.kimdonggeun.rgb.rgb.PopupWindow_item(3,"같이 해야 할일 에 대해서 직장 동료 또는 친구들과 같이 잘 수행했나요?",2,2,1);
            case 3:
                return new com.inclus.kimdonggeun.rgb.rgb.PopupWindow_item(4,"오늘 하루를 계획대로 잘 실행했나요?",0,2,0);
            case 4:
                return new com.inclus.kimdonggeun.rgb.rgb.PopupWindow_item(5,"오늘 충분히 주변 사람들과 잘 지냈나요?",2,0,0);
            case 5:
                return new com.inclus.kimdonggeun.rgb.rgb.PopupWindow_item(6,"근무(공부) 시간과 휴식 시간을 유연하게 잘 나눴나요",0,1,1);
            case 6:
                return new com.inclus.kimdonggeun.rgb.rgb.PopupWindow_item(7,"실행한 일들은 만족스러웠나요?",0,2,0);
            case 7:
                return new com.inclus.kimdonggeun.rgb.rgb.PopupWindow_item(8,"오늘 식사 시간은 만족스러웠나요?",0,0,2);
            case 8:
                return new com.inclus.kimdonggeun.rgb.rgb.PopupWindow_item(9,"미래에 대한 계획을 세우거나 구체화 시켰나요?",0,2,0);
            case 9:
                return new com.inclus.kimdonggeun.rgb.rgb.PopupWindow_item(10,"오늘 가족 및 친구들과 충분히 여가시간을 보냈나요?",1,0,2);
            case 10:
                return new com.inclus.kimdonggeun.rgb.rgb.PopupWindow_item(11,"오늘 칭찬이나 웃음을 다른 사람에게 3번 이상 건넸나요?",2,0,0);
            case 11:
                return new com.inclus.kimdonggeun.rgb.rgb.PopupWindow_item(12,"오늘 잠은 6시간 주무셨나요?",0,0,2);




            default:
                return null;
        }

    }
    @Override
    public int getCount(){
        return 12;
    }



}