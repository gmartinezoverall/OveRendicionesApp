package com.overall.developer.overrendicion.data.repository.Reembolso.api

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.overall.developer.overrendicion.BuildConfig
import com.overall.developer.overrendicion.data.model.bean.ReembolsoBean
import com.overall.developer.overrendicion.ui.reembolso.reembolso.interactor.IReembolsoInteractor
import com.overall.developer.overrendicion.utils.UrlApi
import org.json.JSONObject
import kotlin.random.Random


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


}