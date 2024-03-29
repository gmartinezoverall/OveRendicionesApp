package com.overall.developer.overrendicion2.ui.reembolso.rendicionesList.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.omega_r.libs.omegarecyclerview.OmegaRecyclerView
import com.overall.developer.overrendicion2.R
import com.overall.developer.overrendicion2.data.model.entity.RendicionEntity
import com.overall.developer.overrendicion2.ui.reembolso.formularios.view.FormularioActivity
import com.overall.developer.overrendicion2.ui.reembolso.rendicionesList.presenter.DocumentosListPresenter
import com.overall.developer.overrendicion2.ui.reembolso.rendicionesList.presenter.IDocumentosListPresenter
import com.overall.developer.overrendicion2.ui.reembolso.rendicionesList.view.adapter.DocumentosAdapter
import com.overall.developer.overrendicion2.utils.realmBrowser.RealmBrowser

import kotlinx.android.synthetic.main.activity_documentos_list.*
import org.jetbrains.anko.startActivity


class DocumentosListActivity : AppCompatActivity(), IDocumentosListView {

    internal lateinit var mPresenter: IDocumentosListPresenter
    private var realmBrowser: RealmBrowser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documentos_list)
        //setSupportActionBar(toolbar)

        mPresenter = DocumentosListPresenter(this)

        mPresenter.setDocumentosList()

        fab.setOnClickListener {
            startActivity<FormularioActivity>()
        }

    }

    override fun listRendicionSuccess(rendicionEntityList: ArrayList<RendicionEntity>) {

        val omegaRecyclerView = findViewById<OmegaRecyclerView>(R.id.recycler_view_contacts)
        val adapter = DocumentosAdapter(this, rendicionEntityList)
        adapter.notifyDataSetChanged()
        omegaRecyclerView.adapter = adapter

    }

    //region estadosActividad
/*    override fun onResume() {
        super.onResume()
        realmBrowser = RealmBrowser()
        realmBrowser!!.start()
        realmBrowser!!.showServerAddress(this)

    }

    override fun onStop() {
        super.onStop()

        if (realmBrowser != null) {
            realmBrowser!!.stop()
        }
    }
    //endregion*/

    //region Functions

    fun deleteRendicion(codRendicion: String){  mPresenter.deleteRendicion(codRendicion)   }

    //endregion

}
