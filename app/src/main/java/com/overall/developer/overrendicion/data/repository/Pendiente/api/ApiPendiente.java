package com.overall.developer.overrendicion.data.repository.Pendiente.api;

import io.reactivex.Observable;

public interface ApiPendiente
{
    void setAllDocumentApi();
    void setAllBancoApi();
    Observable listPendienteApi(String dniUser);
    void refreshListPendienteApi(String dniUser);
    void insertProvinciaApi(String dniUser);
    void sendResumeEmailApi(String codLiquidacion);
}
