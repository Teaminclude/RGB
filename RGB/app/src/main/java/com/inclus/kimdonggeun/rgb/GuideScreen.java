package com.inclus.kimdonggeun.rgb;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;

public class GuideScreen extends Dialog{

    public GuideScreen(Context context, int theme) {

        super(context, theme);

    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().setLayout(android.view.WindowManager.LayoutParams.MATCH_PARENT, android.view.WindowManager.LayoutParams.MATCH_PARENT);

    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        dismiss();
        return super.onTouchEvent(event);
    }

    void setGuidePage(int pageNum){
        switch (pageNum){
            case 0:
                setContentView(R.layout.buttonguide0);
                break;
            case 1:
                setContentView(R.layout.buttonguide1);
                break;
            default:
                break;
        }
    }

}
