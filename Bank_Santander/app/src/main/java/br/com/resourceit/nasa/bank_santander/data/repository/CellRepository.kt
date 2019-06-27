package br.com.resourceit.nasa.bank_santander.data.repository

import br.com.resourceit.nasa.bank_santander.data.remote.ApiService
import br.com.resourceit.nasa.bank_santander.data.remote.model.CellModel
import br.com.resourceit.nasa.bank_santander.data.remote.model.CellResponse
import br.com.resourceit.nasa.bank_santander.utils.BaseCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CellRepository {

    fun getCellsList(onResult:BaseCallback<List<CellModel>>) {
        ApiService.invoke().getCellsList()
            .enqueue(object : Callback<CellResponse> {
                override fun onFailure(call: Call<CellResponse>, t: Throwable) {
                    t.message?.let { onResult.onUnsuccessful(it) }
                }

                override fun onResponse(call: Call<CellResponse>, response: Response<CellResponse>) {
                    if(response.body() == null || !response.isSuccessful) onResult.onUnsuccessful(response.message())

                    response.body()?.let {
                        onResult.onSuccessful(it.cells)
                    }
                }

            })

    }

}