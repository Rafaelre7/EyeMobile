package rafaelpimenta.studio.com.eyemobile_rafael.view.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Helper {

    public static String retornarData() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


        return sdf.format(c.getTime());
    }

    public static String retornaMoeda(int valor) {

        String v = String.valueOf(valor).trim();


        return NumberFormat.getCurrencyInstance().format(Integer.parseInt(v));
    }
}
