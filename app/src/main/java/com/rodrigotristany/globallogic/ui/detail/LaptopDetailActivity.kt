package com.rodrigotristany.globallogic.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import com.rodrigotristany.globallogic.R
import com.rodrigotristany.globallogic.data.models.Laptop
import com.rodrigotristany.globallogic.ui.list.LaptopsListActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_laptop_detail.*
import java.lang.Exception

class LaptopDetailActivity : AppCompatActivity(), LaptopDetailMVP.View {

    private lateinit var laptop: Laptop

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laptop_detail)

        val cityJson = intent.extras?.getString(LaptopsListActivity.SELECTED_LAPTOP)
        cityJson?.let {
            try {
                laptop = Gson().fromJson(cityJson, Laptop::class.java)
                initializeView()
            } catch (ex: Exception) {
                this.showToast(ex.message)
                finish()
            }
        }
    }

    private fun initializeView() {
        laptop_name.text = laptop.title
        laptop_description.text = laptop.description
        Picasso.get()
            .load(laptop.image)
            .placeholder(R.drawable.ic_laptop_mac_black_48dp)
            .error(R.drawable.ic_error_outline_black_48dp)
            .into(laptop_image)
    }

    override fun showToast(message: String?) {
        Toast.makeText(this, message?: "Error", Toast.LENGTH_LONG).show()
    }
}
