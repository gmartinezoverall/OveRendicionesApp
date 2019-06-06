package com.overall.developer.overrendicion2.ui.reembolso.formularios.view.fragments

import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.fxn.pix.Pix
import com.fxn.utility.PermUtil
import com.jakewharton.rxbinding2.widget.RxTextView

import com.overall.developer.overrendicion2.R
import com.overall.developer.overrendicion2.data.model.entity.ProvinciaEntity
import com.overall.developer.overrendicion2.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion2.data.model.entity.RendicionEntity
import com.overall.developer.overrendicion2.data.model.entity.TipoGastoEntity
import com.overall.developer.overrendicion2.data.model.entity.formularioEntity.BoletoAereoEntity
import com.overall.developer.overrendicion2.ui.communicator.Communicator
import com.overall.developer.overrendicion2.ui.communicator.OttoBus
import com.overall.developer.overrendicion2.ui.reembolso.formularios.view.FormularioActivity
import com.overall.developer.overrendicion2.utils.Util
import com.squareup.otto.Subscribe
import com.squareup.picasso.Picasso
import id.zelory.compressor.Compressor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_boleto_aereo_reembolso.*
import org.jetbrains.anko.support.v4.toast
import java.io.File
import java.text.SimpleDateFormat


class BoletoAereoFragment : Fragment() {

    private var rtgId: String? = null
    private var idProvincia: String? = null
    private var pathImage: String? = null
    private val razonSocial: String? = null
    private var spnDialog: SpinnerDialog? = null
    private var spnDialogProv: SpinnerDialog? = null
    private var rendicionEntity: RendicionEntity? = null
    private var gastoEntity: TipoGastoEntity? = null
    private var provinciaEntity: ProvinciaEntity? = null

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_boleto_aereo_reembolso, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialCalendar()

        initialBody()

