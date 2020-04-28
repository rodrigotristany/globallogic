package com.rodrigotristany.globallogic.internal.mvp

interface BaseMVP {
    interface View {
        fun showLoader()
        fun hideLoader()
        fun showToast(message: String?)
    }
}
