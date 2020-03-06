package rafaelpimenta.studio.com.eyemobile_rafael.view.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import dmax.dialog.SpotsDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import rafaelpimenta.studio.com.eyemobile_rafael.R;
import rafaelpimenta.studio.com.eyemobile_rafael.view.adapter.SliderAdapter;
import rafaelpimenta.studio.com.eyemobile_rafael.view.util.Controlador;
import rafaelpimenta.studio.com.eyemobile_rafael.view.util.Helper;
import rafaelpimenta.studio.com.eyemobile_rafael.view.util.Mask;

public class FormaPagamentoActivity extends AppCompatActivity implements Controlador {

    @BindView(R.id.tvTotalPagar)
    EditText tvTotalPagar;
    @BindView(R.id.viewPagerSlider)
    ViewPager viewPager;
    @BindView(R.id.dotslayout)
    LinearLayout dotLayout;
    private AlertDialog dialog;

    private SliderAdapter sliderAdapter;
    private TextView[] mDots;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forma_pagamento);
        //Actionbar
        getSupportActionBar().hide();

        ButterKnife.bind(this);

        //Inicializando viewPager
        sliderAdapter = new SliderAdapter(this, this);
        viewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);

        TextWatcher valorMask = Mask.monetario(tvTotalPagar);
        tvTotalPagar.addTextChangedListener(valorMask);


        tvTotalPagar.setText(Helper.retornaMoeda(0));
        tvTotalPagar.setBackgroundColor(Color.TRANSPARENT);

    }

    @OnLongClick(R.id.btnBackSpace)
    void onLongClick() {
        tvTotalPagar.setText(Helper.retornaMoeda(0));
    }

    @OnClick(R.id.btnBackSpace)
    void apagar() {
        String texto = tvTotalPagar.getText().toString().trim();
        if (!texto.isEmpty() && !texto.contains("$0.00")) {
            texto = texto.substring(0, texto.length() - 1);
            tvTotalPagar.setText(texto);
        }
    }

    @OnClick({R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9})
    void buttonClick(Button button) {
        String texto = tvTotalPagar.getText().toString().trim();

        if (texto.contains("$0.00")) {
            tvTotalPagar.setText("");
        }

        switch (button.getId()) {

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

    public void addDotsIndicator(int position) {
        mDots = new TextView[2];
        dotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            dotLayout.addView(mDots[i]);

        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
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

    @Override
    public void clickPagamento(int position) {
        Intent intent = new Intent(this, ComprovanteActivity.class);
        switch (position) {
            case R.id.slide_image:
                intent.putExtra("valor", tvTotalPagar.getText().toString());
                intent.putExtra("pagamento", "DINHEIRO");
                startActivity(intent);
                break;

            case R.id.slide_image2:
                finish();
                intent.putExtra("valor", tvTotalPagar.getText().toString());
                intent.putExtra("pagamento", "DÉBITO");
                startActivity(intent);
                break;
            case R.id.slide_image3:
                finish();
                intent.putExtra("valor", tvTotalPagar.getText().toString());
                intent.putExtra("pagamento", "CRÉDITO");
                startActivity(intent);
                break;
            case R.id.slide_image_sec:
                finish();
                intent.putExtra("valor", tvTotalPagar.getText().toString());
                intent.putExtra("pagamento", "V.R");
                startActivity(intent);
                break;
            case R.id.slide_image_sec2:
                finish();
                intent.putExtra("valor", tvTotalPagar.getText().toString());
                intent.putExtra("pagamento", "CUPOM");
                startActivity(intent);
                break;
            default:
                break;


        }
    }
}