        onClickButtons()

    }

    //region Body
    private fun initialBody() {

        RxTextView.textChanges(etxRuc)
                .filter { etx -> etx.isNotEmpty() && etx.length != 11 }
                .subscribe { etxRuc.error = resources.getString(R.string.validarRuc) }

        RxTextView.textChanges(etxOtrosGastos).filter { etx -> etx.isNotEmpty() }.subscribe { sumaTotal() }
        RxTextView.textChanges(etxValorVenta).filter { etx -> etx.isNotEmpty() }.subscribe { sumaTotal() }

        val itemList = ArrayList<Any>()
        itemList.addAll((context as FormularioActivity).getListSpinner())

        spnDialog = SpinnerDialog(activity, itemList, resources.getString(R.string.tittleSpinerTipoGasto))
        spnDialog!!.bindOnSpinerListener { item, _ ->
            spnTipoGasto.text = (item as TipoGastoEntity).rtgDes
            rtgId = item.rtgId
        }

        val itemListProv = ArrayList<Any>()
        itemListProv.addAll((context as FormularioActivity).getListProvinciaDestino())

        spnDialogProv = SpinnerDialog(activity, itemListProv, resources.getString(R.string.tittleSpinerSearch))
        spnDialogProv!!.bindOnSpinerListener { item, _ ->
            spnDestinoViaje.text = (item as ProvinciaEntity).desc
            idProvincia = item.code
        }

        val adapterTipoMoneda = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, resources.getStringArray(R.array.tipo_moneda))
        spnTipoMoneda.setAdapter(adapterTipoMoneda)

        rendicionEntity = (context as FormularioActivity).getDefaultValues()
        rendicionEntity?.let { setAllDefaultValues() }

    }
    //endregion

    //region Calendar
    private fun initialCalendar() {

        val reembolso: ReembolsoEntity = (context as FormularioActivity).getReembolso()
        with(calendarView)
        {
            calendarView.setDateFormat("dd/MM/yyyy")
            setPreventPreviousDate(false)
            setOnDaySelectedListener{ startDay, endDay ->
                if (!startDay.isEmpty()) {

                    try {
                        val format = SimpleDateFormat("dd/MM/yyyy")
                        val dateNow = format.parse(Util.changeDateFormat(startDay))
                        val dateBefore = format.parse(reembolso.fechaDesde)
                        val dateAfter = format.parse(reembolso.fechaHasta)

                        if (!dateNow.before(dateBefore) && !dateNow.after(dateAfter)) {

                            txvFechaDocumento.run {
                                text = Util.changeDateFormat(startDay)
                                setTypeface(null, Typeface.BOLD)
                                setTextColor(Color.BLACK)
                            }
                            if (startDay != "") {
                                lytCalendar.visibility = (if (lytCalendar.visibility == View.VISIBLE) View.GONE else View.VISIBLE)
                                lytArrow.rotation = (if (lytArrow.rotation == 90f) 0 else 90).toFloat()
                                lytFecha.visibility = View.VISIBLE
                            }

                        } else {

                            toast(resources.getString(R.string.errorDate) + " entre " + reembolso.fechaDesde + " y " + reembolso.fechaHasta)
                            clearDate()

                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            buildCalendar()
        }

    }
    //endregion

    //region Buttons
    fun onClickButtons(){

        lytFecha.setOnClickListener{
            //lytFecha.visibility = View.GONE

            lytArrow.rotation = (if (lytArrow.rotation == 90f) 0 else 90).toFloat()
            lytCalendar.visibility = (if (lytCalendar.visibility == View.VISIBLE) View.GONE else View.VISIBLE)
            calendarView.clearDate()
        }

        spnTipoGasto.setOnClickListener {
            spnDialog!!.showSpinerDialog()
        }
        spnDestinoViaje.setOnClickListener {
            spnDialogProv!!.showSpinerDialog()
        }

        btnSearch.setOnClickListener {
            if (etxRuc.text.toString().length == 11) {
                (context as FormularioActivity).searchRuch(etxRuc.text.toString())
            } else {
                toast(resources.getString(R.string.validarRuc))

            }
        }

        btnFoto.setOnClickListener {
            Pix.start(this, 100, 1)//esta preparado para admitir mas de 1 imagenes y mostrar mas de 1 tambien solo se debe cambiar el numero
        }

        btnGuardar.setOnClickListener{
            val tipoMoneda = if (spnTipoMoneda.selectedIndex == 0) "S" else "D"
            // Log.i("NDa", ((TipoGastoEntity) spnTipoGasto.getSelectedItem()).getRtgId());
            (context as FormularioActivity).saveAndSendData((context as FormularioActivity).getSelectTypoDoc(), BoletoAereoEntity((context as FormularioActivity).getSelectTypoDoc().toString(), etxRuc.text.toString(),
                    etxRazonSocial.text.toString(), etxNDocumento.text.toString() + "-" + etxNSerie.text.toString(), idProvincia.toString(), txvFechaDocumento.text.toString(), tipoMoneda,
                    etxPrecioVenta.text.toString(), resources.getString(R.string.IGV).toString(), (if (chkAfectoIgv.isChecked) "1" else "0").toString(), etxOtrosGastos.text.toString(),
                    rtgId.toString(), pathImage.toString()))

        }


    }
    //endregion

    //region Estados del Fragment
    override fun onResume() {
        super.onResume()
        OttoBus.getBus().register(this)

    }

    override fun onPause() {
        super.onPause()
        OttoBus.getBus().unregister(this)
    }

    @Subscribe
    fun searchRucSuccess(razonSocial: Communicator) {
        etxRazonSocial.setText(razonSocial.razonSocial)
        etxRazonSocial.isEnabled = false
    }

    //endregion

    //region Foto

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            100 -> {
                if (resultCode == Activity.RESULT_OK) {
                    val imageFile = File(data!!.getStringArrayListExtra(Pix.IMAGE_RESULTS)[0])

                    Compressor(context)
                            .setQuality(95)
                            .setCompressFormat(Bitmap.CompressFormat.JPEG)
                            .compressToFileAsFlowable(imageFile)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ file ->
                                imgFoto.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
                                pathImage = file.absolutePath

                            }, { throwable -> Log.i("ErrorCompressImage", throwable.message) })
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PermUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS -> {

                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Pix.start(this, 100, resources.getInteger(R.integer.numFotos))
                } else {
                    Toast.makeText(context, "Tiene que aprobar los permisos para seleccionar una Foto", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }

    //endregion

    //region Funciones
    private fun sumaTotal() {
        val neto: Double?
        val igv: Double?
        val otros: Double?

        if (etxValorVenta != null) {
            if (etxValorVenta.text.toString().isEmpty()) etxValorVenta.setText("0")
            neto = ((if (etxValorVenta.text.toString().isEmpty()) 0 else etxValorVenta.text.toString()).toString()).toDouble()
            igv = java.lang.Double.valueOf(if (chkAfectoIgv.isChecked) (java.lang.Double.valueOf(etxValorVenta.text.toString()) * 0.18).toString() else "0.00")
            txvMontoIGV.text = String.format("%.2f", igv)
            otros = java.lang.Double.valueOf((if (etxOtrosGastos.text.toString().isEmpty()) 0 else etxOtrosGastos.text.toString()).toString())

            etxPrecioVenta.text = String.format("%.3f", neto!! + igv!! + otros!!)
        }
    }

    private fun setAllDefaultValues() {
        gastoEntity = (context as FormularioActivity).getDefaultTipoGasto()
        provinciaEntity = (context as FormularioActivity).getDefaultProvincia()

        val strings = rendicionEntity?.numeroDoc?.split("-")!!

        etxRuc.setText(rendicionEntity?.ruc.toString())
        etxRazonSocial.setText(rendicionEntity?.razonSocial.toString())
        etxNSerie.setText(strings[0])
        etxNDocumento.setText(strings[1])
        spnDestinoViaje.text = provinciaEntity?.desc.toString()
        idProvincia = provinciaEntity?.code.toString()
        txvFechaDocumento.text = rendicionEntity?.fechaDocumento.toString()
        spnTipoMoneda.selectedIndex = if (rendicionEntity?.tipoMoneda == "S") 0 else 1
        etxValorVenta.setText(rendicionEntity?.valorNeto.toString())
        if (rendicionEntity?.afectoIgv == "1") chkAfectoIgv.isChecked = true
        txvMontoIGV.text = rendicionEntity?.igv.toString()
        etxPrecioVenta.text = rendicionEntity?.precioTotal.toString()
        etxOtrosGastos.setText(rendicionEntity?.otroGasto.toString())
        spnTipoGasto.text = gastoEntity?.rtgDes.toString()
        rtgId = gastoEntity?.rtgId.toString()
        pathImage = rendicionEntity?.foto

        Picasso.get()
                //.load("https://s3.us-east-2.amazonaws.com/overrendicion-userfiles-mobilehub-1058830409/uploads/20180826233027.jpg")
                .load(rendicionEntity?.foto)
                .placeholder(R.drawable.ic_add_a_photo)
                .error(R.drawable.ic_highlight_off)
                .into(imgFoto)

    }

    //endregion
}