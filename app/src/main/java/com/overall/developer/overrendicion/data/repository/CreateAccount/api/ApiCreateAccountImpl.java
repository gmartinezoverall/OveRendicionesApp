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
    String response = null;

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
                            response = jsonObject.getString("message").toString();
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
                            if (response.equals("OK")) mRepository.createAccountSuccess(RendicionApplication.getContext().getResources().getString(R.string.createAccountSucces));

                            else mRepository.createAccountError(RendicionApplication.getContext().getResources().getString(R.string.createAccountError));

                    }
                });
        return null;
    }
}
