package com.overall.developer.overrendicion.ui.reembolso.reembolso.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.overall.developer.overrendicion.R
import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import kotlinx.android.synthetic.main.cardview_reembolso.view.*

class ReembolsoAdapter(private val items: ArrayList<ReembolsoEntity>, val context: Context) : RecyclerView.Adapter<ReembolsoAdapter.ViewHolder>()
{
    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        val txvTitulo = view.txvTitulo!!
        val txvDescripcion = view.txvDescripcion!!
        val txvMonto = view.txvMonto!!
        val txvEstado = view.txvEstado!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_reembolso, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder?.txvTitulo?.text = items[position].nombreConsultora
        holder?.txvDescripcion?.text = items[position].descTReembolso
        holder?.txvMonto?.text = items[position].monto
        holder?.txvEstado?.text = items[position].estado

    }

    override fun getItemCount(): Int {
        return  items.size
    }




}