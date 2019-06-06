package com.overall.developer.overrendicion2.data.repository.liquidacion.Formularios.api;

import com.overall.developer.overrendicion2.data.model.request.MovilidadInsertRequest;
import com.overall.developer.overrendicion2.data.model.request.MovilidadMultipleRequest;
import com.overall.developer.overrendicion2.data.model.request.MovilidadUpdateRequest;
import com.overall.developer.overrendicion2.data.model.request.RendicionRequest;

public interface ApiFormularios
{
    void sendDataForInsertApi(RendicionRequest request, Integer idRendicion);

    void sendDataForUpdateApi(RendicionRequest request, Integer idRendicion);

    void sendDataInsertMovilidadApi(MovilidadInsertRequest movilidadRequest);

    void sendDataUpdateMovilidadApi(MovilidadUpdateRequest updateRequest);

    void searchRucApi(String ruc);

    void sendDataInsertMovilidadMultipleApi(MovilidadMultipleRequest movilidadMultipleRequest);

    void setTipoCambioApi(String fecha);

}
