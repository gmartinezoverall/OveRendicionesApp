package com.overall.developer.overrendicion.ui.reembolso.reembolso.view

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.MenuItem
import com.github.florent37.awesomebar.AwesomeBar
import com.overall.developer.overrendicion.R
import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.ui.reembolso.reembolso.presenter.IReembolsoPresenter
import com.overall.developer.overrendicion.ui.reembolso.reembolso.presenter.ReembolsoPresenter
import com.overall.developer.overrendicion.ui.reembolso.reembolso.view.adapter.ReembolsoAdapter
import com.overall.developer.overrendicion.utils.realmBrowser.RealmBrowser

import kotlinx.android.synthetic.main.activity_reembolso.*
import kotlinx.android.synthetic.main.toolbar_reembolso.*
import xyz.sangcomz.stickytimelineview.RecyclerSectionItemDecoration
import xyz.sangcomz.stickytimelineview.TimeLineRecyclerView
import xyz.sangcomz.stickytimelineview.model.SectionInfo

class ReembolsoActivity : AppCompatActivity(), IReembolsoView,  NavigationView.OnNavigationItemSelectedListener
{
    internal lateinit var mPresenter: IReembolsoPresenter
    private var realmBrowser: RealmBrowser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reembolso)


        mPresenter = ReembolsoPresenter(this)
        mPresenter.getReembolsoList()


        val mToolbar: AwesomeBar = toolbarReembolso
        mToolbar.setOnMenuClickedListener { v -> drawer_layout_reembolso.openDrawer(Gravity.START) }

        val toggle = ActionBarDrawerToggle(this, drawer_layout_reembolso, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout_reembolso.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        if (drawer_layout_reembolso.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_reembolso.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_soliRend -> {
                // Handle the camera action
            }
            R.id.nav_sendResume -> {

            }
            R.id.nav_actContra -> {

            }
            R.id.nav_actCorreo -> {

            }
            R.id.nav_liqPend -> {

            }
            R.id.nav_reenbolso -> {

            }
            R.id.nav_sesion -> {

            }
        }

        drawer_layout_reembolso.closeDrawer(GravityCompat.START)
        return true
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

    override fun listReembolsoSuccess(reembolsoEntityList: ArrayList<ReembolsoEntity>)
    {
        val recyclerView: TimeLineRecyclerView = findViewById(R.id.rcvReembolso)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        recyclerView.addItemDecoration(getSectionCallback(reembolsoEntityList))
        recyclerView.adapter = ReembolsoAdapter(reembolsoEntityList, this)
    }

    //Get SectionCallback method
    private fun getSectionCallback(reembolsoList: List<ReembolsoEntity>): RecyclerSectionItemDecoration.SectionCallback {
        return object : RecyclerSectionItemDecoration.SectionCallback {
            //In your data, implement a method to determine if this is a section.
            override fun isSection(position: Int): Boolean = reembolsoList[position].fechaReembolso != reembolsoList[position - 1].fechaReembolso

            //Implement a method that returns a SectionHeader.
            override fun getSectionHeader(position: Int): SectionInfo? = SectionInfo(reembolsoList[position].fechaReembolso, reembolsoList[position].nombreConsultora)
        }
    }

}
