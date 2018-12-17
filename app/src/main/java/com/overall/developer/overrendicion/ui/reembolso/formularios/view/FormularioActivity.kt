package com.overall.developer.overrendicion.ui.reembolso.formularios.view

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.overall.developer.overrendicion.R
import com.overall.developer.overrendicion.ui.reembolso.formularios.view.fragments.*
import com.overall.developer.overrendicion.ui.reembolso.formularios.presenter.FormularioPresenter
import com.overall.developer.overrendicion.ui.reembolso.formularios.presenter.IFormularioPresenter

import kotlinx.android.synthetic.main.content_formulario.*
import java.util.*

class FormularioActivity : AppCompatActivity(), IFormularioView {

    internal lateinit var mPresenter: IFormularioPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_reembolso)

        mPresenter = FormularioPresenter(this)


        val dropdownview = dropdownview
        val typeFormList = (resources.getStringArray(R.array.formularios)).toMutableList()

        dropdownview.setDropDownListItem(typeFormList)
        dropdownview.selectingPosition = 6//formulario por defecto

        dropdownview.setOnSelectionListener{ view, position ->

            replaceFragment(dropdownview.selectingPosition)

        }
        replaceFragment(dropdownview.selectingPosition)
    }

    private fun replaceFragment(nameFragment: Int){

        val fragment = when(nameFragment){
            0 ->  ArrendamientoFragment()
            1 ->  BoletaVentaFragment()
            2 ->  BoletoAereoFragment()
            3 ->  BoletoTerrestreFragment()
            4 ->  CartaPorteAereoFragment()
            5 ->  DescuentoBoletaFragment()
            6 ->  FacturaFragment()
            7 ->  MovilidadFragment()
            8 ->  NotaCreditoFragment()
            9 ->  OtrosDocumentosFragment()
            10 ->  ReciboHonorariosFragment()
            11 ->  ReciboServiciosPublicos()
            12 ->  SinSustentoTributarioFragment()
            13 ->  TicketMaquinaRegistradoraFragment()
            14 ->  MovilidadMultipleFragment()
            15 ->  VoucherBancarioFragment()
            else ->{
                FacturaFragment()
            }
        }

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fytFormularioReembolso, fragment)
        ft.commit()

    }
}
