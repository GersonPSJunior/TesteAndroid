package br.com.resourceit.nasa.bank_santander.data.repository

import br.com.resourceit.nasa.bank_santander.data.remote.ApiService
import br.com.resourceit.nasa.bank_santander.data.remote.model.FundResponse
import br.com.resourceit.nasa.bank_santander.data.remote.model.ScreenModel
import br.com.resourceit.nasa.bank_santander.utils.BaseCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FundRepository {
    fun getFundList(onResult: BaseCallback<ScreenModel>){
        ApiService.invoke().getFundList()
            .enqueue(object : Callback<FundResponse> {
                override fun onFailure(call: Call<FundResponse>, t: Throwable) {
                    t.message?.let { onResult.onUnsuccessful(it) }
                }

                override fun onResponse(call: Call<FundResponse>, response: Response<FundResponse>) {
                    if(response.body() == null || !response.isSuccessful) onResult.onUnsuccessful(response.message())

                    response.body()?.let {
                        onResult.onSuccessful(it.screen)
                    }
                }

            })
    }
}