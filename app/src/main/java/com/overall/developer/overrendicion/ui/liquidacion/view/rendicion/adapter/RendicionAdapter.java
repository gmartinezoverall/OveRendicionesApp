package com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;

import java.util.List;

public class RendicionAdapter extends RecyclerView.Adapter<RendicionAdapter.RendicionViewHolder>
{
    private Context mContext;
    private List<RendicionEntity> mRendicionList;


    public  class RendicionViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txvCodLiquidacion, txvDocumento, txvNumDocumento, txvPrecioTotal;
        private RelativeLayout rlyDetMovilidad, lytRemove;


        public RendicionViewHolder(View itemView)
        {
            super(itemView);

            txvCodLiquidacion = itemView.findViewById(R.id.txvCodLiquidacion);
            txvDocumento = itemView.findViewById(R.id.txvDocumento);
            txvNumDocumento = itemView.findViewById(R.id.txvNumDocumento);
            txvPrecioTotal = itemView.findViewById(R.id.txvPrecioTotal);
            rlyDetMovilidad = itemView.findViewById(R.id.btnDetalle);
            lytRemove = itemView.findViewById(R.id.lytRemove);


        }
    }

    public RendicionAdapter (Context context, List<RendicionEntity> rendicionList)
    {
        this.mContext = context;
        this.mRendicionList = rendicionList;
    }


    @Override
    public RendicionViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_rendicion, parent, false);
        return new RendicionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RendicionViewHolder holder, int position)
    {
        final RendicionEntity rendicion = mRendicionList.get(position);
        holder.txvCodLiquidacion.setText(String.valueOf(rendicion.getCodRendicion()));
        holder.txvDocumento.setText(String.valueOf(rendicion.getRdoId()));
        holder.txvNumDocumento.setText(String.valueOf(rendicion.getNumeroDoc()));
        holder.txvPrecioTotal.setText(String.valueOf(rendicion.getPrecioTotal()));

        //holder.rlyDetMovilidad.setVisibility(View.VISIBLE);

    }

    @Override
    public int getItemCount()
    {
        return mRendicionList.size();
    }


}
