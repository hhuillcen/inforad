package pe.edu.unajma.prueba3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Herwin on 16/11/2014.
 */
public class tiempos extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indices);

        Bundle bundle = getIntent().getExtras();
        String index=bundle.getString("uv");

        TextView radiacion=(TextView)findViewById(R.id.radiacion);
        radiacion.setText("Radiación Actual :"+index);
    }
}
