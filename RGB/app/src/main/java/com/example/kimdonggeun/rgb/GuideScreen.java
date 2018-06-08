package com.example.kimdonggeun.rgb;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.widget.ImageView;

public class GuideScreen extends Dialog{

    public GuideScreen(Context context, int theme) {

        super(context, theme);

    }


    ImageView guideImage;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.button_guide);

        getWindow().setLayout(android.view.WindowManager.LayoutParams.MATCH_PARENT, android.view.WindowManager.LayoutParams.MATCH_PARENT);

        guideImage = findViewById(R.id.buttonguide_image);

    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        dismiss();
        return super.onTouchEvent(event);
    }

    void setImage(int image){
        guideImage.setImageResource(image);
    }

}
