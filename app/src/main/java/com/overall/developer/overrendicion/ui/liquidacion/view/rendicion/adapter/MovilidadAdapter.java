package com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.MovilidadEntity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.RendicionActivity;

import java.util.List;

import static maes.tech.intentanim.CustomIntent.customType;

public class MovilidadAdapter extends RecyclerSwipeAdapter<MovilidadAdapter.MovilidadViewHolder>
{
    private Context mContext;
    private List<MovilidadEntity> mMovilidadList;


    public MovilidadAdapter (Context context, List<MovilidadEntity> movilidadList)
    {
        this.mContext = context;
        this.mMovilidadList = movilidadList;
    }



    public  class MovilidadViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txvFecha, txvDetMotivo, txvDetDestino, txvMonto;
        private RelativeLayout lytEdit, lytRemove;
        private LinearLayout lytCardViewRendicion;
        private Button rowButton;
        private SwipeLayout swipeLayout;


        public MovilidadViewHolder(View itemView)
        {
            super(itemView);

            txvFecha = itemView.findViewById(R.id.txvFecha);
            txvDetMotivo = itemView.findViewById(R.id.txvDetMotivo);
            txvDetDestino = itemView.findViewById(R.id.txvDetDestino);
            txvMonto = itemView.findViewById(R.id.txvMonto);
            lytRemove = itemView.findViewById(R.id.lytRemove);
            lytEdit = itemView.findViewById(R.id.lytEdit);
            rowButton = itemView.findViewById(R.id.rowButton);
            swipeLayout = itemView.findViewById(R.id.swipe);

            txvDetMotivo.setSelected(true);

        }
    }

    @NonNull
    @Override
    public MovilidadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_movilidad, parent, false);
        return new MovilidadViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovilidadViewHolder holder, int position)
    {
        final MovilidadEntity rendicion = mMovilidadList.get(position);
        holder.txvFecha.setText(String.valueOf(rendicion.getId()));
        holder.txvDetMotivo.setText(String.valueOf(rendicion.getMotivoMovilidad()));
        holder.txvDetDestino.setText(String.valueOf(rendicion.getDestinoMovilidad()));
        holder.txvMonto.setText(String.valueOf(rendicion.getMontoMovilidad()));

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

        holder.lytEdit.setOnClickListener(v ->
        {
            Intent intent = new Intent(mContext, FormularioActivity.class);
            intent.putExtra("id", String.valueOf(mMovilidadList.get(position).getId()));
            customType(mContext, "fadein-to-fadeout");
            mContext.startActivity(intent);
        });

        holder.lytRemove.setOnClickListener(v ->
        {
           

        });



    }

    @Override
    public int getItemCount() {
        return mMovilidadList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position)
    {
        return mMovilidadList.size();
    }

}
