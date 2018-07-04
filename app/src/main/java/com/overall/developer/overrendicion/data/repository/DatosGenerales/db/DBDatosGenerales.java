package com.overall.developer.overrendicion.data.repository.DatosGenerales.db;

import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.ProvinciaBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;

import java.util.ArrayList;
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
