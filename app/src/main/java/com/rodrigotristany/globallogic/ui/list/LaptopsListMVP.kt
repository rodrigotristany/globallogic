package com.rodrigotristany.globallogic.ui.list

import com.rodrigotristany.globallogic.internal.mvp.BaseMVP

interface LaptopsListMVP {
    interface View : BaseMVP.View {

    }

    interface Presenter {
        fun laptops()
        fun setView(view: View)
    }
}
