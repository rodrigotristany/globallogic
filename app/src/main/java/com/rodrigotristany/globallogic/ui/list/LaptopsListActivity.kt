package com.rodrigotristany.globallogic.ui.list

import android.media.audiofx.PresetReverb
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.rodrigotristany.globallogic.R
import com.rodrigotristany.globallogic.internal.App
import com.rodrigotristany.globallogic.ui.list.di.DaggerLaptopsListComponent
import kotlinx.android.synthetic.main.activity_laptops_list.*
import javax.inject.Inject

class LaptopsListActivity : AppCompatActivity(), LaptopsListMVP.View {

    @Inject
    lateinit var presenter : LaptopsListMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laptops_list)
        initInjector()
        initRecyclerView()
        initListeners()
        presenter.setView(this)
    }

    private fun initListeners() {

    }

    private fun initRecyclerView() {

    }

    private fun initInjector() {
        DaggerLaptopsListComponent.builder()
            .applicationComponent((application as App).applicationComponent)
            .build()
            .inject(this)
    }

    override fun showLoader() {
        indeterminateBar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        indeterminateBar.visibility = View.GONE
    }

    override fun showToast(message: String?) {
        Toast.makeText(this, message?: "Error", Toast.LENGTH_SHORT).show()
    }
}
