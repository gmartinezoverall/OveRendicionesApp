package com.overall.developer.overrendicion.data.repository.Rendicion.db;

import com.overall.developer.overrendicion.data.model.bean.RendicionBean;

import java.util.List;

public interface DBRendicion
{
    List<RendicionBean> listRendicion();

    String deleteRendicionForCodDB(int position);

    void changeStatusLiquidacion();
}
