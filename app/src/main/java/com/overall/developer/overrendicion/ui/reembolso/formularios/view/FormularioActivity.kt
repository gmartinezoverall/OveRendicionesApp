package com.overall.developer.overrendicion.ui.reembolso.formularios.view

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jaredrummler.android.widget.AnimatedSvgView
import com.overall.developer.overrendicion.R
import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.data.model.entity.TipoGastoEntity
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.communicator.Communicator
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.fragments.communicator.OttoBus
import com.overall.developer.overrendicion.ui.reembolso.formularios.view.fragments.*
import com.overall.developer.overrendicion.ui.reembolso.formularios.presenter.FormularioPresenter
import com.overall.developer.overrendicion.ui.reembolso.formularios.presenter.IFormularioPresenter
import com.overall.developer.overrendicion.utils.Util
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.content_formulario.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class FormularioActivity : AppCompatActivity(), IFormularioView {

    internal lateinit var mPresenter: IFormularioPresenter

    private var mDialog: Dialog? = null

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
    fun getReembolso(): ReembolsoEntity = mPresenter.getReembolso()

    fun getListSpinner(): ArrayList<TipoGastoEntity> = mPresenter.getListSpinner(Util.getIdFragment(dropdownview.selectingPosition).toString())

    fun searchRuch(ruc: String){

        showDialog()
        mPresenter.searchRuch(ruc)
    }


    //region Interfaces
    override fun searchRucSuccess(razonSocial: String) {
        mDialog!!.dismiss()
        OttoBus.getBus().post(Communicator(razonSocial))

    }

    override fun searchRucError() {
        mDialog!!.dismiss()
        OttoBus.getBus().post(Communicator(""))
    }
    //endregion


    //region Dialog
    private fun showDialog() {
        mDialog = Dialog(this)
        mDialog!!.setContentView(R.layout.dialog_progress)
        val svgView: AnimatedSvgView = mDialog!!.findViewById(R.id.animated_svg_view)
        //svgView.postDelayed(() -> svgView.start(), 200);

        mDialog!!.getWindow().setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.backgroundtext_card)))
        mDialog!!.setCancelable(false)
        mDialog!!.show()

        Observable.interval(500, 3500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { timer -> svgView.start() }
    }
    //endregion

}
