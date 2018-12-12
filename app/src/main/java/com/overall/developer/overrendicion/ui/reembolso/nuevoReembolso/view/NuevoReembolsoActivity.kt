package com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.view

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import com.github.florent37.awesomebar.AwesomeBar
import com.overall.developer.overrendicion.R
import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.data.model.entity.TipoReembolso
import com.overall.developer.overrendicion.ui.liquidacion.view.formularios.FormularioActivity
import com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.presenter.INuevoReembolsoPresenter
import com.overall.developer.overrendicion.ui.reembolso.nuevoReembolso.presenter.NuevoReembolsoPresenter
import com.overall.developer.overrendicion.utils.Util
import kotlinx.android.synthetic.main.activity_reembolso.*
import kotlinx.android.synthetic.main.content_nuevo_reembolso.*
import kotlinx.android.synthetic.main.toolbar_reembolso.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat

class NuevoReembolsoActivity : AppCompatActivity(), INuevoReembolsoView
{
    internal lateinit var mPresenter: INuevoReembolsoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_reembolso)

        mPresenter = NuevoReembolsoPresenter(this)

        mPresenter.changeStateAllReembolso()//cambia el estado de todos los reembolsos a False

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

        lytFecha.setOnClickListener{alterCalendarView()}

        btnSaveNR.setOnClickListener{
            mPresenter.saveDateNewRefund(ReembolsoEntity("-", "-", "-", txvDniNR.text.toString(),
                                         txvNombreNR.text.toString(), "0", spnTipoMonedaNR.text.toString(),
                                         txvComNR.text.toString(),"-", spnTipoReembolsoNR.text.toString(), txvMotivoNR.text.toString(),
                                         Util.getCurrentDate(), "-", txvFechaDesdeNR.text.toString(), Util.getCurrentDate(), "P"))
        }

        val mToolbar: AwesomeBar = toolbarReembolso
        mToolbar.setOnMenuClickedListener { drawer_layout_reembolso.openDrawer(Gravity.START) }
        val txvTittleToolbar = txvTitulo
        txvTittleToolbar.text = "Nuevo Reembolso"
    }

    override fun insertNRSuccess()
    {
        startActivity<FormularioActivity>()
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
                txvFechaDesdeNR.setTypeface(null, Typeface.BOLD)
                txvFechaDesdeNR.setTextColor(ContextCompat.getColor(this, R.color.colorBlack))
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
}
