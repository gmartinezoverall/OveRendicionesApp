package com.overall.developer.overrendicion.data.repository.CreateAccount.api;

import android.util.Log;

import com.androidnetworking.common.Priority;

import com.overall.developer.overrendicion.BuildConfig;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.RendicionApplication;
import com.overall.developer.overrendicion.data.repository.CreateAccount.CreateAccountRepository;
import com.overall.developer.overrendicion.utils.UrlApi;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ApiCreateAccountImpl implements ApiCreateAccount
{
    private CreateAccountRepository mRepository;

    public ApiCreateAccountImpl(CreateAccountRepository createAccountRepository)
    {
        this.mRepository = createAccountRepository;

    }

    @Override
    public Observable createAccountApi(String dni, String password, String email, String telefono)
    {
        return apiCreateAccount(dni, password, email, telefono);

    }

    private Observable apiCreateAccount(String dni, String password, String email, String telefono)
    {

        Rx2AndroidNetworking.post(UrlApi.urlCreateAccount)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("dni",dni)
                .addBodyParameter("password", password)
                .addBodyParameter("email",email)
                .addBodyParameter("telefono",telefono)
                .setPriority(Priority.HIGH)
                .build()
                .getJSONObjectObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JSONObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JSONObject jsonObject)
                    {
                        try {

                            if (jsonObject.getString("code").equals("0"))
                            {
                                //Se deserializo el Jquery y se envio a la entidad User

                                mRepository.createAccountSuccess(RendicionApplication.getContext().getResources().getString(R.string.createAccountSucces));


                            }else if (jsonObject.getString("code").equals("0104"))
                            {
                                mRepository.createAccountError(String.valueOf(jsonObject.getString("message")));
                            }
                            else if (jsonObject.getString("code").equals("0106"))
                            {
                                mRepository.createAccountError(String.valueOf(jsonObject.getString("message")));
                            }
                            else if (jsonObject.getString("code").equals("0107"))
                            {
                                mRepository.createAccountError(String.valueOf(jsonObject.getString("message")));
                            }
                            else
                            {
                                mRepository.createAccountError(String.valueOf(jsonObject.getString("message")));
                            }


                            //response = jsonObject.getString("message").toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete()
                    {
/*                            if (response.equals("OK")) mRepository.createAccountSuccess(RendicionApplication.getContext().getResources().getString(R.string.createAccountSucces));

                            else mRepository.createAccountError(RendicionApplication.getContext().getResources().getString(R.string.createAccountError));*/

                    }
                });
        return null;
    }
}
