package com.overall.developer.overrendicion.data.repository.liquidacion.DatosGenerales;

import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.request.UpdateLiquidationRequest;

import java.util.List;


public interface DatosGeneralesRepository
{
    LiquidacionBean getForCodLiquidacion(String codLiquidacion);
    List<ProvinciaBean> listProvinciaForSpinner();
    UserBean getUser();
    void updateLiquidacionDB(LiquidacionBean liquidacionBean);
    void sendUpdateLiquidacionAPI(UpdateLiquidationRequest request);
    boolean existsRendicion();
    void changeStatusLiquidacion();
    ProvinciaBean getProvinciaDB(String idProvincia);


    void updateSuccess(String message) ;
    void updateError(String message) ;
    void finisLogin();



}
