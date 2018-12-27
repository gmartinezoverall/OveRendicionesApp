package com.overall.developer.overrendicion.ui.reembolso.datosGenerales.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import com.github.florent37.awesomebar.AwesomeBar
import com.overall.developer.overrendicion.R
import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.ui.reembolso.datosGenerales.presenter.INuevoReembolsoPresenter
import com.overall.developer.overrendicion.ui.reembolso.datosGenerales.presenter.NuevoReembolsoPresenter
import com.overall.developer.overrendicion.ui.reembolso.reembolso.view.ReembolsoActivity
import com.overall.developer.overrendicion.utils.Util
import com.overall.developer.overrendicion.utils.realmBrowser.RealmBrowser
import kotlinx.android.synthetic.main.activity_reembolso.*
import kotlinx.android.synthetic.main.content_nuevo_reembolso.*
import kotlinx.android.synthetic.main.toolbar_reembolso.*
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat

class NuevoReembolsoActivity : AppCompatActivity(), INuevoReembolsoView
{
    internal lateinit var mPresenter: INuevoReembolsoPresenter
    var reembolsoEntity: ReembolsoEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_reembolso)

        mPresenter = NuevoReembolsoPresenter(this)

        val datosList = mPresenter.getUser()

        txvComNR.text = datosList[0]
        txvDniNR.text = datosList[1]
        txvNombreNR.text = datosList[2]
        txvFechaHastaNR.text = Util.getCurrentDate()


        val adapterTipoMoneda = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, resources.getStringArray(R.array.tipo_moneda))
        spnTipoMonedaNR.setAdapter(adapterTipoMoneda)

        val reembolsoList = arrayOf("VIATICOS", "MOVILIDAD", "CAJA CHICA", "ENTREGAS A RENDIR")
        val adapterTipoReembolso = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, reembolsoList)
        spnTipoReembolsoNR.setAdapter(adapterTipoReembolso)

        initialCalendar()

        val txvTittleToolbar = txvTitulo
        txvTittleToolbar.text = "Nuevo Reembolso"

        val bundle = intent.extras
        if(bundle != null){
            getDefaultValesReembolso(bundle.getString("codReembolso").toString())
            txvTittleToolbar.text = "Editar Reembolso"
        }
        //intent?.let {getDefaultValesReembolso(it.extras.get("codReembolso").toString())}

        lytFecha.setOnClickListener{alterCalendarView()}

        val mToolbar: AwesomeBar = toolbarReembolso
        mToolbar.setOnMenuClickedListener { drawer_layout_reembolso.openDrawer(Gravity.START) }



        clickButtons()
    }

    override fun successRestApi()
    {
        startActivity<ReembolsoActivity>()
        finish()
    }




    private fun initialCalendar(){
        cldFechaDesdeNR.setDateFormat("dd/MM/yyyy")
        cldFechaDesdeNR.setPreventPreviousDate(false)
        cldFechaDesdeNR.setErrToastMessage(R.string.error_date)
       // cldFechaDesdeNR.setOnDaySelectedListener((startDay!, endDay!) ->
        cldFechaDesdeNR.setOnDaySelectedListener{startDay, endDay ->

            if (!startDay.isEmpty())
            {
                val format = SimpleDateFormat("dd/MM/yyyy")
                val dateStart = format.parse(Util.changeDateFormat(startDay))
                txvFechaDesdeNR.text = (Util.changeDateFormat(startDay))
                //txvFechaDesdeNR.setTypeface(null, Typeface.BOLD)
                //txvFechaDesdeNR.setTextColor(ContextCompat.getColor(this, R.color.colorBlack))
                alterCalendarView()
                cldFechaDesdeNR.clearDate()

            }

        }
        cldFechaDesdeNR.buildCalendar()

    }
    private fun alterCalendarView()
    {
        lytCalendar.visibility = (if (lytCalendar.visibility == View.VISIBLE) View.GONE else View.VISIBLE)
        lytArrow.rotation = (if (lytArrow.rotation == 90F) 0F else 90F)
    }

    private fun getDefaultValesReembolso(codReembolso:String) {
        reembolsoEntity = mPresenter.getDefaultValesReembolso(codReembolso)
        spnTipoReembolsoNR.selectedIndex= when(reembolsoEntity?.descTReembolso){
            "VIATICOS" -> 0
            "MOVILIDAD" -> 1
            "CAJA CHICA" -> 2
            else->{3}
        }
        txvFechaDesdeNR.text = reembolsoEntity?.fechaDesde
        spnTipoMonedaNR.selectedIndex = if (reembolsoEntity?.tipoMoneda == "S") 0 else 1
        txvMotivoNR?.setText(reembolsoEntity?.motivoReembolso)

    }

    private fun clickButtons()
    {
        btnSaveNR.setOnClickListener{
            mPresenter.saveDateNewRefund(ReembolsoEntity(reembolsoEntity?.codReembolso ?: "-", "-", "-", txvDniNR.text.toString(),
                    txvNombreNR.text.toString(), "0", spnTipoMonedaNR.text.toString(),
                    txvComNR.text.toString(),"-", spnTipoReembolsoNR.text.toString(), txvMotivoNR.text.toString(),
                    Util.getCurrentDate(), "-", txvFechaDesdeNR.text.toString(), Util.getCurrentDate(), "P"))
        }

    }
}
