package com.overall.developer.overrendicion2.data.repository.reembolso.NuevoReembolso.api

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.overall.developer.overrendicion2.BuildConfig
import com.overall.developer.overrendicion2.data.model.request.InsertReembolsoRequest
import com.overall.developer.overrendicion2.data.model.request.UpdateReembolsoRequest
import com.overall.developer.overrendicion2.ui.reembolso.datosGenerales.interactor.INuevoReembolsoInteractor
import com.overall.developer.overrendicion2.utils.UrlApi
import org.json.JSONObject

class ApiNuevoReembolso(internal val mInteractor: INuevoReembolsoInteractor): IApiNuevoReembolso
{
    override fun insertNewRefundApi(reembolsoRequest: InsertReembolsoRequest) {

        AndroidNetworking.post(UrlApi.urlInsertarReembolso)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter(reembolsoRequest)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?)
                    {
                        mInteractor.successRestApi()
                    }

                    override fun onError(anError: ANError?)
                    {
                        Log.i("ASDError",anError.toString())
                    }
                })
    }

    override fun updateRefundApi(updateRequest: UpdateReembolsoRequest) {

        AndroidNetworking.post(UrlApi.urlEditarReembolso)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter(updateRequest)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?)
                    {
                        mInteractor.successRestApi()

                    }

                    override fun onError(anError: ANError?)
                    {
                        Log.i("ASDError",anError.toString())
                    }
                })
    }
}