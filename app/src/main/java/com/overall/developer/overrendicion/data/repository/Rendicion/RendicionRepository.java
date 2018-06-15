package com.overall.developer.overrendicion.data.repository.Rendicion;


import com.overall.developer.overrendicion.data.model.bean.RendicionBean;

import java.util.List;

public interface RendicionRepository
{
    List<RendicionBean> listRendicion();

    String deleteRendicionForCodDB(int position);

    void deleteRendicionForCodApi(String codCodRendicion);

    void changeStatusLiquidacionDB();
}
