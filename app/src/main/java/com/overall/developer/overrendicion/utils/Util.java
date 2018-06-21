package com.overall.developer.overrendicion.utils;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.overall.developer.overrendicion.RendicionApplication;


public class Util
{
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
            case 14://VOUCHER BANCARIO
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


            default:
                valor = 00;
                break;
        }


        return valor;
    }

}
