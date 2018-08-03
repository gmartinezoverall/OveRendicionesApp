package com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.RendicionDetalleEntity;
import com.overall.developer.overrendicion.data.model.entity.RendicionEntity;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.RendicionActivity;

import java.util.List;

public class RendicionAdapter extends RecyclerSwipeAdapter<RendicionAdapter.RendicionViewHolder>
{
    private Context mContext;
    private Activity mActivity;
    private List<RendicionEntity> mRendicionList;
    private List<RendicionDetalleEntity> mMovilidadList;
    private RendicionActivity.ItemClick itemCLick;

    public RendicionAdapter (Context context, Activity activity, List<RendicionEntity> rendicionList, List<RendicionDetalleEntity> movilidadList, RendicionActivity.ItemClick onClick)
    {
        this.mContext = context;
        this.mRendicionList = rendicionList;
        this.mActivity = activity;
        this.mMovilidadList= movilidadList;
        this.itemCLick = onClick;
    }

    public  class RendicionViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txvCodLiquidacion, txvDocumento, txvNumDocumento, txvPrecioTotal;
        private RelativeLayout lytDetalle, lytRemove, lytEdit, lytNew;
        private LinearLayout lytDetMovilidad, lytCardViewRendicion;
        private Button rowButton;
        private RecyclerView rcvMovilidad;
        private SwipeLayout swipeLayout;


        public RendicionViewHolder(View itemView)
        {
            super(itemView);

            txvCodLiquidacion = itemView.findViewById(R.id.txvCodLiquidacion);
            txvDocumento = itemView.findViewById(R.id.txvDocumento);
            txvNumDocumento = itemView.findViewById(R.id.txvNumDocumento);
            txvPrecioTotal = itemView.findViewById(R.id.txvPrecioTotal);
            lytDetalle = itemView.findViewById(R.id.lytDetalle);
            lytRemove = itemView.findViewById(R.id.lytRemove);
            lytEdit = itemView.findViewById(R.id.lytEdit);
            lytNew = itemView.findViewById(R.id.lytNew);
            lytDetMovilidad = itemView.findViewById(R.id.lytDetMovilidad);
            rowButton = itemView.findViewById(R.id.rowButton);
            lytCardViewRendicion = itemView.findViewById(R.id.lytCardViewRendicion);
            rcvMovilidad = itemView.findViewById(R.id.rcvMovilidad);
            swipeLayout = itemView.findViewById(R.id.swipe);
            txvDocumento.setSelected(true);


        }
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

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        holder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout)
            {
                YoYo.with(Techniques.FadeOutLeft).duration(500).playOn(layout.findViewById(R.id.rowButton));
            }

            @Override
            public void onClose(SwipeLayout layout) {
                YoYo.with(Techniques.FadeInLeft).duration(500).playOn(layout.findViewById(R.id.rowButton));
            }
        });
        /*holder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                //Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });

        holder.swipeLayout.*/

        holder.txvCodLiquidacion.setText(String.valueOf(rendicion.getCodRendicion()));
        holder.txvDocumento.setText(String.valueOf(rendicion.getRdoId()));
        holder.txvNumDocumento.setText(String.valueOf(rendicion.getNumeroDoc()));
        holder.txvPrecioTotal.setText(String.valueOf(rendicion.getPrecioTotal()));

        holder.lytDetalle.setVisibility(rendicion.getRdoId().equals("MOVILIDAD INDIVIDUAL - HOJA RUTA") ? View.VISIBLE : View.GONE);

        holder.lytNew.setVisibility(rendicion.getRdoId().equals("MOVILIDAD INDIVIDUAL - HOJA RUTA") ? View.VISIBLE : View.GONE);

        holder.lytEdit.setVisibility(rendicion.getRdoId().equals("MOVILIDAD INDIVIDUAL - HOJA RUTA") ? View.GONE : View.VISIBLE);

        holder.lytEdit.setOnClickListener( v -> itemCLick.onClick(v, position) );

        holder.lytRemove.setOnClickListener( v -> itemCLick.onClick(v, position) );

        holder.lytNew.setOnClickListener(v -> itemCLick.onClick(v, position));

        holder.lytDetalle.setOnClickListener(v ->
        {
                holder.lytDetMovilidad.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FadeInLeft).duration(500).playOn(holder.itemView.findViewById(R.id.rowButton));
                holder.rowButton.setRotation(90);
                holder.rcvMovilidad.setAdapter(new MovilidadAdapter(mContext, mMovilidadList, (view, movPosition) ->
                {
                    itemCLick.onClick(view, movPosition);
                }));
                holder.rcvMovilidad.setLayoutManager(new LinearLayoutManager(mContext));

                final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(holder.rcvMovilidad.getContext(), R.anim.layout_slide_bottom);

                holder.rcvMovilidad.setLayoutAnimation(controller);
                holder.rcvMovilidad.getAdapter().notifyDataSetChanged();
                holder.rcvMovilidad.scheduleLayoutAnimation();


        });

        holder.lytCardViewRendicion.setOnClickListener(v ->
        {
            if (holder.lytDetMovilidad.getVisibility() == View.VISIBLE) {
                holder.lytDetMovilidad.setVisibility(View.GONE);
                holder.rowButton.setRotation(0);
                YoYo.with(Techniques.FadeInLeft).duration(500).playOn(holder.itemView.findViewById(R.id.rowButton));
            }

        });
        holder.rowButton.setOnClickListener(v ->
        {
            if (holder.lytDetMovilidad.getVisibility() == View.VISIBLE) {
                holder.lytDetMovilidad.setVisibility(View.GONE);
                holder.rowButton.setRotation(0);
                YoYo.with(Techniques.FadeInLeft).duration(500).playOn(holder.itemView.findViewById(R.id.rowButton));
            }

        });

    }

    @Override
    public int getItemCount()
    {
        return mRendicionList.size();
    }


    @Override
    public int getSwipeLayoutResourceId(int position) {
        return  R.id.swipe;
    }
}
