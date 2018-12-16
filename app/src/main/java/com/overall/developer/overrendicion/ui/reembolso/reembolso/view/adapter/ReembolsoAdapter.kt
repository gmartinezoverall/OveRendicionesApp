package com.overall.developer.overrendicion.ui.reembolso.reembolso.view.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.daimajia.androidanimations.library.Techniques
import com.overall.developer.overrendicion.R
import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.ui.reembolso.datosGenerales.view.NuevoReembolsoActivity
import com.overall.developer.overrendicion.ui.reembolso.rendicionesList.view.DocumentosListActivity
import com.overall.developer.overrendicion.ui.reembolso.reembolso.view.ReembolsoActivity
import kotlinx.android.synthetic.main.cardview_reembolso.view.*
import kotlinx.android.synthetic.main.hover_pendiente.view.*
import org.jetbrains.anko.startActivity

class ReembolsoAdapter(private val items: ArrayList<ReembolsoEntity>, val context: Context) : RecyclerView.Adapter<ReembolsoAdapter.ViewHolder>()
{
    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        internal val inflater = view.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        internal val hover = inflater.inflate(R.layout.hover_pendiente, null)

        val txvTitulo = view.txvTitulo!!
        val txvDescripcion = view.txvDescripcion!!
        val txvMonto = view.txvMonto!!
        val txvEstado = view.txvEstado!!
        val txvFechaReembolso = view.txvFechaReembolso!!
        val lytContent = view.lytBlur!!
        val colorReembolso = view.colorReembolso!!

        val btnDatos = hover.btnDatos!!
        val btnReembolso = hover.btnRendicion!!
        val btnEnviarResumen = hover.btnEnviarResumen!!
        val hoverDescripcion = hover.txvDescription!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_reembolso, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        with(holder)
        {
            txvTitulo.text = items[position].nombreConsultora
            txvDescripcion.text = items[position].descTReembolso
            txvMonto.text = items[position].monto
            txvEstado.text = items[position].estado
            txvFechaReembolso.text = items[position].fechaReembolso
            btnReembolso.text = "Reembolsos"

            when(txvEstado.text) {
                "Por Enviar" -> colorReembolso.setBackgroundColor(Color.BLUE)
                "En Espera" -> colorReembolso.setBackgroundColor(Color.YELLOW)
                "Aprobado" -> colorReembolso.setBackgroundColor(Color.GREEN)
                "Rechazado" -> colorReembolso.setBackgroundColor(Color.RED)
            }

            hoverDescripcion.text = txvDescripcion.text.toString()

            btnDatos.setOnClickListener{
                context.startActivity<NuevoReembolsoActivity>("codReembolso" to items[position].codReembolso)
            }

            btnReembolso.setOnClickListener {
                (context as ReembolsoActivity).changeStatusReembolso(items[position].codReembolso)
                context.startActivity<DocumentosListActivity>() }

            with(lytContent)
            {
                setHoverView(hover)
                setBlurDuration(1000)

                addChildAppearAnimator(hover, R.id.btnDatos, Techniques.FlipInX, 550, 0)
                addChildDisappearAnimator(hover, R.id.btnDatos, Techniques.FlipOutX, 550, 500)

                addChildAppearAnimator(hover, R.id.txvDescription, Techniques.FadeInUp)
                addChildDisappearAnimator(hover, R.id.txvDescription, Techniques.FadeOutDown)

                addChildAppearAnimator(hover, R.id.btnRendicion, Techniques.FlipInX, 550, 250)
                addChildDisappearAnimator(hover, R.id.btnRendicion, Techniques.FlipOutX, 550, 250)

                addChildAppearAnimator(hover, R.id.btnEnviarResumen, Techniques.FlipInX, 550, 500)
                addChildDisappearAnimator(hover, R.id.btnEnviarResumen, Techniques.FlipOutX, 550, 0)

            }


        }

    }

    override fun getItemCount(): Int {
        return  items.size
    }




}