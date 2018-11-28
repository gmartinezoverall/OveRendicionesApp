package com.overall.developer.overrendicion.ui.reembolso.reembolso.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import com.overall.developer.overrendicion.R
import com.overall.developer.overrendicion.data.model.entity.ReembolsoEntity
import com.overall.developer.overrendicion.ui.reembolso.reembolso.presenter.IReembolsoPresenter
import com.overall.developer.overrendicion.ui.reembolso.reembolso.presenter.ReembolsoPresenter

import kotlinx.android.synthetic.main.activity_reembolso.*
import xyz.sangcomz.stickytimelineview.RecyclerSectionItemDecoration
import xyz.sangcomz.stickytimelineview.TimeLineRecyclerView
import xyz.sangcomz.stickytimelineview.model.SectionInfo

class ReembolsoActivity : AppCompatActivity(), IReembolsoView
{
    internal lateinit var mPresenter: IReembolsoPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reembolso)
        setSupportActionBar(toolbar)

        mPresenter = ReembolsoPresenter(this)

        val recyclerView: TimeLineRecyclerView = findViewById(R.id.rcvReembolso)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        recyclerView.addItemDecoration(getSectionCallback(mPresenter.getReembolsoList()))

    }

    override fun onLoginResult(message: String)
    {

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
