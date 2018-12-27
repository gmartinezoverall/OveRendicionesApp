package com.overall.developer.overrendicion.data.repository.reembolso.Formulario.api

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.overall.developer.overrendicion.BuildConfig
import com.overall.developer.overrendicion.data.model.request.EditRendicionReembolsoRequest
import com.overall.developer.overrendicion.data.model.request.InsertRendicionReembolsoRequest
import com.overall.developer.overrendicion.data.model.request.RendicionRequest
import com.overall.developer.overrendicion.ui.reembolso.formularios.interactor.FormularioInteractor
import com.overall.developer.overrendicion.utils.UrlApi
import okhttp3.OkHttpClient
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class ApiFormulario (internal val mInteractor: FormularioInteractor): IApiFormulario
{
    override fun searchRuchApi(ruc: String) {

        val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(8, TimeUnit.SECONDS)
                .readTimeout(8, TimeUnit.SECONDS)
                .writeTimeout(8, TimeUnit.SECONDS)
                .build()

        AndroidNetworking.get(UrlApi.urlWSRuc)
                .addPathParameter("ruc", ruc)
                .setPriority(Priority.IMMEDIATE)
                .setOkHttpClient(okHttpClient)
                .build()
                .getAsJSONArray(object : JSONArrayRequestListener {
                    override fun onResponse(response: JSONArray) {
                        try {
                            val jsonObject = response.getJSONObject(0)
                            mInteractor.searchRucSuccess(jsonObject.getString("razon_social"))
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }

                    }

                    override fun onError(anError: ANError) {
                        searchRucProveedoresAPi(ruc)
                    }
                })
    }

    private fun searchRucProveedoresAPi(ruc: String) {

        AndroidNetworking.post(UrlApi.urlSearchProveedores)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter("ruc", ruc)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            if (response.getString("message") == "OK") {
                                val desc = response.getJSONArray("proveedor").getJSONObject(0).getString("desc")
                                mInteractor.searchRucSuccess(desc)
                            } else {
                                mInteractor.searchRucError()
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }

                    }

                    override fun onError(anError: ANError) {
                        Log.e("ApiFormulariosImpl", anError.response.toString())
                    }
                })
    }

    override fun sendDataForInsertApi(request: InsertRendicionReembolsoRequest, idRendicion: Int) {

        AndroidNetworking.post(UrlApi.urlInsertarRendicionReembolso)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter(request)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            if (response.getString("code") == "0") {
                                //val desc = response.getJSONArray("proveedor").getJSONObject(0).getString("desc")
                                mInteractor.deleteRendicionSend(idRendicion)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }

                    }

                    override fun onError(anError: ANError) {
                        Log.e("ApiFormulariosImpl", anError.response.toString())
                    }
                })

    }

    override fun sendDataForUpdateApi(request: EditRendicionReembolsoRequest, idRendicion: Int) {

        AndroidNetworking.post(UrlApi.urlEditarRendicionReembolso)
                .addBodyParameter("apiKey", BuildConfig.API_KEY)
                .addBodyParameter(request)
                .setPriority(Priority.IMMEDIATE)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        try {
                            if (response.getString("code") == "0") {
                                mInteractor.deleteRendicionSend(idRendicion)
                            }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }

                    }

                    override fun onError(anError: ANError) {
                        Log.e("ApiFormulariosImpl", anError.response.toString())
                    }
                })

    }
}