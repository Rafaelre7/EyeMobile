package rafaelpimenta.studio.com.eyemobile_rafael.view.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import rafaelpimenta.studio.com.eyemobile_rafael.R;

public class ComprovanteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprovante);

        getSupportActionBar().setDisplayShowHomeEnabled(true);//mostrar o icone e não o titulo

    }
}
