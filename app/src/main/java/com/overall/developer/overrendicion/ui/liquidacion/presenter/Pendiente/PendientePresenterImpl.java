package com.overall.developer.overrendicion.ui.liquidacion.presenter.Pendiente;

import android.content.Context;

import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.ui.liquidacion.interactor.Pendiente.PendienteInteractor;
import com.overall.developer.overrendicion.ui.liquidacion.interactor.Pendiente.PendienteInteractorImpl;
import com.overall.developer.overrendicion.ui.liquidacion.view.pendiente.PendienteView;

import java.util.List;

/**
 * Created by terry on 3/22/2018.
 */

public class PendientePresenterImpl implements PendientePresenter
{
    private PendienteView mView;
    private PendienteInteractor mInteractor;

    public PendientePresenterImpl(PendienteView view, Context context)
    {
        this.mView = view;
        mInteractor = new PendienteInteractorImpl(this, context);
    }

    @Override
    public UserBean getUser()
    {
        return mInteractor.getUser();
    }

    @Override
    public void finisLogin() {
        mInteractor.finisLogin();
    }

    @Override
    public void successSendResume() { mView.successSendResume();}

    @Override
    public void errorSendResume() { mView.errorSendResume();}

    @Override
    public void saveTelefono(String numTelefono) {
        mInteractor.saveTelefono(numTelefono);
    }

    @Override
    public void listPendiente(String dniUser)
    {
        mInteractor.listPendiente(dniUser);
    }

    @Override
    public void filterLiquidacion(String entidad, String texto)
    {
        mInteractor.filterLiquidacion(entidad, texto);
    }

    @Override
    public void filterLiquidacionForUser(String dniUser) {
        mInteractor.filterLiquidacionForUser(dniUser);
    }

    @Override
    public void insertProvincia(String dniUser)
    {
        mInteractor.insertProvincia(dniUser);

    }

    @Override
    public void refreshList(String dniUser)
    {
        mInteractor.refreshList(dniUser);

    }

    @Override
    public void sendResumeEmail(String codRendicion)
    {
        mInteractor.sendResumeEmail(codRendicion);
    }

    @Override
    public boolean validateRendicionisEmpy(String codLiquidacion) {
        return mInteractor.validateRendicionisEmpy(codLiquidacion);
    }

    @Override
    public void initialDefaultApis()
    {
        mInteractor.initialDefaultApis();
    }

    @Override
    public boolean checkingPhone() {
        return mInteractor.checkingPhone();
    }

    @Override
    public void successPendienteList(String message)
    {
        mView.successPendienteList(message);
    }

    @Override
    public void refreshListSuccess(List<LiquidacionBean> listPendiente)
    {
        mView.refreshListSuccess(listPendiente);
    }

    @Override
    public void errorPendienteList(String message)
    {
        mView.errorPendienteList(message);
    }

    @Override
    public void setListPendiente(List<LiquidacionBean> pendienteBean)
    {
        mView.setListPendiente(pendienteBean);

    }

    @Override
    public void setListPendienteForUser(List<LiquidacionBean> pendienteBean)
    {
        mView.setListPendienteForUser(pendienteBean);
    }

    @Override
    public void searchListPendienteResult(List<LiquidacionBean> pendienteBean)
    {
        mView.searchListPendienteResult(pendienteBean);

    }


}
