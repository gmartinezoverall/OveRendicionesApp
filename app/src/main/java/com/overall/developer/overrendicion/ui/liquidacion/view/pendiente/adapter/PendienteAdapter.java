package com.overall.developer.overrendicion.ui.liquidacion.view.pendiente.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.ui.liquidacion.view.datosGenerales.DatosGeneralesActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.rendicion.RendicionActivity;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.List;

import static maes.tech.intentanim.CustomIntent.customType;

/**
 * Created by terry on 1/20/2017.
 */

public class PendienteAdapter extends RecyclerView.Adapter<PendienteAdapter.ComprarViewHolder>
{
    private Context mContext;
    private Activity activity;
    private Animation mAnimation;
    private List<LiquidacionBean> mPendienteBeanList;

    public class ComprarViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txvDescripcion,txvMonto, txvAcuenta, txvSaldo, txvEstado;
        private ImageView imgProducto;
        private Button btnDatos, btnRendicion;
        private LinearLayout viewButton;

        public ComprarViewHolder(View itemView)
        {
            super(itemView);

            txvDescripcion = itemView.findViewById(R.id.txvDescripcion);
            txvMonto = itemView.findViewById(R.id.txvMonto);
            txvAcuenta = itemView.findViewById(R.id.txvAcuenta);
            txvSaldo = itemView.findViewById(R.id.txvSaldo);
            txvEstado = itemView.findViewById(R.id.txvEstado);
            viewButton = itemView.findViewById(R.id.viewButton);
            btnDatos = itemView.findViewById(R.id.btnDatos);
            btnRendicion = itemView.findViewById(R.id.btnRendicion);
            PushDownAnim.setPushDownAnimTo(btnDatos);


        }
    }

    public PendienteAdapter(Context context, List<LiquidacionBean> pendienteBeanList, Activity activity)
    {
        this.mContext = context;
        this.mPendienteBeanList = pendienteBeanList;
        this.activity = activity;
    }

    @Override
    public ComprarViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pendiente, parent, false);
        return new ComprarViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ComprarViewHolder holder, int position)
    {
        final LiquidacionBean liquidacionBean = mPendienteBeanList.get(position);
        //holder.txvNombre.setText(pendienteBean.getNombre().toString());
        holder.txvDescripcion.setText(liquidacionBean.getDescripcionLiquidacion());
        holder.txvMonto.setText(String.valueOf(liquidacionBean.getMonto()));
        holder.txvAcuenta.setText(String.valueOf(liquidacionBean.getaCuenta()));
        holder.txvSaldo.setText(String.valueOf(liquidacionBean.getSaldo()));

        //holder.lnyCardview.setB
        holder.txvEstado.setText("P");

        holder.txvEstado.setTextColor(this.mContext.getResources().getColor(R.color.pendienteColor));

/*        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.card_view_fall_down: R.anim.card_view_fall_down);
        holder.itemView.startAnimation(animation);
        lastPosition = position;*/
        holder.btnDatos.setOnClickListener(view ->
        {
            Intent intent = new Intent(mContext, DatosGeneralesActivity.class);
            intent.putExtra("CodLiquidacion", String.valueOf(liquidacionBean.getCodLiquidacion()));
            //intent.putExtra("CodLiquidacion", String.valueOf(liquidacionBean.getDescripcionLiquidacion()));
            Pair<View, String> p0 = Pair.create(holder.txvDescripcion, "fondo");
            Pair<View, String> p1 = Pair.create(holder.txvMonto, "monto");
            Pair<View, String> p2 = Pair.create(holder.txvAcuenta, "acuenta");
            Pair<View, String> p3 = Pair.create(holder.txvSaldo, "saldo");
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, p0, p1, p2, p3);
            activity.startActivity(intent, options.toBundle());

        });
        holder.btnRendicion.setOnClickListener(v ->
        {
            Intent intent = new Intent(mContext, RendicionActivity.class);
            intent.putExtra("CodLiquidacion", String.valueOf(liquidacionBean.getCodLiquidacion()));
            mContext.startActivity(intent);
            customType(mContext, "fadein-to-fadeout");

        });

        holder.itemView.setOnClickListener(view ->
        {
            holder.viewButton.setVisibility(holder.viewButton.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);

            mAnimation = AnimationUtils.loadAnimation(mContext, R.anim.fade_in);
            holder.viewButton.setAnimation(mAnimation);

        });

    }

    @Override
    public int getItemCount()
    {
        return mPendienteBeanList.size();
    }


}
