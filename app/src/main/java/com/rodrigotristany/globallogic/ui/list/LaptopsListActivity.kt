package com.rodrigotristany.globallogic.ui.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.rodrigotristany.globallogic.R
import com.rodrigotristany.globallogic.data.models.Laptop
import com.rodrigotristany.globallogic.internal.App
import com.rodrigotristany.globallogic.ui.detail.LaptopDetailActivity
import com.rodrigotristany.globallogic.ui.list.di.DaggerLaptopsListComponent
import kotlinx.android.synthetic.main.activity_laptops_list.*
import javax.inject.Inject

class LaptopsListActivity : AppCompatActivity(), LaptopsListMVP.View {

    @Inject
    lateinit var presenter : LaptopsListMVP.Presenter

    companion object {
        const val SELECTED_LAPTOP = "SELECTED_LAPTOP"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laptops_list)
        initInjector()
        initRecyclerView()
        initListeners()
        presenter.setView(this)
        presenter.initLaptops()
    }

    private fun initInjector() {
        DaggerLaptopsListComponent.builder()
            .applicationComponent((application as App).applicationComponent)
            .build()
            .inject(this)
    }

    private fun initRecyclerView() {
        laptops_recycler_view.itemAnimator = DefaultItemAnimator()
        laptops_recycler_view.setHasFixedSize(true)
        laptops_recycler_view.layoutManager = LinearLayoutManager(this)
    }

    private fun initListeners() {
        retry_btn.setOnClickListener {
            presenter.initLaptops()
        }
    }

    override fun showLaptopsList(laptops: List<Laptop>) {
        laptops_recycler_view.adapter = LaptopsListAdapter(laptops) {laptop ->
            openLaptopDetail(laptop)
        }
    }

    private fun openLaptopDetail(laptop: Laptop) {
        val resultIntent = Intent(this, LaptopDetailActivity::class.java)
        resultIntent.putExtra(SELECTED_LAPTOP, Gson().toJson(laptop))
        startActivity(resultIntent)
    }

    override fun showRetry() {
        retry_btn.visibility = View.VISIBLE
    }

    override fun hideRetry() {
        retry_btn.visibility = View.GONE
    }

    override fun showLoader() {
        indeterminate_bar.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        indeterminate_bar.visibility = View.GONE
    }

    override fun showToast(message: String?) {
        Toast.makeText(this, message?: "Error", Toast.LENGTH_LONG).show()
    }
}
