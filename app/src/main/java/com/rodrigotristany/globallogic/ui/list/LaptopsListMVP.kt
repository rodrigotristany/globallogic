package com.rodrigotristany.globallogic.ui.list

import com.rodrigotristany.globallogic.data.models.Laptop

interface LaptopsListMVP {
    interface View {
        fun showRetry()
        fun hideRetry()
        fun showLaptopsList(laptops: List<Laptop>)
        fun showLoader()
        fun hideLoader()
        fun showToast(message: String?)
    }

    interface Presenter {
        fun initLaptops()
        fun setView(view: View)
    }
}
