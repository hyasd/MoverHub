package com.HouseMovers.UserApp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.bumptech.glide.Glide;
import com.HouseMovers.UserApp.R;


public class ViewPagerAdapter extends PagerAdapter {

    private android.content.Context context;
    private LayoutInflater layoutInflater;
    private String[] name;
    private int[] image;


    private ViewPagerAdapter.OnItemClickListener mListener;
    private Object Context;

    public interface OnItemClickListener {
        void onImageClick(int position);
    }

    public void setOnItemClickListener(ViewPagerAdapter.OnItemClickListener listener) {
        mListener = listener;
    }


    public ViewPagerAdapter(android.content.Context context, int[] image) {
        this.context = context;
        this.image = image;
    }

    public ViewPagerAdapter(android.content.Context context, String[] name, int[] image) {

        this.name = name;
        this.image = image;
        this.context = context;


    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_pager_custom_design, null);


        ImageView imageView = view.findViewById(R.id.imageViewClick);
//        TextView textView = view.findViewById(R.id.driver_Name);
        RelativeLayout relativeLayout = view.findViewById(R.id.real);

//        final String driverName = name[position];
////        textView.setText(driverName);

        final int img = image[position];
        Glide.with(context).load(img).into(imageView);


        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {

                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onImageClick(position);
                    }
                }
            }
        });

//        imageView.setImageResource(images[position]);

        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
