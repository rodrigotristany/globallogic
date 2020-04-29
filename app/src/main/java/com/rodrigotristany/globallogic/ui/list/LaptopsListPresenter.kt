package com.rodrigotristany.globallogic.ui.list

import android.content.Context
import com.rodrigotristany.globallogic.R
import com.rodrigotristany.globallogic.data.models.Laptop
import com.rodrigotristany.globallogic.domain.GetLaptopsUseCase
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class LaptopsListPresenter
@Inject constructor(private val getLaptopsUseCase: GetLaptopsUseCase,
                    private val context: Context) : LaptopsListMVP.Presenter {

    private val TAG : String = LaptopsListPresenter::javaClass.name
    private var view : LaptopsListMVP.View? = null

    override fun initLaptops() {
        this.view?.hideRetry()
        this.view?.showLoader()
        getLaptopsUseCase.execute(object : DisposableObserver<List<Laptop>>(){
            override fun onComplete() {
                view?.hideLoader()
            }

            override fun onNext(laptops: List<Laptop>) {
                view?.hideLoader()
                if(laptops.isEmpty()) {
                    view?.showToast(context.getString(R.string.no_matching_data_from_server))
                    view?.showRetry()
                }
                view?.showLaptopsList(laptops)
            }

            override fun onError(e: Throwable) {
                view?.hideLoader()
                var message = when(e) {
                    is java.net.UnknownHostException -> context.getString(R.string.network_error)
                    else -> e.message
                }
                view?.showToast(message)
                view?.showRetry()
            }
        })
    }

    override fun setView(view: LaptopsListMVP.View) {
        this.view = view
    }
}
