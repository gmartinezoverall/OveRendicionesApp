package com.overall.developer.overrendicion.data.repository.Pendiente.api;

import io.reactivex.Observable;

public interface ApiPendiente
{
    void getAllDocumentApi();
    Observable listPendienteApi(String dniUser);
    void insertProvinciaApi(String dniUser);

}
