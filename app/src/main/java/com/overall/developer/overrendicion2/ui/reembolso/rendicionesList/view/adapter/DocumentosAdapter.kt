package com.overall.developer.overrendicion2.ui.reembolso.rendicionesList.view.adapter

import android.content.Context

import android.view.ViewGroup
import com.omega_r.libs.omegarecyclerview.OmegaRecyclerView
import com.omega_r.libs.omegarecyclerview.swipe_menu.SwipeViewHolder
import com.overall.developer.overrendicion2.R
import com.overall.developer.overrendicion2.data.model.entity.RendicionEntity
import com.overall.developer.overrendicion2.ui.reembolso.formularios.view.FormularioActivity
import com.overall.developer.overrendicion2.ui.reembolso.rendicionesList.view.DocumentosListActivity
import kotlinx.android.synthetic.main.card_view_rendicion_reembolso.view.*
import kotlinx.android.synthetic.main.item_right_swipe_menu.view.*
import org.jetbrains.anko.startActivity


class DocumentosAdapter(val context: Context, private val itemsList: ArrayList<RendicionEntity>): OmegaRecyclerView.Adapter<DocumentosAdapter.ViewHolder>()
{
    class ViewHolder(view: ViewGroup): SwipeViewHolder(view, R.layout.card_view_rendicion_reembolso, SwipeViewHolder.NO_ID, R.layout.item_right_swipe_menu)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        with(holder)
        {
            with(itemView)
            {
                txvCodReembolso.text = itemsList[position].codLiquidacion
                txvDocumento.text = itemsList[position].rdoId
                txvNumDocumento.text = itemsList[position].valorNeto
                txvPrecioTotal.text = itemsList[position].precioTotal

                btnDelete.setOnClickListener {
                    (context as DocumentosListActivity).deleteRendicion(itemsList[position].codRendicion)
                }

                btnEdit.setOnClickListener {
                    context.startActivity<FormularioActivity>("codRendicion" to itemsList[position].codRendicion)
                }
            }

        }

    }

}