package com.overall.developer.overrendicion.ui.reembolso.documentoList.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.overall.developer.overrendicion.R
import com.overall.developer.overrendicion.utils.realmBrowser.RealmBrowser

import kotlinx.android.synthetic.main.activity_documentos_list.*

class DocumentosListActivity : AppCompatActivity() {

    private var realmBrowser: RealmBrowser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documentos_list)
        setSupportActionBar(toolbar)



    }


    //region estadosActividad
    override fun onResume() {
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
    //endregion

}
