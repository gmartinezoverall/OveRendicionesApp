package com.overall.developer.overrendicion.ui.reembolso.rendicionesList.view.adapter

import android.content.Context

import android.view.ViewGroup
import com.omega_r.libs.omegarecyclerview.OmegaRecyclerView
import com.omega_r.libs.omegarecyclerview.swipe_menu.SwipeViewHolder
import com.overall.developer.overrendicion.R
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity


class DocumentosAdapter(val context: Context, private val itemsList: ArrayList<RendicionEntity>): OmegaRecyclerView.Adapter<DocumentosAdapter.ViewHolder>()
{
    class ViewHolder(view: ViewGroup): SwipeViewHolder(view, R.layout.card_view_rendicion_reembolso, R.layout.item_right_swipe_menu)

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

        }
        //holder.itemView.txvPrueba01.text = itemsList[position]?.desc

    }

}