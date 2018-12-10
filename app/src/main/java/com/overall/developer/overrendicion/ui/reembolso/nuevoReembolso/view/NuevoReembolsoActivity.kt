package com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.overall.developer.overrendicion.R
import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity
import com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.presenter.INuevoReembolsoPresenter
import com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.presenter.NuevoReembolsoPresenter
import kotlinx.android.synthetic.main.content_nuevo_reembolso.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class NuevoReembolsoActivity : AppCompatActivity(), INuevoReembolsoView
{
    internal lateinit var mPresenter: INuevoReembolsoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_reembolso)

        mPresenter = NuevoReembolsoPresenter(this)


        btnSaveNR.setOnClickListener{
            mPresenter.saveDateNewRefund(ReembolsoEntity(txvDniNR.text.toString(), txvNombreNR.text.toString(), spnTipoReembolsoNR.text.toString(),
                                         txvFechaDesdeNR.text.toString(), txvFechaHastaNR.text.toString(), spnTipoMonedaNR.text.toString(),
                                         txvMotivoNR.text.toString(),"", "", txvMotivoNR.text.toString(),
                                         "", "", txvFechaDesdeNR.text.toString(), txvFechaHastaNR.text.toString(), ""))
        }
    }

    override fun insertNRSuccess()
    {
        startActivity<FormularioActivity>()

    }
}
