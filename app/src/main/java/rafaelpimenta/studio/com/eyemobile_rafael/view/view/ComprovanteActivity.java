package rafaelpimenta.studio.com.eyemobile_rafael.view.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import rafaelpimenta.studio.com.eyemobile_rafael.R;
import rafaelpimenta.studio.com.eyemobile_rafael.view.util.Helper;

public class ComprovanteActivity extends AppCompatActivity implements View.OnClickListener {

    private Bundle dados;
    private Button btnConfirmar;
    private ImageView imgRecibo;
    private TextView tvDataHr, tvFormaPag, tvValor, tvTitulo;
    private ConstraintLayout constraintLayout;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprovante);

        inicializaComponentes();

        dados = getIntent().getExtras();
        getSupportActionBar().setTitle("PAGAMENTO REALIZADO COM SUCESSO");
        btnConfirmar.setOnClickListener(this);


        createCupom();
    }

    private void createCupom() {
        tvValor.setText("R$" + dados.getString("valor"));
        tvFormaPag.setText(dados.getString("pagamento"));
        tvDataHr.setText(Helper.retornarData());

        createBitmap();
    }

    private void inicializaComponentes() {
        btnConfirmar = findViewById(R.id.btnConfirmar);
        constraintLayout = findViewById(R.id.contraintComprovante);
        imgRecibo = findViewById(R.id.imgRecibo);
        tvTitulo = findViewById(R.id.tvTitulo);
        tvFormaPag = findViewById(R.id.tvPagamento);
        tvValor = findViewById(R.id.tvValor);
        tvDataHr = findViewById(R.id.tvDataHr);
    }

    public void createBitmap() {

        mCompositeDisposable.add(getBitmap(constraintLayout)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dados ->
                        imgRecibo.setImageBitmap(dados)
                ));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnConfirmar:
                finish();
                Intent intent = new Intent(this, FormaPagamentoActivity.class);
                startActivity(intent);
                break;
        }

    }

    public static Observable<Bitmap> getBitmap(View view) {
        return Observable.create(emitter -> {

            Thread.sleep(1000);
            Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(returnedBitmap);
            Drawable bgDrawable = view.getBackground();
            if (bgDrawable != null)
                bgDrawable.draw(canvas);
            else
                canvas.drawColor(Color.WHITE);
            view.draw(canvas);


            emitter.onNext(returnedBitmap);
            emitter.onComplete();
        });
    }

}
