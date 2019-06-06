package com.overall.developer.overrendicion2.data.repository.reembolso.Reembolso.api

interface IApiReembolso
{
    fun listReembolsoApi(dni: String)

    fun sendResumeApi(codReembolso: String)
}