package com.inclus.kimdonggeun.rgb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class Main0_Start extends Fragment implements View.OnClickListener {
    ImageButton btnGo,btnNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main0_start, container, false);
        btnGo = view.findViewById(R.id.btn_go);
        btnGo.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go:
                Intent intent = new Intent(this.getContext(), PopupWindow_form.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}