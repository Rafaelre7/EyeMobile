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

public class SliderAdapter extends PagerAdapter implements View.OnClickListener {

    Context context;
    LayoutInflater layoutInflater;

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
        return 2;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.slide_image:
                Toast.makeText(context, "Clicou: " + slide_headings[0], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ComprovanteActivity.class);
                context.startActivity(intent);
                break;

            case R.id.slide_image2:
                Toast.makeText(context, "Clicou: " + slide_headings[1], Toast.LENGTH_SHORT).show();
                break;
            case R.id.slide_image3:
                Toast.makeText(context, "Clicou: " + slide_headings[2], Toast.LENGTH_SHORT).show();
                break;
            case R.id.slide_image_sec:
                Toast.makeText(context, "Clicou: " + slide_headings[3], Toast.LENGTH_SHORT).show();
                break;
            case R.id.slide_image_sec2:
                Toast.makeText(context, "Clicou: " + slide_headings[4], Toast.LENGTH_SHORT).show();
                break;
            default:
                break;


        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);


        if (position == 1) {
            view = layoutInflater.inflate(R.layout.slide_layout_sec, container, false);
            ImageView slideImage_sec1 = view.findViewById(R.id.slide_image_sec);

            TextView tvDesc_sec1 = view.findViewById(R.id.tvDesc_sec);

            ImageView slideImage_sec2 = view.findViewById(R.id.slide_image_sec2);
            TextView tvDesc_sec2 = view.findViewById(R.id.tvDesc_sec2);

            slideImage_sec1.setImageResource(slide_images[3]);
            tvDesc_sec1.setText(slide_headings[3]);
            slideImage_sec2.setImageResource(slide_images[4]);
            tvDesc_sec2.setText(slide_headings[4]);

            slideImage_sec1.setOnClickListener(this);
            slideImage_sec2.setOnClickListener(this);
        } else if (position == 0) {
            ImageView slideImage = view.findViewById(R.id.slide_image);
            TextView tvDesc = view.findViewById(R.id.tvDesc);
            ImageView slideImage2 = view.findViewById(R.id.slide_image2);
            TextView tvDesc2 = view.findViewById(R.id.tvDesc2);
            ImageView slideImage3 = view.findViewById(R.id.slide_image3);
            TextView tvDesc3 = view.findViewById(R.id.tvDesc3);

            slideImage.setImageResource(slide_images[0]);
            tvDesc.setText(slide_headings[0]);

            slideImage2.setImageResource(slide_images[1]);
            tvDesc2.setText(slide_headings[1]);

            slideImage3.setImageResource(slide_images[2]);
            tvDesc3.setText(slide_headings[2]);

            slideImage.setOnClickListener(this);
            slideImage2.setOnClickListener(this);
            slideImage3.setOnClickListener(this);
        }

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
