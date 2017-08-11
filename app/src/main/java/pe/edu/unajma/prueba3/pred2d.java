package pe.edu.unajma.prueba3;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.androidplot.Plot;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * A straightforward example of using AndroidPlot to plot some data.
 */
public class pred2d extends Activity
{

    private XYPlot plot,plot1;

    public String inicio,h600,h630,h700,h730,h800,h830,h900,h930,h1000,h1030,h1100,h1130,h1200,h1230,h1300,h1330,h1400,h1430,h1500,h1530,h1600,h1630,h1700,h1730,h1800;
    public String a600,a630,a700,a730,a800,a830,a900,a930,a1000,a1030,a1100,a1130,a1200,a1230,a1300,a1330,a1400,a1430,a1500,a1530,a1600,a1630,a1700,a1730,a1800;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);

        setContentView(R.layout.pred2d);




        final TextView txthv = (TextView)findViewById(R.id.txBienvenida1);
        final TextView txttemp = (TextView)findViewById(R.id.txBienvenida2);

        final Calendar c = Calendar.getInstance();
        int anio = c.get(Calendar.YEAR); //obtenemos el año
        int mes = c.get(Calendar.MONTH); //obtenemos el mes

        mes = mes + 1;
        int dia = c.get(Calendar.DAY_OF_MONTH);
        dia=dia+1;

        txthv.setText("Predicciones de radiación UV para el día siguiente : "+dia+"-"+mes+"-"+anio);
        txttemp.setText("Predicciones de Temperatura para el día siguiente : "+dia+"-"+mes+"-"+anio);


        try
        {
            File ruta_sd = Environment.getExternalStorageDirectory();

            File f = new File(ruta_sd.getAbsolutePath(), "predictuv2d.txt");

            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(f)));


            inicio= fin.readLine();
            h600 = fin.readLine();
            h630= fin.readLine();
            h700= fin.readLine();
            h730= fin.readLine();
            h800= fin.readLine();
            h830= fin.readLine();
            h900= fin.readLine();
            h930= fin.readLine();
            h1000= fin.readLine();
            h1030= fin.readLine();
            h1100= fin.readLine();
            h1130= fin.readLine();
            h1200= fin.readLine();
            h1230= fin.readLine();
            h1300= fin.readLine();
            h1330= fin.readLine();
            h1400= fin.readLine();
            h1430= fin.readLine();
            h1500= fin.readLine();
            h1530= fin.readLine();
            h1600= fin.readLine();
            h1630= fin.readLine();
            h1700= fin.readLine();
            h1730= fin.readLine();
            h1800= fin.readLine();
            fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero sedetala.txt desde tarjeta SD");
        }



        //////////////////// fin deecarga y cargado de datos



        // initialize our XYPlot reference:
        plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);


        // Number[] series1Numbers = {4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        Number[] series1Numbers = {
                1424084400,
                1424086200,
                1424088000,
                1424089800,
                1424091600,
                1424093400,
                1424095200,
                1424097000,
                1424098800,
                1424100600,
                1424102400,
                1424104200,
                1424106000,
                1424107800,
                1424109600,
                1424111400,
                1424113200,
                1424115000,
                1424116800,
                1424118600,
                1424120400,
                1424122200,
                1424124000,
                1424125800,
                1424127600
        };
        // Number[] series2Numbers = {4, 6, 3, 8, 2, 10};
        Number[] uv={Math.rint(Float.parseFloat(h600)*10)/10,
                Math.rint(Float.parseFloat(h630)*10)/10,
                Math.rint(Float.parseFloat(h700)*10)/10,
                Math.rint(Float.parseFloat(h730)*10)/10,
                Math.rint(Float.parseFloat(h800)*10)/10,
                Math.rint(Float.parseFloat(h830)*10)/10,
                Math.rint(Float.parseFloat(h900)*10)/10,
                Math.rint(Float.parseFloat(h930)*10)/10,
                Math.rint(Float.parseFloat(h1000)*10)/10,
                Math.rint(Float.parseFloat(h1030)*10)/10,
                Math.rint(Float.parseFloat(h1100)*10)/10,
                Math.rint(Float.parseFloat(h1130)*10)/10,
                Math.rint(Float.parseFloat(h1200)*10)/10,
                Math.rint(Float.parseFloat(h1230)*10)/10,
                Math.rint(Float.parseFloat(h1300)*10)/10,
                Math.rint(Float.parseFloat(h1330)*10)/10,
                Math.rint(Float.parseFloat(h1400)*10)/10,
                Math.rint(Float.parseFloat(h1430)*10)/10,
                Math.rint(Float.parseFloat(h1500)*10)/10,
                Math.rint(Float.parseFloat(h1530)*10)/10,
                Math.rint(Float.parseFloat(h1600)*10)/10,
                Math.rint(Float.parseFloat(h1630)*10)/10,
                Math.rint(Float.parseFloat(h1700)*10)/10,
                Math.rint(Float.parseFloat(h1730)*10)/10,
                Math.rint(Float.parseFloat(h1800)*10)/10
        };


        //plot.setMarkupEnabled(true);

        plot.setBorderStyle(Plot.BorderStyle.NONE, null, null);
        plot.setPlotMargins(0, 0, 0, 0);
        plot.setPlotPadding(0, 0, 0, 0);
        plot.setGridPadding(0, 10, 5, 0);

        plot.setBackgroundColor(Color.WHITE);

        plot.getGraphWidget().getBackgroundPaint().setColor(Color.WHITE);
        plot.getGraphWidget().getGridBackgroundPaint().setColor(Color.WHITE);

        plot.getGraphWidget().getDomainLabelPaint().setColor(Color.BLACK);
        plot.getGraphWidget().getRangeLabelPaint().setColor(Color.BLACK);

        plot.getGraphWidget().getDomainOriginLabelPaint().setColor(Color.BLACK);
        plot.getGraphWidget().getDomainOriginLinePaint().setColor(Color.BLACK);
        plot.getGraphWidget().getRangeOriginLinePaint().setColor(Color.BLACK);


        // Domain
        //plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 24);
        //plot.setDomainStep(XYStepMode.SUBDIVIDE,24);
        // plot.setDomainValueFormat(new DecimalFormat("0"));
        //plot.setDomainStepValue(1);

        plot.setDomainValueFormat(new Format() {

            private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {

                // because our timestamps are in seconds and SimpleDateFormat expects milliseconds
                // we multiply our timestamp by 1000:
                long timestamp = ((Number) obj).longValue() * 1000;
                Date date = new Date(timestamp);
                return dateFormat.format(date, toAppendTo, pos);
            }

            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;

            }
        });

        //Range
        plot.setRangeBoundaries(0, 12, BoundaryMode.FIXED);
        plot.setRangeValueFormat(new DecimalFormat("0"));

        // plot.getGraphWidget().setDomainValueFormat(new DecimalFormat("0"));
        plot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 1);
        plot.setDomainStep(XYStepMode.SUBDIVIDE, 25);

        //plot.getDomainLabelWidget().setMarginBottom(400);
        //plot.getDomainLabelWidget().setHeight(800);
        //plot.getRangeLabelWidget().setHeight(800);//setSize(new SizeMetrics(15,SizeLayoutType.ABSOLUTE,100,SizeLayoutType.ABSOLUTE));

        // plot.getRangeLabelWidget().getLabelPaint().setTextSize(15);
        //plot.getDomainLabelWidget().getLabelPaint().setTextSize(15);
        //plot.getGraphWidget().setMarginBottom(50);
        // Create a couple arrays of y-values to plot:

        // Turn the above arrays into XYSeries':
        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(series1Numbers),          // SimpleXYSeries takes a List so turn our array into a List
                Arrays.asList(uv),
                "UV predecido");                             // Set the display title of the series

