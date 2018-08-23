package com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.entity.RendicionDetalleEntity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.RendicionActivity;

import java.util.List;

import static maes.tech.intentanim.CustomIntent.customType;

public class MovilidadMultipleAdapter extends RecyclerSwipeAdapter<MovilidadMultipleAdapter.MovilidadMultipleViewHolder>
{
    private Context mContext;
    private List<RendicionDetalleEntity> mMovilidadList;
    private RendicionActivity.ItemClick itemCLick;

    public MovilidadMultipleAdapter(Context context, List<RendicionDetalleEntity> movilidadList, RendicionActivity.ItemClick itemCLick)
    {
        this.mContext = context;
        this.mMovilidadList = movilidadList;
        this.itemCLick = itemCLick;
    }

    public class MovilidadMultipleViewHolder extends RecyclerView.ViewHolder
    {
        private SwipeLayout swipeLayout;
        private RelativeLayout lytEdit, lytRemoveDet;
        private TextView txvNombre, txvDni, txvFecha, txvMotivo, txvDestino, txvMonto;

        public MovilidadMultipleViewHolder(View itemView)
        {
            super(itemView);

            txvNombre = itemView.findViewById(R.id.txvNombre);
            txvDni = itemView.findViewById(R.id.txvDni);
            txvFecha = itemView.findViewById(R.id.txvFecha);
            txvMotivo = itemView.findViewById(R.id.txvMotivo);
            txvDestino = itemView.findViewById(R.id.txvDestino);
            txvMonto = itemView.findViewById(R.id.txvMonto);
            lytRemoveDet = itemView.findViewById(R.id.lytRemoveDet);
            lytEdit = itemView.findViewById(R.id.lytEdit);
            swipeLayout = itemView.findViewById(R.id.swipe);
        }
    }

    @NonNull
    @Override
    public MovilidadMultipleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_movidad_multiple, parent, false);
        return new MovilidadMultipleAdapter.MovilidadMultipleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovilidadMultipleViewHolder viewHolder, int position)
    {
        final RendicionDetalleEntity rendicionDetalle = mMovilidadList.get(position);

        viewHolder.txvNombre.setText(String.valueOf(rendicionDetalle.getBeneficiario()));
        viewHolder.txvDni.setText(String.valueOf(rendicionDetalle.getNumBeneficiario()));
        viewHolder.txvFecha.setText(String.valueOf(rendicionDetalle.getFechaRendicion()));
        viewHolder.txvMotivo.setText(String.valueOf(rendicionDetalle.getMotivoMovilidad()));
        viewHolder.txvDestino.setText(String.valueOf(rendicionDetalle.getDestinoMovilidad()));
        viewHolder.txvMonto.setText(String.valueOf(rendicionDetalle.getMontoMovilidad()));

        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
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

        viewHolder.lytEdit.setOnClickListener(v ->
        {
            Intent intent = new Intent(mContext, FormularioActivity.class);
            intent.putExtra("id", String.valueOf(mMovilidadList.get(position).getId()));
            customType(mContext, "fadein-to-fadeout");
            mContext.startActivity(intent);
        });

        viewHolder.lytRemoveDet.setOnClickListener(v ->
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle(R.string.tittleDialog);
            builder.setMessage(R.string.messageDialog);
            builder.setPositiveButton(R.string.btnPositive, (dialog, id) ->
            {
                itemCLick.onClick(v, rendicionDetalle.getId());
                mMovilidadList.remove(position);
                notifyItemRemoved(position);

            });
            builder.setNegativeButton(R.string.btnNegative, (dialog, id) ->

                    dialog.dismiss()
            );

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return mMovilidadList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return mMovilidadList.size();
    }

}
