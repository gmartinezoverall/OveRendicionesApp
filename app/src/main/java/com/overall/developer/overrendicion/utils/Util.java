package com.overall.developer.overrendicion.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;

import com.overall.developer.overrendicion.RendicionApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import id.zelory.compressor.Compressor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class Util
{
    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy");

        return mdformat.format(calendar.getTime());

    }


    public static boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) RendicionApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static int getIdFragment(int fragmentPosition)
    {
        int valor;
        switch (fragmentPosition)
        {
            case 0://ARRENDAMIENTO
                valor = 14;
                break;
            case 1://BOLETA DE VENTA
                valor = 2;
                break;
            case 2://BOLETO AEREO
                valor = 8;
                break;
            case 3://BOLETO TERRESTRE
                valor = 9;
                break;
            case 4://CARTA DE PORTE AEREO
                valor = 21;
                break;
            case 5://DESCUENTO BOLETA
                valor = 18;
                break;
            case 6://FACTURA
                valor = 1;
                break;
            case 7://MOVILIDAD INDIVIDUAL
                valor = 10;
                break;
            case 8://NOTA DE CREDITO
                valor = 3;
                break;
            case 9://OTROS DOCUMENTOS
                valor = 13;
                break;
            case 10://RECIBO POR HONORARIOS
                valor = 5;
                break;
            case 11://RECIBO POR SERVICIOS PUBLICOS
                valor = 11;
                break;
            case 12://SIN SUSTENTO TRIBUTARIO
                valor = 12;
                break;
            case 13://TICKET MAQUINA REGISTRADORA
                valor = 15;
                break;
            case 14://VARIOS TRABAJADORES
                valor = 19;
                break;
            case 15://VOUCHER BANCARIO
                valor = 17;
                break;

            default:
                valor = 00;
                break;
        }


        return valor;
    }

    public static int getFragmentForRdoId(int rdoId)
    {
        int valor;
        switch (rdoId)
        {
            case 1://FACTURA
                valor = 6;
                break;
            case 2://BOLETA DE VENTA
                valor = 1;
                break;

            case 17://VOUCHER BANCARIO
                valor = 14;
                break;

            case 10://MOVILIDAD
                valor = 7;
                break;

            default:
                valor = 00;
                break;
        }


        return valor;
    }

    public static String SaveImage(String filepath)
    {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/overRendicion";
        File dir = new File(path);
        dir.mkdirs();

        String fileName = new SimpleDateFormat("yyyyMMddHHmmss'.jpg'").format(new Date());
        File file = new File(path,fileName);
        file.mkdirs();

        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            Bitmap bitmap = BitmapFactory.decodeFile(filepath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 95, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return file.getAbsolutePath();
    }

}
