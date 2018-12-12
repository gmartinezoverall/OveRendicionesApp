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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.androidviewhover.BlurLayout;
import com.overall.developer.overrendicion.R;
import com.overall.developer.overrendicion.data.model.bean.LiquidacionBean;
import com.overall.developer.overrendicion.ui.liquidacion.view.datosGenerales.DatosGeneralesActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity;
import com.overall.developer.overrendicion.ui.liquidacion.view.pendiente.PendienteActivity;
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
    private LayoutInflater inflater;
    private View hover;

    public class ComprarViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txvDescripcion,txvMonto, txvAcuenta, txvSaldo, hoverDescripcion, txvTitulo;
        private ImageView imgProducto;
        private ImageButton btnEnviarResumen;
        private Button btnDatos, btnRendicion, btnArrow;
        private LinearLayout lytFondo;
        private BlurLayout mLayout;

        public ComprarViewHolder(View itemView)
        {
            super(itemView);

            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            hover = inflater.inflate(R.layout.hover_pendiente, null);
            lytFondo = itemView.findViewById(R.id.lytFondo);
            txvDescripcion = itemView.findViewById(R.id.txvDescripcion);
            txvMonto = itemView.findViewById(R.id.txvMonto);
            txvAcuenta = itemView.findViewById(R.id.txvAcuenta);
            txvSaldo = itemView.findViewById(R.id.txvSaldo);
            //txvEstado = itemView.findViewById(R.id.txvEstado);
            txvTitulo = itemView.findViewById(R.id.txvTitulo);
            btnArrow = itemView.findViewById(R.id.btnArrow);
            mLayout = itemView.findViewById(R.id.lytBlur);

            btnDatos = hover.findViewById(R.id.btnDatos);
            btnRendicion = hover.findViewById(R.id.btnRendicion);
            btnEnviarResumen = hover.findViewById(R.id.btnEnviarResumen);
            hoverDescripcion = hover.findViewById(R.id.txvDescription);

            PushDownAnim.setPushDownAnimTo(btnDatos, btnRendicion, btnEnviarResumen);

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

        holder.txvDescripcion.setText(liquidacionBean.getDescripcionLiquidacion());
        holder.txvMonto.setText(String.valueOf(liquidacionBean.getMonto()));
        holder.txvAcuenta.setText(String.valueOf(liquidacionBean.getaCuenta()));
        holder.txvSaldo.setText(String.valueOf(liquidacionBean.getSaldo()));
        holder.txvTitulo.setText(String.valueOf(liquidacionBean.getNombre()));
        holder.setIsRecyclable(false);



        //holder.lnyCardview.setB
/*        holder.txvEstado.setText("P");

        holder.txvEstado.setTextColor(this.mContext.getResources().getColor(R.color.pendienteColor));*/

/*        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.card_view_fall_down: R.anim.card_view_fall_down);
        holder.itemView.startAnimation(animation);
        lastPosition = position;*/

       holder.hoverDescripcion.setText(holder.txvDescripcion.getText());

       holder.btnDatos.setOnClickListener(v ->
                {
                    Intent intent = new Intent(mContext, DatosGeneralesActivity.class);
                    intent.putExtra("CodLiquidacion", String.valueOf(liquidacionBean.getCodLiquidacion()));
                    //intent.putExtra("CodLiquidacion", String.valueOf(liquidacionBean.getDescripcionLiquidacion()));
                    Pair<View, String> p0 = Pair.create(holder.lytFondo, "fondo");
                    Pair<View, String> p1 = Pair.create(holder.txvMonto, "monto");
                    Pair<View, String> p2 = Pair.create(holder.txvAcuenta, "acuenta");
                    Pair<View, String> p3 = Pair.create(holder.txvSaldo, "saldo");
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, p0, p1, p2, p3);
                    activity.startActivity(intent, options.toBundle());
                });

        holder.btnRendicion.setOnClickListener(v ->
                {

                    if (!liquidacionBean.getMotivoViaje().isEmpty()) {
                        Intent intent = new Intent(mContext, RendicionActivity.class);
                        intent.putExtra("CodLiquidacion", String.valueOf(liquidacionBean.getCodLiquidacion()));
                        mContext.startActivity(intent);
                        customType(mContext, "fadein-to-fadeout");
                    } else {
                        Toast.makeText(this.mContext, mContext.getResources().getString(R.string.validatDatosGenerales), Toast.LENGTH_LONG).show();
                    }

                });

       holder.btnEnviarResumen.setOnClickListener(v ->
                {
                    ((PendienteActivity)mContext).sendResumeEmail(liquidacionBean.getCodLiquidacion());
                });

        holder.mLayout.setHoverView(hover);
        holder.mLayout.setBlurDuration(1000);
        holder.mLayout.addChildAppearAnimator(hover, R.id.btnDatos, Techniques.FlipInX, 550, 0);
        holder.mLayout.addChildDisappearAnimator(hover, R.id.btnDatos, Techniques.FlipOutX, 550, 500);


        holder.mLayout.addChildAppearAnimator(hover, R.id.txvDescription, Techniques.FadeInUp);
        holder.mLayout.addChildDisappearAnimator(hover, R.id.txvDescription, Techniques.FadeOutDown);

        if (!liquidacionBean.getMotivoViaje().isEmpty())
        {
            holder.mLayout.addChildAppearAnimator(hover, R.id.btnRendicion, Techniques.FlipInX, 550, 250);
            holder.mLayout.addChildDisappearAnimator(hover, R.id.btnRendicion, Techniques.FlipOutX, 550, 250);
        }
        if (((PendienteActivity)mContext).validateRendicionisEmpy(liquidacionBean.getCodLiquidacion()))
        {
            holder.mLayout.addChildAppearAnimator(hover, R.id.btnEnviarResumen, Techniques.FlipInX, 550, 500);
            holder.mLayout.addChildDisappearAnimator(hover, R.id.btnEnviarResumen, Techniques.FlipOutX, 550, 0);
        }

    }



    @Override
    public int getItemCount()
    {
        return mPendienteBeanList.size();
    }


}
