package com.overall.developer.overrendicion.ui.liquidacion.interactor.DatosGenerales;

import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion.data.model.entity.ProvinciaEntity;

import java.util.ArrayList;
import java.util.List;

public interface DatosGeneralesInteractor
{
    LiquidacionEntity getForCodLiquidacion(String codLiquidacion);
    List<ProvinciaEntity> listProvinciaForSpinner();
    UserBean getUser();
    void saveData(String codLiquidacion, String fechaViatico, String motivoViaje, String ubigeoProvDestino, String fechaDesde, String fechaHasta, String tipoViatico);
    boolean existsRendicion();
    void changeStatusLiquidacion();


    void updateSuccess(String message) ;
    void updateError(String message) ;
    void finisLogin();


}
