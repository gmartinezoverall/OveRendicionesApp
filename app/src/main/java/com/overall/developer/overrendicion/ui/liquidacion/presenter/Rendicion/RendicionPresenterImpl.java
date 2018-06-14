package com.overall.developer.overrendicion.ui.liquidacion.presenter.Rendicion;

import android.content.Context;

import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.ui.liquidacion.interactor.Rendicion.RendicionInteractor;
import com.overall.developer.overrendicion.ui.liquidacion.interactor.Rendicion.RendicionInteractorImpl;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.RendicionView;

import java.util.List;

public class RendicionPresenterImpl implements RendicionPresenter
{
    private RendicionView mView;
    private RendicionInteractor mInteractor;

    public RendicionPresenterImpl(RendicionView view, Context context)
    {
        this.mView = view;
        mInteractor = new RendicionInteractorImpl(this);
    }

    @Override
    public List<RendicionEntity> listRendicion()
    {
        return mInteractor.listRendicion();
    }

    @Override
    public void deleteRendicionForCod(int position)
    {
         mInteractor.deleteRendicionForCod(position);

    }

}
