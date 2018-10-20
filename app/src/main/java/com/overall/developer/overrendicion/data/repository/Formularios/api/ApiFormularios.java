package com.overall.developer.overrendicion.data.repository.Formularios.api;

import com.overall.developer.overrendicion.data.model.request.MovilidadInsertRequest;
import com.overall.developer.overrendicion.data.model.request.MovilidadMultipleRequest;
import com.overall.developer.overrendicion.data.model.request.MovilidadUpdateRequest;
import com.overall.developer.overrendicion.data.model.request.RendicionRequest;

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
