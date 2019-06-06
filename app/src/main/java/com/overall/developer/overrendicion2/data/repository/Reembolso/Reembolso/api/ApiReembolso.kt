package com.overall.developer.overrendicion2.data.repository.reembolso.Reembolso.api

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.overall.developer.overrendicion2.BuildConfig
import com.overall.developer.overrendicion2.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion2.ui.reembolso.reembolso.interactor.IReembolsoInteractor
import com.overall.developer.overrendicion2.utils.UrlApi
import org.json.JSONObject


class ApiReembolso (internal val mInteractor: IReembolsoInteractor) : IApiReembolso
{
    override fun listReembolsoApi(dni: String)
    {
        AndroidNetworking.post(UrlApi.urlListarReembolso)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("dni", dni)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener{
                    override fun onResponse(response: JSONObject?)
                    {
                        val collectionType = object : TypeToken<Collection<ReembolsoBean>>() {}.type
                        val reembolsoBeans = Gson().fromJson<List<ReembolsoBean>>(response?.getString("refunds"), collectionType)

                        mInteractor.listReembolsoApiSuccess(reembolsoBeans)
                    }

                    override fun onError(anError: ANError?)
                    {
                        mInteractor.listReembolsoApiError()

                    }
                })
    }

    override fun sendResumeApi(codReembolso: String) {
        AndroidNetworking.post(UrlApi.urlSendResumeEmailReemsolo)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("codReembolso", codReembolso)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener{
                    override fun onResponse(response: JSONObject?)
                    {
                       mInteractor.sendResumeSuccess()
                    }

                    override fun onError(anError: ANError?)
                    {
                      Log.i("ASD.Api",anError.toString())
                    }
                })
    }
}