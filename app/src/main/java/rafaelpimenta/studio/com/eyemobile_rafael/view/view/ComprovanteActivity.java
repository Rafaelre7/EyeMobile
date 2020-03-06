package rafaelpimenta.studio.com.eyemobile_rafael.view.view;

import android.app.AlertDialog;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import rafaelpimenta.studio.com.eyemobile_rafael.R;
import rafaelpimenta.studio.com.eyemobile_rafael.view.util.Helper;

public class ComprovanteActivity extends AppCompatActivity {

    private Bundle dados;
    @BindView(R.id.imgRecibo)
    ImageView imgRecibo;
    @BindView(R.id.tvDataHr)
    TextView tvDataHr;
    @BindView(R.id.tvPagamento)
    TextView tvFormaPag;
    @BindView(R.id.tvValor)
    TextView tvValor;
    @BindView(R.id.tvTitulo)
    TextView tvTitulo;
    private AlertDialog dialog;

    private ConstraintLayout constraintLayout;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprovante);

        ButterKnife.bind(this);
        inicializaComponentes();

        dados = getIntent().getExtras();
        getSupportActionBar().setTitle("PAGAMENTO REALIZADO COM SUCESSO");

        createCupom();
    }

    private void createCupom() {
        tvValor.setText("R$" + dados.getString("valor"));
        tvFormaPag.setText(dados.getString("pagamento"));
        tvDataHr.setText(Helper.retornarData());

        dialog = new SpotsDialog.Builder()
                .setContext(this)
                .setMessage("Gerando Comprovante")
                .setCancelable(false)
                .build();
        dialog.show();

        createBitmap();
    }

    private void inicializaComponentes() {
        constraintLayout = findViewById(R.id.contraintComprovante);
    }

    public void createBitmap() {

        mCompositeDisposable.add(getBitmap(constraintLayout)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dados ->
                        imgRecibo.setImageBitmap(dados)
                ));
    }

    @OnClick(R.id.btnConfirmar)
    void onClick() {
        finish();
        Intent intent = new Intent(this, FormaPagamentoActivity.class);
        startActivity(intent);
    }


    public Observable<Bitmap> getBitmap(View view) {
        return Observable.create(emitter -> {

            Thread.sleep(2000);
            Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(returnedBitmap);
            Drawable bgDrawable = view.getBackground();
            if (bgDrawable != null)
                bgDrawable.draw(canvas);
            else
                canvas.drawColor(Color.WHITE);
            view.draw(canvas);

            dialog.dismiss();
            emitter.onNext(returnedBitmap);
            emitter.onComplete();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
