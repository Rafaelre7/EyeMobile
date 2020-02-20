package rafaelpimenta.studio.com.eyemobile_rafael.view.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import rafaelpimenta.studio.com.eyemobile_rafael.R;

public class ComprovanteActivity extends AppCompatActivity {

    private Bundle dados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprovante);

        dados = getIntent().getExtras();
        getSupportActionBar().setTitle("PAGAMENTO REALIZADO COM SUCESSO");
//        getSupportActionBar().setDisplayShowHomeEnabled(true);//mostrar o icone e não o titulo

        Toast.makeText(this, "Valor: "+dados.getString("valor")+"-" +
                dados.getString("pagamento"),Toast.LENGTH_LONG).show();
    }
}
