package com.overall.developer.overrendicion.data.repository.Login.api;

import android.util.Log;

import com.androidnetworking.common.Priority;
import com.google.gson.Gson;

import com.overall.developer.overrendicion.BuildConfig;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.RendicionApplication;
import com.overall.developer.overrendicion.data.model.bean.UserBean;
import com.overall.developer.overrendicion.data.model.entity.UserEntity;
import com.overall.developer.overrendicion.data.repository.Login.LoginRepository;
import com.overall.developer.overrendicion.utils.UrlApi;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ApiLoginUserImpl implements ApiLoginUser
{
    private LoginRepository mRepository;
    UserBean userBean = new UserBean();
    Gson gson = new Gson();

    public ApiLoginUserImpl(LoginRepository loginRepository)
    {
        this.mRepository = loginRepository;

    }

    @Override
    public void validateUserApi(UserEntity userEntity)
    {
        loginUser(userEntity);
    }

    @Override
    public void loginRecovery(String dniUser)
    {
        Rx2AndroidNetworking.post(UrlApi.urlRecoveryPassword)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("dni", dniUser)
                .setPriority(Priority.HIGH)
                .build()
                .getJSONObjectObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JSONObject jsonObject)
                    {
                        try
                        {
                            if (jsonObject.getString("code").equals("0"))
                            {
                                mRepository.passwordRecoveryResponse(String.valueOf(jsonObject.getString("message")));

                            }
                            else
                            {
                                mRepository.passwordRecoveryError(String.valueOf(jsonObject.getString("message")));
                            }

                        }catch (Exception e)
                        {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    //region loginUser
    private void loginUser(UserEntity userEntity)
    {
        Rx2AndroidNetworking.post(UrlApi.urlLogin)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
/*                .addBodyParameter("dni",userEntity.getDni())
                .addBodyParameter("password",userEntity.getPassword())*/
                .addBodyParameter(userEntity)
                .setPriority(Priority.HIGH)
                .build()
                .getJSONObjectObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONObject>() {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                    }
                    @Override
                    public void onNext(JSONObject jsonObject)
                    {
                        try
                        {
                            if (jsonObject.getString("message").equals("OK"))
                            {
                                //Se deserializo el Jquery y se envio a la entidad User
                                userBean= gson.fromJson(jsonObject.getString("user"), UserBean.class);
                                mRepository.registerUserDB(userBean);
                                mRepository.validateSucces(userBean.getNumDocBeneficiario(), userBean.getNombre(), userBean.getEmail());


                            }else if (jsonObject.getString("code").equals("0103"))
                            {
                                mRepository.validateError(String.valueOf(jsonObject.getString("message")));
                            }
                            else if (jsonObject.getString("code").equals("0105"))
                            {
                                mRepository.validateError(String.valueOf(jsonObject.getString("message")));
                            }
                            else
                            {
                                mRepository.validateError(RendicionApplication.getContext().getResources().getString(R.string.sessionError));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            e.getMessage();
                            Log.i("NDa",  e.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        mRepository.validateError(RendicionApplication.getContext().getResources().getString(R.string.servidorError));
                    }

                    @Override
                    public void onComplete()
                    {
                        Log.i("LoginUser",  "Correcto");
                    }
                });

    }
    //endregion
}
