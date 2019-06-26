package br.com.resourceit.nasa.bank_santander.data

import br.com.resourceit.nasa.bank_santander.utils.URL_SERVICE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiService {



    //
    companion object {
        operator fun invoke(): ApiService {
            val interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL_SERVICE)
                .build()
                .create(ApiService::class.java)
        }
    }
}