package com.overall.developer.overrendicion.data.repository.Drawable.api;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.overall.developer.overrendicion.BuildConfig;
import com.overall.developer.overrendicion.data.repository.Drawable.RecoveryPasswordRepository;
import com.overall.developer.overrendicion.utils.UrlApi;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiRecoveryPasswordImpl implements ApiRecoveryPassword
{
    private RecoveryPasswordRepository mRepository;

    public ApiRecoveryPasswordImpl(RecoveryPasswordRepository repository)
    {
        this.mRepository = repository;

    }

    @Override
    public void sendData(String dni, String newPassword)
    {
        AndroidNetworking.post(UrlApi.urlUpdatePassword)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("dni", dni)
                .addBodyParameter("password", newPassword)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            if (response.getString("code").equals("0")) {

                                mRepository.responseRoveryPasswordSuccess(String.valueOf(response.getString("message")));
                            }else
                            {
                                mRepository.responseRoveryPasswordError(String.valueOf(response.getString("message")));
                            }
                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onError(ANError error)
                    {


                    }
                });

    }
}
