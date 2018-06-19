package com.overall.developer.overrendicion.data.repository.Rendicion;


import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;

import java.util.List;

public interface RendicionRepository
{
    List<RendicionBean> listRendicion();

    String deleteRendicionForCodDB(int position);

    void deleteRendicionForCodApi(String codCodRendicion);

    void changeStatusLiquidacionDB();

    void insertListRendicionesApi(String codLiquidacion);

    String getCodLiquidacionDB();

    void insertListRendicionInDB(List<RendicionBean> mRendionList);

    void insertListCompleted();

    LiquidacionBean getForCodLiquidacionDB(String codLiquidacion);

    UserBean getUserDB();

    void finishLoginDB();
}
