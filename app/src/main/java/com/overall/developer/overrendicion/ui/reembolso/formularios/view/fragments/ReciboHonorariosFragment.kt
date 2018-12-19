package com.overall.developer.overrendicion.ui.reembolso.formularios.view.fragments

import `in`.galaxyofandroid.spinerdialog.SpinnerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.overall.developer.overrendicion.R

class ReciboHonorariosFragment : Fragment() {

    private var rtgId: String? = null
    private var pathImage: String? = null
    private val razonSocial: String? = null
    private var spnDialog: SpinnerDialog? = null

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recibo_honorarios_reembolso, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

/*    //region Body
    private fun initialBody() {

        RxTextView.textChanges(etxRuc)
                .filter { etx -> etx.isNotEmpty() && etx.length != 11 }
                .subscribe { etxRuc.error = resources.getString(R.string.validarRuc) }

        val itemList = ArrayList<Any>()
        itemList.addAll((context as FormularioActivity).getListSpinner())

        spnDialog = SpinnerDialog(activity, itemList, resources.getString(R.string.tittleSpinerTipoGasto))
        spnDialog!!.bindOnSpinerListener { item, position ->
            spnTipoGasto.text = (item as TipoGastoEntity).rtgDes
            rtgId = item.rtgId
        }



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
                                img_foto.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
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

    //endregion*/

}