;

        ////////////////////////////////////////////
        PointLabelFormatter plf = new PointLabelFormatter();
        plf.getTextPaint().setTextSize(14);
        plf.getTextPaint().setColor(Color.RED);



        // Create a formatter to use for drawing a series using LineAndPointRenderer:
        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.rgb(0, 200, 0),Color.rgb(0, 100, 0),Color.CYAN,plf);
        // fill color
        // setup our line fill paint to be a slightly transparent gradient:
        Paint lineFill = new Paint();
        lineFill.setAlpha(200);

        //int[] colores={Color.rgb(204,0,102),Color.RED,Color.rgb(255,128,0),Color.YELLOW};
        lineFill.setShader(new LinearGradient(0, 0, 0, 280, Color.WHITE, Color.GREEN, Shader.TileMode.MIRROR));
        //lineFill.setShader(new LinearGradient(0, 0, 0,0, colores, null, Shader.TileMode.REPEAT));
        series1Format.setFillPaint(lineFill);

        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);

        plot.getGraphWidget().setDomainLabelOrientation(-45);
        // plot.getGraphWidget().getDomainLabelPaint().setTextSize(22);
        plot.setTicksPerRangeLabel(1);









        ////////prueba del 2do grafico
        plot1 = (XYPlot) findViewById(R.id.mySimpleXYPlot1);

        try
        {
            File ruta_sd = Environment.getExternalStorageDirectory();

            File f = new File(ruta_sd.getAbsolutePath(), "predicttemp2d.txt");

            BufferedReader fin =
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(f)));


            inicio= fin.readLine();
            h600 = fin.readLine();
            h630= fin.readLine();
            h700= fin.readLine();
            h730= fin.readLine();
            h800= fin.readLine();
            h830= fin.readLine();
            h900= fin.readLine();
            h930= fin.readLine();
            h1000= fin.readLine();
            h1030= fin.readLine();
            h1100= fin.readLine();
            h1130= fin.readLine();
            h1200= fin.readLine();
            h1230= fin.readLine();
            h1300= fin.readLine();
            h1330= fin.readLine();
            h1400= fin.readLine();
            h1430= fin.readLine();
            h1500= fin.readLine();
            h1530= fin.readLine();
            h1600= fin.readLine();
            h1630= fin.readLine();
            h1700= fin.readLine();
            h1730= fin.readLine();
            h1800= fin.readLine();
            fin.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero sedetala.txt desde tarjeta SD");
        }




        // Number[] series1Numbers = {4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        Number[] series3Numbers = {
                1422335100,
                1422338400,
                1422342000,
                1422345600,
                1422349200,
                1422352800,
                1422356400,
                1422360000,
                1422363600,
                1422367200,
                1422370800,
                1422374400,
                1422378000,
                1422381600,
                1422385200,
                1422388800,
                1422392400,
                1422396000,
                1422399600,
                1422403200,
                1422406800,
                1422410400,
                1422414000,
                1422417600,
                1422421500
        };
        // Number[] series2Numbers = {4, 6, 3, 8, 2, 10};
        Number[] temp={Math.rint(Float.parseFloat(h600)*10)/10,
                Math.rint(Float.parseFloat(h630)*10)/10,
                Math.rint(Float.parseFloat(h700)*10)/10,
                Math.rint(Float.parseFloat(h730)*10)/10,
                 Math.rint(Float.parseFloat(h800)*10)/10,
                Math.rint(Float.parseFloat(h830)*10)/10,
                Math.rint(Float.parseFloat(h900)*10)/10,
                Math.rint(Float.parseFloat(h930)*10)/10,
                Math.rint(Float.parseFloat(h1000)*10)/10,
                Math.rint(Float.parseFloat(h1030)*10)/10,
                Math.rint(Float.parseFloat(h1100)*10)/10,
                Math.rint(Float.parseFloat(h1130)*10)/10,
                Math.rint(Float.parseFloat(h1200)*10)/10,
                 Math.rint(Float.parseFloat(h1230)*10)/10,
                Math.rint(Float.parseFloat(h1300)*10)/10,
                Math.rint(Float.parseFloat(h1330)*10)/10,
                Math.rint(Float.parseFloat(h1400)*10)/10,
               Math.rint(Float.parseFloat(h1430)*10)/10,
                Math.rint(Float.parseFloat(h1500)*10)/10,
               Math.rint(Float.parseFloat(h1530)*10)/10,
                Math.rint(Float.parseFloat(h1600)*10)/10,
                Math.rint(Float.parseFloat(h1630)*10)/10,
                Math.rint(Float.parseFloat(h1700)*10)/10,
                Math.rint(Float.parseFloat(h1730)*10)/10,
                Math.rint(Float.parseFloat(h1800)*10)/10
        };



        plot1.setBorderStyle(Plot.BorderStyle.NONE, null, null);
        plot1.setPlotMargins(0, 0, 0, 0);
        plot1.setPlotPadding(0, 0, 0, 0);
        plot1.setGridPadding(0, 10, 5, 0);

        plot1.setBackgroundColor(Color.WHITE);

        plot1.getGraphWidget().getBackgroundPaint().setColor(Color.WHITE);
        plot1.getGraphWidget().getGridBackgroundPaint().setColor(Color.WHITE);

        plot1.getGraphWidget().getDomainLabelPaint().setColor(Color.BLACK);
        plot1.getGraphWidget().getRangeLabelPaint().setColor(Color.BLACK);

        plot1.getGraphWidget().getDomainOriginLabelPaint().setColor(Color.BLACK);
        plot1.getGraphWidget().getDomainOriginLinePaint().setColor(Color.BLACK);
        plot1.getGraphWidget().getRangeOriginLinePaint().setColor(Color.BLACK);


        // Domain
        //plot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, 24);
        //plot.setDomainStep(XYStepMode.SUBDIVIDE,24);
        // plot.setDomainValueFormat(new DecimalFormat("0"));
        //plot.setDomainStepValue(1);

        plot1.setDomainValueFormat(new Format() {

            private SimpleDateFormat dateFormat = new SimpleDateFormat("HH");

            @Override
            public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {

                // because our timestamps are in seconds and SimpleDateFormat expects milliseconds
                // we multiply our timestamp by 1000:
                long timestamp = ((Number) obj).longValue() * 1000;
                Date date = new Date(timestamp);
                return dateFormat.format(date, toAppendTo, pos);
            }

            @Override
            public Object parseObject(String source, ParsePosition pos) {
                return null;

            }
        });

        //Range
        plot1.setRangeBoundaries(0, 30, BoundaryMode.FIXED);
        plot1.setRangeValueFormat(new DecimalFormat("0"));

        XYSeries series3 = new SimpleXYSeries(
                Arrays.asList(series3Numbers),          // SimpleXYSeries takes a List so turn our array into a List
                Arrays.asList(temp),
                "Temp. predecida");                             // Set the display title of the series


        ////////////////////////////////////////////
        PointLabelFormatter plf3 = new PointLabelFormatter();
        plf3.getTextPaint().setTextSize(14);
        plf3.getTextPaint().setColor(Color.RED);



        // Create a formatter to use for drawing a series using LineAndPointRenderer:
        LineAndPointFormatter series3Format = new LineAndPointFormatter(Color.rgb(0, 200, 0),Color.rgb(0, 100, 0),Color.CYAN,plf3);
        // fill color
        // setup our line fill paint to be a slightly transparent gradient:
        Paint lineFill1 = new Paint();
        lineFill1.setAlpha(200);

        //int[] colores={Color.rgb(204,0,102),Color.RED,Color.rgb(255,128,0),Color.YELLOW};
        lineFill1.setShader(new LinearGradient(0, 0, 0, 280, Color.WHITE, Color.YELLOW, Shader.TileMode.MIRROR));
        //lineFill.setShader(new LinearGradient(0, 0, 0,0, colores, null, Shader.TileMode.REPEAT));
        series3Format.setFillPaint(lineFill1);
        // plot.getGraphWidget().setDomainValueFormat(new DecimalFormat("0"));
        plot1.setRangeStep(XYStepMode.INCREMENT_BY_VAL, 1);
        plot1.setDomainStep(XYStepMode.SUBDIVIDE, 25);
        plot1.addSeries(series3, series3Format);
        plot1.getGraphWidget().setDomainLabelOrientation(-45);
        // plot.getGraphWidget().getDomainLabelPaint().setTextSize(22);
        plot1.setTicksPerRangeLabel(1);


        ///////fin prueba del segundo graico

    }






}

