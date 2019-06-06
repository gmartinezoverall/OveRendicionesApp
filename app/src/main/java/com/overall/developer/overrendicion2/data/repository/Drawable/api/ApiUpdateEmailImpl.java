package com.overall.developer.overrendicion2.data.repository.Drawable.api;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.overall.developer.overrendicion2.BuildConfig;
import com.overall.developer.overrendicion2.data.repository.Drawable.UpdateEmailRepository;
import com.overall.developer.overrendicion2.utils.UrlApi;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiUpdateEmailImpl implements ApiUpdateEmail
{
    private UpdateEmailRepository mRepository;

    public ApiUpdateEmailImpl(UpdateEmailRepository repository)
    {
        this.mRepository = repository;
    }

    @Override
    public void sendDataApi(String dni, String newEmail)
    {
        AndroidNetworking.post(UrlApi.urlUpdateEmail)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("dni", dni)
                .addBodyParameter("email", newEmail)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            if (response.getString("code").equals("0")) {

                                mRepository.responseUpdateEmailSuccess(String.valueOf(response.getString("message")));
                            }else
                            {
                                mRepository.responseUpdateEmailError(String.valueOf(response.getString("message")));
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
