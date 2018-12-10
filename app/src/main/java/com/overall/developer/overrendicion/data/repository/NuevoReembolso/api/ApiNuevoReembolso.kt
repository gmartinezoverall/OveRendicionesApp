package com.overall.developer.overrendicion.data.repository.NuevoReembolso.api

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.overall.developer.overrendicion.BuildConfig
import com.overall.developer.overrendicion.data.model.request.ReembolsoRequest
import com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.interactor.INuevoReembolsoInteractor
import com.overall.developer.overrendicion.utils.UrlApi
import org.json.JSONObject

class ApiNuevoReembolso(internal val mInteractor: INuevoReembolsoInteractor): IApiNuevoReembolso
{
    override fun insertNewRefundApi(reembolsoRequest: ReembolsoRequest) {

        AndroidNetworking.post(UrlApi.urlListarReembolso)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter(reembolsoRequest)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?)
                    {
                        mInteractor.insertNRSuccess()
                    }

                    override fun onError(anError: ANError?)
                    {

                    }
                })
    }

}