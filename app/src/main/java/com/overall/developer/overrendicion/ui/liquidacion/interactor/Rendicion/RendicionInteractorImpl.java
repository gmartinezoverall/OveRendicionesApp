package com.overall.developer.overrendicion.ui.liquidacion.interactor.Rendicion;


import com.overall.developer.overrendicion.data.model.bean.RendicionBean;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.data.repository.Rendicion.RendicionRepository;
import com.overall.developer.overrendicion.data.repository.Rendicion.RendicionRepositoryImpl;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Rendicion.RendicionPresenter;

import java.util.ArrayList;
import java.util.List;

public class RendicionInteractorImpl implements RendicionInteractor
{
    RendicionPresenter mPresenter;
    RendicionRepository mRepository;


    public RendicionInteractorImpl(RendicionPresenter presenter)
    {
        this.mPresenter = presenter;
        mRepository = new RendicionRepositoryImpl(this);
    }

    @Override
    public List<RendicionEntity> listRendicion()
    {
        List<RendicionEntity> rendicionList= new ArrayList<>();
        for (RendicionBean bean : mRepository.listRendicion())
        {
            rendicionList.add(new RendicionEntity(bean.getIdRendicion(),bean.getCodRendicion(), bean.getRdoDescipcion(), bean.getCodLiquidacion(), bean.getIdUsuario(), bean.getNumeroDoc(),
                    bean.getBienServicio(), bean.getIgv(), bean.getAfectoIgv(), bean.getPrecioTotal(), bean.getObservacion(), bean.getFechaDocumento(),
                    bean.getFechaVencimiento(), bean.getRuc(), bean.getRazonSocial(), bean.getBcoCod(), bean.getTipoServicio(), bean.getRtgId(), bean.getOtroGasto(),
                    bean.getCodDestino(), bean.getAfectoRetencion(), bean.getCodSuspencionH(), bean.getTipoMoneda(), bean.getTipoCambio(), bean.isSend()));
        }

        return rendicionList;
    }

    @Override
    public void deleteRendicionForCod(int position)
    {
        String codCodRendicion = mRepository.deleteRendicionForCodDB(position);
        if (!codCodRendicion.equals("-")) mRepository.deleteRendicionForCodApi(codCodRendicion);;

    }

    @Override
    public void changeStatusLiquidacion()
    {
        mRepository.changeStatusLiquidacionDB();

    }


}
