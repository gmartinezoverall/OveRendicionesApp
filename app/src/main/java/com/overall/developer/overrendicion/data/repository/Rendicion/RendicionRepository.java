package com.overall.developer.overrendicion.data.repository.Rendicion;


import com.overall.developer.overrendicion.data.model.bean.RendicionBean;

import java.util.List;

public interface RendicionRepository
{
    List<RendicionBean> listRendicion();

    void deleteRendicionForCodDB(int position);

}
