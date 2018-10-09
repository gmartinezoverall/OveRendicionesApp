package com.overall.developer.overrendicion.ui.liquidacion.interactor.Pendiente;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.repository.Pendiente.PendienteRepository;
import com.overall.developer.overrendicion.data.repository.Pendiente.PendienteRepositoryImpl;
import com.overall.developer.overrendicion.ui.liquidacion.presenter.Pendiente.PendientePresenter;
import com.overall.developer.overrendicion.ui.user.view.Login.LoginActivity;
import com.overall.developer.overrendicion.utils.Util;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static maes.tech.intentanim.CustomIntent.customType;


/**
 * Created by terry on 3/22/2018.
 */

public class PendienteInteractorImpl implements PendienteInteractor
{
    private PendientePresenter mPresenter;
    private PendienteRepository mRepository;
    private Context mContext;

    public PendienteInteractorImpl(PendientePresenter presenter, Context context)
    {
        this.mPresenter = presenter;
        mRepository = new PendienteRepositoryImpl(this);
        mContext = context;
    }


    @Override
    public UserBean getUser()
    {
        return mRepository.getUser();
    }

    @Override
    public void finisLogin()
    {
        mRepository.finisLogin();
        mContext.startActivity(new Intent(mContext, LoginActivity.class));
        customType(mContext, "fadein-to-fadeout");
    }

    //region Interfaces
    @Override
    public void listPendiente(String dniUser)
    {
        if (Util.isOnline()) mRepository.listPendienteApi(dniUser);
        else listUserForDNI(dniUser);

    }

    @Override
    public void filterLiquidacion(String entidad, String texto)
    {
        if (entidad.equals("Codigo"))       entidad = "codLiquidacion";
        if (entidad.equals("DNI"))          entidad = "dni";
        if (entidad.equals("Estado"))       entidad = "estado";
        if (entidad.equals("Tipo"))         entidad = "tipoLiquidacion";
        if (entidad.equals("Descripcion"))  entidad = "descripcionLiquidacion";
        if (entidad.equals("Nombre"))       entidad = "nombre";
        if (entidad.equals("Monto"))        entidad = "monto";

        List<LiquidacionBean> mBeanList = mRepository.listPendienteDB(entidad, texto);
        mPresenter.searchListPendienteResult(mBeanList);

    }

    @Override
    public void filterLiquidacionForUser(String dniUser)
    {
        listUserForDNI(dniUser);
    }

    @Override
    public void insertProvincia(String dniUser)
    {
        mRepository.insertProvinciaApi(dniUser);
    }

    @Override
    public void refreshList(String dniUser)
    {
        if (Util.isOnline()) mRepository.refreshListPendienteApi(dniUser);
        else listUserForDNI(dniUser);

    }

    @Override
    public void sendResumeEmail(String codRendicion)
    {

        if (Util.isOnline())mRepository.sendResumeEmailApi(getUser().getNumDocBeneficiario(), codRendicion);
    }

    @Override
    public boolean validateRendicionisEmpy(String codLiquidacion) {
        return mRepository.validateRendicionisEmpyDB(codLiquidacion);
    }

    @Override
    public void initialDefaultApis()
    {
        if (mRepository.setAllDocumentDB() == 0) mRepository.setAllDocumentApi();//sets Provincias por Api
        else Log.i("LogNDa","La tabla documentos esta llena o tiene al menos un valor");

        if (mRepository.setAllBancoDB() == 0) mRepository.setAllBancoApi();//sets Provincias por Api
        else Log.i("LogNDa","La tabla banco esta llena o tiene al menos un valor");

        mRepository.setRmvApi();//set Sueldo minimo

    }


    @Override
    public void successPendienteList(String message, String dniUser)
    {
        mPresenter.successPendienteList(message);
        listUserForDNI(dniUser);

    }


    @Override
    public void errorPendienteList(String message)
    {
        mPresenter.errorPendienteList(message);

    }

    @Override
    public void refreshListSuccess(String dniUser)
    {
        //se Lista los Obj de la DB
        List<LiquidacionBean> mBeanList = mRepository.listPendienteDB();

        //se envian
        Observable.fromArray(mBeanList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMapIterable((Function<List<LiquidacionBean>, List<LiquidacionBean>>) beanList -> beanList)
                .filter(pendienteBean -> pendienteBean.getDni().equals(dniUser))
                .toList()
                .subscribe(listPendiente -> mPresenter.refreshListSuccess(listPendiente));

    }

    @Override
    public void successSendResume()
    {
        Toast.makeText(mContext, String.valueOf(mContext.getResources().getString(R.string.sendEmailSuccess)),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorSendResume()
    {
        Toast.makeText(mContext, String.valueOf(mContext.getResources().getString(R.string.sendEmailError)),Toast.LENGTH_SHORT).show();
    }

    private void listUserForDNI(String dniUser)
    {
        //se Lista los Obj de la DB
        List<LiquidacionBean> mBeanList = mRepository.listPendienteDB();

        //se envian
        Observable.fromArray(mBeanList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMapIterable((Function<List<LiquidacionBean>, List<LiquidacionBean>>) beanList -> beanList)
                .filter(pendienteBean -> pendienteBean.getDni().equals(dniUser))
                .toList()
                .subscribe(listPendiente -> mPresenter.setListPendiente(listPendiente));

    }



    //endregion


}
