package com.overall.developer.overrendicion.ui.liquidacion.interactor.Rendicion;



import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.LiquidacionEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;

import java.util.List;

public interface RendicionInteractor
{
    void listRendicion();

    void deleteRendicionForCod(int position);

    void changeStatusLiquidacion();

    void updateListRendicion(List<RendicionBean> rendicionBeans);

    LiquidacionEntity getForCodLiquidacion(String codLiquidacion);

    UserBean getUser();

    void finishLogin();
}
