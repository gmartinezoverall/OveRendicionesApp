package com.overall.developer.overrendicion.data.repository.liquidacion.Rendicion.api;

public interface ApiRendicion
{
    void deleteRendicionForCodApi(String codCodRendicion);

    void insertListRendicionesApi(String codLiquidacion);

    void insertListMovilidadApi(String codLiquidacionDB);

    void deleteDetMovForCodApi(String idDetMov);

    void sendDataPhoteApi(String codRendicion, String pathImage);
}
