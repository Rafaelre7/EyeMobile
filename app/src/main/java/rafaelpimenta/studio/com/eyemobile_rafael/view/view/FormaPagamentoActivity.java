package rafaelpimenta.studio.com.eyemobile_rafael.view.view;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.text.DecimalFormat;

import rafaelpimenta.studio.com.eyemobile_rafael.R;
import rafaelpimenta.studio.com.eyemobile_rafael.view.adapter.SliderAdapter;

public class FormaPagamentoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    private ImageButton btnBackSpace;
    private TextView tvTotalPagar;
    DecimalFormat df = new DecimalFormat("#,##");
    private ViewPager viewPager;
    private LinearLayout dotLayout;
    private SliderAdapter sliderAdapter;


    private TextView[] mDots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forma_pagamento);

        inicializarComponentes();
        btnBackSpace.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        sliderAdapter = new SliderAdapter(this);

        viewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        viewPager.addOnPageChangeListener(viewListener);

    }


    private void inicializarComponentes() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        tvTotalPagar = findViewById(R.id.tvTotalPagar);
        btnBackSpace = findViewById(R.id.btnBackSpace);
        viewPager = findViewById(R.id.viewPagerSlider);
        dotLayout = findViewById(R.id.dotslayout);
    }


    @Override
    public void onClick(View view) {
        String texto = tvTotalPagar.getText().toString();
        switch (view.getId()) {
            case R.id.btnBackSpace:

                if (!texto.isEmpty()) {
                    texto = texto.substring(0, texto.length() - 1);
                }
                tvTotalPagar.setText(texto);

//                tvTotalPagar.setText(String.format("$%.2f"+texto));

                break;
            case R.id.btn0:
                tvTotalPagar.append("0");
                break;
            case R.id.btn1:
                tvTotalPagar.append("1");
                break;
            case R.id.btn2:
                tvTotalPagar.append("2");
                break;
            case R.id.btn3:
                tvTotalPagar.append("3");
                break;
            case R.id.btn4:
                tvTotalPagar.append("4");
                break;
            case R.id.btn5:
                tvTotalPagar.append("5");
                break;
            case R.id.btn6:
                tvTotalPagar.append("6");
                break;
            case R.id.btn7:
                tvTotalPagar.append("7");
                break;
            case R.id.btn8:
                tvTotalPagar.append("8");
                break;
            case R.id.btn9:
                tvTotalPagar.append("9");
                break;
            default:
                break;

        }
    }

    public String formatString(String s) {
        String givenstring = s;
        Long longval;

        longval = Long.parseLong(givenstring);
        DecimalFormat formatter = new DecimalFormat("#,##");
        String formattedString = formatter.format(longval);
        return formattedString;
    }

    public void addDotsIndicator(int position) {
        mDots = new TextView[3];
        dotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            dotLayout.addView(mDots[i]);

        }

        if (mDots.length > 0){
           mDots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
