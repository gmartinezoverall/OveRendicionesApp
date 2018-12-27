package com.overall.developer.overrendicion.data.repository.reembolso.documentosReembolsoList.api

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.overall.developer.overrendicion.BuildConfig
import com.overall.developer.overrendicion.data.model.bean.RendicionReembolsoBean
import com.overall.developer.overrendicion.ui.reembolso.rendicionesList.interactor.IDocumentosListInteractor
import com.overall.developer.overrendicion.utils.UrlApi
import org.json.JSONObject

class ApiDocumentosList(internal val mInteractor: IDocumentosListInteractor): IApiDocumentosList
{
    override fun setDocumentosListApi(codReembolso: String) {
        AndroidNetworking.post(UrlApi.urlListarRendicionReembolso)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("codReembolso", codReembolso)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?)
                    {
                        val collectionType = object : TypeToken<Collection<RendicionReembolsoBean>>() {}.type
                        val reembolsoBeans = Gson().fromJson<ArrayList<RendicionReembolsoBean>>(response?.getString("rendicionReembolso"), collectionType)

                        mInteractor.successGetRendicionesListtApi(reembolsoBeans)
                    }

                    override fun onError(anError: ANError?)
                    {
                        Log.i("ASDError",anError.toString())
                    }
                })
    }

    override fun deleteRendicionApi(codRendicion: String) {
        AndroidNetworking.post(UrlApi.urlEliminarRendicionReembolso)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("codRendicionR", codRendicion)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?)
                    {
                        Log.i("ASDCorrecto","Exito al Elminar")
                    }

                    override fun onError(anError: ANError?)
                    {
                        Log.i("ASDError",anError.toString())
                    }
                })
    }
}