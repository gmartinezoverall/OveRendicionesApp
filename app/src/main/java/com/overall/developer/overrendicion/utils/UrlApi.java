package com.overall.developer.overrendicion.utils;


import com.overall.developer.overrendicion.BuildConfig;

public class UrlApi
{
    public static String urlLogin = BuildConfig.URL_BASE + "user/login";
    public static String urlRecoveryPassword = BuildConfig.URL_BASE + "user/RecuperarClave";
    public static String urlCreateAccount = BuildConfig.URL_BASE + "user/create";
    public static String urlListPendiente = BuildConfig.URL_BASE + "liquidation/list";
    public static String urlListProvinces = BuildConfig.URL_BASE + "liquidation/provinces";
    public static String urlLiquidationUpdate = BuildConfig.URL_BASE + "liquidation/UpdateLiquidacion";
    public static String urlTiposDocumentos = BuildConfig.URL_BASE + "liquidation/TiposDoc";
    public static String urlInsertarRendicion = BuildConfig.URL_BASE + "liquidation/InsertarRendicion";
    public static String urlEliminarRendicion = BuildConfig.URL_BASE + "liquidation/EliminarRendicion";
    public static String urlListarRendicion = BuildConfig.URL_BASE + "liquidation/listarRendiciones";


}
