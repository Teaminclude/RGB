package com.inclus.kimdonggeun.rgb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Guide_PagerAdapter extends PagerAdapter {
    private int[] images;
    private LayoutInflater inflater;
    private Context context;

    public int position;

    public  Guide_PagerAdapter(Context context, int[] images){
        this.context = context;
        this.images = images;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.position = position;

        View view = inflater.inflate(R.layout.guide_item, container, false);
        ImageView imageView = view.findViewById(R.id.img_guide);
        imageView.setImageResource(images[position]);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.invalidate();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return  view == object;
    }
}
