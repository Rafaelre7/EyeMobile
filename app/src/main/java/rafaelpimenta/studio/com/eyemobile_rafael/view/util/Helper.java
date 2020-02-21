package rafaelpimenta.studio.com.eyemobile_rafael.view.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Helper {

    public static String retornarData() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


        return sdf.format(c.getTime());
    }
}
