package com.overall.developer.overrendicion.ui.liquidacion.presenter.DatosGenerales;

import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion.data.model.entity.ProvinciaEntity;

import java.util.ArrayList;
import java.util.List;

public interface DatosGeneralesPresenter
{
    //Valors de Ida
    LiquidacionEntity getForCodLiquidacion(String codLiquidacion);
    List<ProvinciaEntity> listProvinciaForSpinner();
    UserBean getUser();
    void saveData(String codLiquidacion, String fechaViatico, String motivoViaje, String ubigeoProvDestino, String fechaDesde, String fechaHasta, String tipoViatico);
    boolean existsRendicion();
    void changeStatusLiquidacion();

    //valores de vuelta
    void finisLogin();


}
