package com.overall.developer.overrendicion2.data.repository.liquidacion.DatosGenerales.db;

import com.overall.developer.overrendicion2.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion2.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion2.data.model.bean.UserBean;

import java.util.List;


public interface DBDatosGenerales
{
    LiquidacionBean getForCodLiquidacionDB(String codLiquidacion);
    List<ProvinciaBean> listProvinciaForSpinnerDB();
    UserBean getUser();
    void updateLiquidacionDB(LiquidacionBean liquidacionBean);
    boolean existsRendicionDB();
    void changeStatusLiquidacion();
    ProvinciaBean getProvinciaDB(String idProvincia);

    void finisLogin();

}
