package rafaelpimenta.studio.com.eyemobile_rafael.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import rafaelpimenta.studio.com.eyemobile_rafael.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int [] slide_images = {
            R.drawable.ic_money,
            R.drawable.ic_credit,
            R.drawable.ic_debit

    };

    public String [] slide_headings = {
            "DINHEIRO",
            "CRÉDITO",
            "DÉBITO"
    };


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//        View view = layoutInflater.inflate(R.layout.slide_layout, container,false);
//        if (position == 1){
//            view = layoutInflater.inflate(R.layout.slide_layout, container,false);
//        }else if (position == 2){
//             view = layoutInflater.inflate(R.layout.slide_layout, container,false);
//        }

        View view = layoutInflater.inflate(R.layout.slide_layout, container,false);

        ImageView slideImage = view.findViewById(R.id.slide_image);
        TextView tvDesc = view.findViewById(R.id.tvDesc);

        slideImage.setImageResource(slide_images[position]);
        tvDesc.setText(slide_headings[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
