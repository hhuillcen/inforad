package pe.edu.unajma.prueba3;

import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mapa; // Might be null if Google Play services APK is not available.

    public int vista=0;
    //private final LatLng UPV = new LatLng(39.481106, -0.340987);
    private final LatLng ANDAHUAYLAS = new LatLng(-13.657194, -73.387953);
    private final LatLng CENTRO = new LatLng(-13.657561, -73.414038);
    private final LatLng TALAVERA = new LatLng(-13.650720,  -73.441544);
    public String fecha="fecha";
    public String hora="hora";
    public String temperatura="temperatura";
    public String humedad="humedad";
    public String viento="viento";
    public String uv="uv";
    public String sensor="sensor";

    public String fechat="fecha";
    public String horat="hora";
    public String temperaturat="temperatura";
    public String humedadt="humedad";
    public String vientot="viento";
    public String uvt="uv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        //////

        final Button button1 = (Button)findViewById(R.id.button1);
        final Button button2 = (Button)findViewById(R.id.button2);
        final Button button3 = (Button)findViewById(R.id.button3);
        final Button button4 = (Button)findViewById(R.id.button4);
        final Button button5 = (Button)findViewById(R.id.button5);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Intent intent = new Intent(MapsActivity.this, pred1d.class);
                // Bundle bundle = new Bundle();
                //intent.putExtras(bundle);
                startActivity(intent);
           }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                Intent intent = new Intent(MapsActivity.this, pred2d.class);
                // Bundle bundle = new Bundle();
                //intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
               geolocalizar();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {

           setUpMap();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                finish();
                System.runFinalization();
                System.exit(0);
                MapsActivity.this.finish();
            }
        });

   /*     mapa.setOnMarkerClickListener(new OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(
                        MapsActivity.this,
                        "Marcador pulsado:\n" +
                                marker.getTitle(),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });*/

        mapa.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
             /*   Toast.makeText(
                        MapsActivity.this,
                        "Detalle de marcador:\n" +
                                marker.getSnippet(),
                        Toast.LENGTH_SHORT).show();*/
                if ((marker.getTitle().equalsIgnoreCase("SENSOR ANDAHUAYLAS"))||(marker.getTitle().equalsIgnoreCase("SENSOR TALAVERA"))) {
                    Intent intent = new Intent(MapsActivity.this, GeoUv.class);
                    Bundle bundle = new Bundle();
                   // if (sensor.equalsIgnoreCase("ANDAHUAYLAS")){
                    if (marker.getTitle().equalsIgnoreCase("SENSOR ANDAHUAYLAS")){
                        bundle.putString("fecha", fecha);
                        bundle.putString("hora", hora);
                        bundle.putString("uv", uv);
                        bundle.putString("temperatura", temperatura);
                        bundle.putString("humedad", humedad);
                        bundle.putString("viento", viento);
                        bundle.putString("sensorg", "ANDAHUAYLAS");
                    }
                    else {
                        bundle.putString("fecha", fechat);
                        bundle.putString("hora", horat);
                        bundle.putString("uv", uvt);
                        bundle.putString("temperatura", temperaturat);
                        bundle.putString("humedad", humedadt);
                        bundle.putString("viento", vientot);
                        bundle.putString("sensorg", "TALAVERA");
                    }

                    intent.putExtras(bundle);
                    startActivity(intent);
                }


            }
        });
    }




    private class Descarga extends AsyncTask<String, Float, Integer> {

        protected void onPreExecute() {
         }

        protected Integer doInBackground(String... urls) {
            try {

                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                //URL url = new URL("http://www.iepsantarosa.com/uv_casa.html");
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.connect();
                File SDCardRoot = Environment.getExternalStorageDirectory();
                File file = new File(SDCardRoot,"sedeadm.txt");
                FileOutputStream fileOutput = new FileOutputStream(file);
                InputStream inputStream = urlConnection.getInputStream();
                int totalSize = urlConnection.getContentLength();
                int downloadedSize = 0;
                byte[] buffer = new byte[1024];
                int bufferLength = 0;
                while ( (bufferLength = inputStream.read(buffer)) > 0 ) {

                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                }

                fileOutput.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }

            return null;
            //////////////
        }

        protected void onProgressUpdate (Float... valores) {
        }

        protected void onPostExecute(Integer bytes) {

        }
    }


    private class Descarga1 extends AsyncTask<String, Float, Integer> {

        protected void onPreExecute() {
        }

        protected Integer doInBackground(String... urls) {
            try {

                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                //URL url = new URL("http://www.iepsantarosa.com/uv_casa.html");
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.connect();
                File SDCardRoot = Environment.getExternalStorageDirectory();
                File file = new File(SDCardRoot,"sedetala.txt");
                FileOutputStream fileOutput = new FileOutputStream(file);
                InputStream inputStream = urlConnection.getInputStream();
                int totalSize = urlConnection.getContentLength();
                int downloadedSize = 0;
                byte[] buffer = new byte[1024];
                int bufferLength = 0;
                while ( (bufferLength = inputStream.read(buffer)) > 0 ) {

                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                }

                fileOutput.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }

            return null;
            //////////////
        }

        protected void onProgressUpdate (Float... valores) {
        }

        protected void onPostExecute(Integer bytes) {

        }
    }



    private class Descarga_pred extends AsyncTask<String, Float, Integer> {

        protected void onPreExecute() {
        }

        protected Integer doInBackground(String... urls) {
            try {

                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                //URL url = new URL("http://www.iepsantarosa.com/uv_casa.html");
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.connect();
                File SDCardRoot = Environment.getExternalStorageDirectory();
                File file = new File(SDCardRoot,"predict.txt");
                FileOutputStream fileOutput = new FileOutputStream(file);
                InputStream inputStream = urlConnection.getInputStream();
                int totalSize = urlConnection.getContentLength();
                int downloadedSize = 0;
                byte[] buffer = new byte[1024];
                int bufferLength = 0;
                while ( (bufferLength = inputStream.read(buffer)) > 0 ) {

                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                }

                fileOutput.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }

            return null;
            //////////////
        }

        protected void onProgressUpdate (Float... valores) {
        }

        protected void onPostExecute(Integer bytes) {

        }
    }


    private class Descarga_pred2d extends AsyncTask<String, Float, Integer> {

        protected void onPreExecute() {
        }

        protected Integer doInBackground(String... urls) {
            try {

                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                //URL url = new URL("http://www.iepsantarosa.com/uv_casa.html");
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.connect();
                File SDCardRoot = Environment.getExternalStorageDirectory();
                File file = new File(SDCardRoot,"predictuv2d.txt");
                FileOutputStream fileOutput = new FileOutputStream(file);
                InputStream inputStream = urlConnection.getInputStream();
                int totalSize = urlConnection.getContentLength();
                int downloadedSize = 0;
                byte[] buffer = new byte[1024];
                int bufferLength = 0;
                while ( (bufferLength = inputStream.read(buffer)) > 0 ) {

                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                }

                fileOutput.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }

            return null;
            //////////////
        }

        protected void onProgressUpdate (Float... valores) {
        }

        protected void onPostExecute(Integer bytes) {

        }
    }




    private class Descarga_actual extends AsyncTask<String, Float, Integer> {

        protected void onPreExecute() {
        }

        protected Integer doInBackground(String... urls) {
            try {

                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                //URL url = new URL("http://www.iepsantarosa.com/uv_casa.html");
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.connect();
                File SDCardRoot = Environment.getExternalStorageDirectory();
                File file = new File(SDCardRoot,"uv_actual.txt");
                FileOutputStream fileOutput = new FileOutputStream(file);
                InputStream inputStream = urlConnection.getInputStream();
                int totalSize = urlConnection.getContentLength();
                int downloadedSize = 0;
                byte[] buffer = new byte[1024];
                int bufferLength = 0;
                while ( (bufferLength = inputStream.read(buffer)) > 0 ) {

                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                }

                fileOutput.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }

            return null;
            //////////////
        }

        protected void onProgressUpdate (Float... valores) {
        }

        protected void onPostExecute(Integer bytes) {

        }
    }


    private class Descarga_predtemp1d extends AsyncTask<String, Float, Integer> {

        protected void onPreExecute() {
        }

        protected Integer doInBackground(String... urls) {
            try {

                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                //URL url = new URL("http://www.iepsantarosa.com/uv_casa.html");
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.connect();
                File SDCardRoot = Environment.getExternalStorageDirectory();
                File file = new File(SDCardRoot,"predicttemp1d.txt");
                FileOutputStream fileOutput = new FileOutputStream(file);
                InputStream inputStream = urlConnection.getInputStream();
                int totalSize = urlConnection.getContentLength();
                int downloadedSize = 0;
                byte[] buffer = new byte[1024];
                int bufferLength = 0;
                while ( (bufferLength = inputStream.read(buffer)) > 0 ) {

                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                }

                fileOutput.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }

            return null;
            //////////////
        }

        protected void onProgressUpdate (Float... valores) {
        }

        protected void onPostExecute(Integer bytes) {

        }
    }


    private class Descarga_predtemp2d extends AsyncTask<String, Float, Integer> {

        protected void onPreExecute() {
        }

        protected Integer doInBackground(String... urls) {
            try {

                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                //URL url = new URL("http://www.iepsantarosa.com/uv_casa.html");
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.connect();
                File SDCardRoot = Environment.getExternalStorageDirectory();
                File file = new File(SDCardRoot,"predicttemp2d.txt");
                FileOutputStream fileOutput = new FileOutputStream(file);
                InputStream inputStream = urlConnection.getInputStream();
                int totalSize = urlConnection.getContentLength();
                int downloadedSize = 0;
                byte[] buffer = new byte[1024];
                int bufferLength = 0;
                while ( (bufferLength = inputStream.read(buffer)) > 0 ) {

                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                }

                fileOutput.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }

            return null;
            //////////////
        }

        protected void onProgressUpdate (Float... valores) {
        }

        protected void onPostExecute(Integer bytes) {

        }
    }


    private class Descarga_tempactual extends AsyncTask<String, Float, Integer> {

        protected void onPreExecute() {
        }

        protected Integer doInBackground(String... urls) {
            try {

                if (android.os.Build.VERSION.SDK_INT > 9) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                //URL url = new URL("http://www.iepsantarosa.com/uv_casa.html");
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.connect();
                File SDCardRoot = Environment.getExternalStorageDirectory();
                File file = new File(SDCardRoot,"temp_actual.txt");
                FileOutputStream fileOutput = new FileOutputStream(file);
                InputStream inputStream = urlConnection.getInputStream();
                int totalSize = urlConnection.getContentLength();
                int downloadedSize = 0;
                byte[] buffer = new byte[1024];
                int bufferLength = 0;
                while ( (bufferLength = inputStream.read(buffer)) > 0 ) {

                    fileOutput.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                }

                fileOutput.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }

            return null;
            //////////////
        }

        protected void onProgressUpdate (Float... valores) {
        }

        protected void onPostExecute(Integer bytes) {

        }
    }







    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mapa == null) {
            // Try to obtain the map from the SupportMapFragment.
            mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mapa != null) {
                setUpMap();
            }
        }
    }


    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mapa} is not null.
     */
    private void setUpMap() {
       // mapa.addMarker(new MarkerOptions().position(new LatLng(-13.656393 * 1E6, -73.389943 * 1E6)).title("Marker"));

        mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(CENTRO, 13));
        mapa.setMyLocationEnabled(false);
        mapa.getUiSettings().setZoomControlsEnabled(true);
        mapa.getUiSettings().setCompassEnabled(true);

        new Descarga().execute("http://radiacionv.unajma.edu.pe/sedeadm.html");
        new Descarga_pred().execute("http://radiacionv.unajma.edu.pe/predict.txt");
        new Descarga_pred2d().execute("http://radiacionv.unajma.edu.pe/predictuv2d.txt");
        new Descarga_actual().execute("http://radiacionv.unajma.edu.pe/uv_actual.txt");
        new Descarga_predtemp1d().execute("http://radiacionv.unajma.edu.pe/predicttemp1d.txt");
        new Descarga_predtemp2d().execute("http://radiacionv.unajma.edu.pe/predicttemp2d.txt");
        new Descarga_tempactual().execute("http://radiacionv.unajma.edu.pe/temp_actual.txt");
        try
        {
            File ruta_sd = Environment.getExternalStorageDirectory();

            File f = new File(ruta_sd.getAbsolutePath(), "sedeadm.txt");

            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(f)));

            fecha = fin.readLine();
            hora= fin.readLine();
            temperatura= fin.readLine();
            humedad= fin.readLine();
            viento= fin.readLine();
            uv= fin.readLine();

            fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero sedeadm.txt desde tarjeta SD");
        }

        new Descarga1().execute("http://radiacionv.unajma.edu.pe/sedetala.html");

        try
        {
            File ruta_sd = Environment.getExternalStorageDirectory();

            File f = new File(ruta_sd.getAbsolutePath(), "sedetala.txt");

            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(f)));

            fechat = fin.readLine();
            horat= fin.readLine();
            temperaturat= fin.readLine();
            humedadt= fin.readLine();
            vientot= fin.readLine();
            uvt= fin.readLine();

            fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero sedetala.txt desde tarjeta SD");
        }

        mapa.addMarker(new MarkerOptions()
                .position(ANDAHUAYLAS)
                .title("SENSOR ANDAHUAYLAS")
                .snippet("Rad.UV: "+uv+" ,Temp: "+temperatura+" °C, "+hora)
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.cloudysunny))

                .anchor(0.5f, 0.5f));

        mapa.addMarker(new MarkerOptions()
                .position(TALAVERA)
                .title("SENSOR TALAVERA")
                .snippet("Rad.UV: "+uvt+" ,Temp: "+temperaturat+" °C, "+horat)
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.cloudysunny))

                .anchor(0.5f, 0.5f));
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu1, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;

    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu1_vista:
                alternarVista();
                break;
            case R.id.menu1_posicionar:
                geolocalizar();
                break;
            case R.id.menu1_pred1d:
                Intent intent = new Intent(MapsActivity.this, pred1d.class);
               // Bundle bundle = new Bundle();
                //intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.menu1_salir:
                finish();
                System.runFinalization();
                System.exit(0);
                MapsActivity.this.finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }



    private void alternarVista()
    {
        vista = (vista + 1) % 4;

        switch(vista)
        {
            case 0:
                mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case 1:
                mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case 2:
                mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case 3:
                mapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
        }
    }

    private void geolocalizar()
    {
        mapa.setMyLocationEnabled(true);

        Location location = mapa.getMyLocation();
        LatLng myLocation = new LatLng(-13.657561, -73.414038);
       // LatLng myLocation = new LatLng(-13.653222, -73.432488);
        if (location != null) {
            myLocation = new LatLng(location.getLatitude(),
                    location.getLongitude());
            }
            else{

                      Toast.makeText(
                        MapsActivity.this,
                        "Lo sentimos...\n" +
                        "Probablemente no cuente con acceso a internet"+
                        "o no cuente con un dispositivo con GPS, si embargo"+
                        "lo ubicaremos en el punto medio de los sensores",
                        Toast.LENGTH_LONG).show();
            }
        if (myLocation.longitude>=-73.414038) {
            sensor = "ANDAHUAYLAS";
        }
            else{
                sensor="TALAVERA";
            }

        mapa.addMarker(new MarkerOptions()
                .position(myLocation)
                .title("Mi Ubicación")
                .snippet("Sensor Próximo: "+sensor)
               // .snippet("[VER RECOMENDACIONES]")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.marcador_google_maps))

                .anchor(0.5f, 0.5f));


        // Zoom in the Google Map

        mapa.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
        mapa.animateCamera(CameraUpdateFactory.zoomTo(13));
    }

}
