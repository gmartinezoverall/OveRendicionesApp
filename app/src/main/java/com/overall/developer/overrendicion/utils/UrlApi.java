package com.overall.developer.overrendicion.utils;


import com.overall.developer.overrendicion.BuildConfig;

public class UrlApi
{
    public static String urlLogin = BuildConfig.URL_BASE + "user/login";
    public static String urlRecoveryPassword = BuildConfig.URL_BASE + "user/RecuperarClave";
    public static String urlUpdatePassword = BuildConfig.URL_BASE + "user/ActualizarContrasenia";
    public static String urlUpdateEmail = BuildConfig.URL_BASE + "user/ActualizarCorreo";
    public static String urlCreateAccount = BuildConfig.URL_BASE + "user/create";
    public static String urlListPendiente = BuildConfig.URL_BASE + "liquidation/list";
    public static String urlListProvinces = BuildConfig.URL_BASE + "liquidation/provinces";
    public static String urlLiquidationUpdate = BuildConfig.URL_BASE + "liquidation/UpdateLiquidacion";
    public static String urlTiposDocumentos = BuildConfig.URL_BASE + "liquidation/TiposDoc";
    public static String urlInsertarRendicion = BuildConfig.URL_BASE + "liquidation/InsertarRendicion";
    public static String urlEliminarRendicion = BuildConfig.URL_BASE + "liquidation/EliminarRendicion";
    public static String urlListarRendicion = BuildConfig.URL_BASE + "liquidation/listarRendiciones";
    public static String urlEditarRendicion = BuildConfig.URL_BASE + "liquidation/EditarRendicion";
    public static String urlListarBancos = BuildConfig.URL_BASE + "liquidation/Bancos";
    public static String urlListarMovilidad = BuildConfig.URL_BASE + "liquidation/ListarGastosMovilidad";
    public static String urlEliminarGastoMovilidad = BuildConfig.URL_BASE + "liquidation/EliminarGastoMovilidad";
    public static String urlInsertarGastoMovilidad = BuildConfig.URL_BASE + "liquidation/InsertarGastosMovilidad";
    public static String urlUpdateGastoMovilidad = BuildConfig.URL_BASE + "liquidation/EditarGastosMovilidad";
    public static String urlUpdateFotoRendicion = BuildConfig.URL_BASE + "liquidation/ActualizarFotoRendicion";
    public static String urlInsertarGastoMovilidadMultiple = BuildConfig.URL_BASE + "liquidation/InsertarGastosMovilidadMultiple";
    public static String urlWSRuc = "http://200.60.6.20:8081/ruc/consulta/{ruc}";



}
