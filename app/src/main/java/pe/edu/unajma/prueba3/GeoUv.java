package pe.edu.unajma.prueba3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * Created by Herwin on 10/11/2014.
 */
public class GeoUv extends Activity {
    public String hh;
    public String index1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geouv);


        Bundle bundle = getIntent().getExtras();
        final TextView txtcab = (TextView)findViewById(R.id.txBienvenida);
       final TextView txtTexto1 = (TextView)findViewById(R.id.txPerfil);
       final TextView txtubic = (TextView)findViewById(R.id.txubic);
        final TextView txttemp = (TextView)findViewById(R.id.txttemp);
        final TextView txthv = (TextView)findViewById(R.id.txthv);
        final ImageView img= (ImageView)findViewById(R.id.ImgReco);
        final Button button= (Button)findViewById(R.id.button);

        //img.setImageResource(R.drawable.reco1);
        txtcab.setText("RADIACIÓN UV-B: SENSOR "+bundle.getString("sensorg"));
        txtubic.setText("Datos actualizados al día: "+bundle.getString("fecha")+ " a horas: "+bundle.getString("hora"));
        //txtubic1.setText("referenciado a su sensor más cercano: "+bundle.getString("sensor"));
        txtTexto1.setText("Radiación Actual: " + bundle.getString("uv"));
        txttemp.setText("Temperatura Actual: "+bundle.getString("temperatura")+ " °C");
        txthv.setText("Humedad Actual: "+bundle.getString("humedad")+ "         Velocidad del Viento: "+bundle.getString("viento"));

        SeekBar seekBar = (SeekBar)findViewById(R.id.seekBar);
        index1=bundle.getString("uv");
        String index=bundle.getString("uv");
        index=index.substring(0,index.indexOf("."));

        int idx=Integer.parseInt(index);

    //    Toast.makeText(
      //          GeoUv.this,"idx= "+idx,
        //        Toast.LENGTH_LONG).show();

        if (idx<=0){
            seekBar.setProgress(0);
        }
        else{
            seekBar.setProgress(idx-1);
        }

        if (idx<=2){
            img.setImageResource(R.drawable.reco1);
        }
        if ((idx>2)&&(idx<=5)){
            img.setImageResource(R.drawable.reco2);
        }
        if ((idx>5)&&(idx<=7)){
            img.setImageResource(R.drawable.reco3);
        }
        if ((idx>7)&&(idx<=10)){
            img.setImageResource(R.drawable.reco4);
        }
        if (idx>=11){
            img.setImageResource(R.drawable.reco5);
        }





        seekBar.setEnabled(false);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Intent intent = new Intent(GeoUv.this, tiempos.class);


                Bundle bundle = new Bundle();

                    bundle.putString("uv", index1);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
}