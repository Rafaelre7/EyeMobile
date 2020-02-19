package rafaelpimenta.studio.com.eyemobile_rafael.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import rafaelpimenta.studio.com.eyemobile_rafael.R;
import rafaelpimenta.studio.com.eyemobile_rafael.view.view.ComprovanteActivity;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ClickPagamento clickPagamento;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.ic_money,
            R.drawable.ic_debit,
            R.drawable.ic_credit,
            R.drawable.ic_vr,
            R.drawable.ic_cupom

    };

    public String[] slide_headings = {
            "DINHEIRO",
            "DÉBITO",
            "CRÉDITO",
            "V.R",
            "CUPOM"
    };


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    public interface ClickPagamento {
        void click_pagamento();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.slide_layout, container,false);
//        if (position == 1){
//            view = layoutInflater.inflate(R.layout.slide_layout, container,false);
//        }else if (position == 2){
//             view = layoutInflater.inflate(R.layout.slide_layout, container,false);
//        }

        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImage = view.findViewById(R.id.slide_image);
        TextView tvDesc = view.findViewById(R.id.tvDesc);

        slideImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "Clicou: " + slide_headings[position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ComprovanteActivity.class);
                context.startActivity(intent);
            }
        });

        slideImage.setImageResource(slide_images[position]);
        tvDesc.setText(slide_headings[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
