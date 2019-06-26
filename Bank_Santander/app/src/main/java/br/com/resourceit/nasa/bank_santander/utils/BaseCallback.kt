package br.com.resourceit.nasa.bank_santander.utils


interface BaseCallback<T> {
    fun onSuccessful(value : T)
    fun onUnsuccessful(error: String)
}