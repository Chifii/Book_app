package com.nt.kavakapp.base

interface BaseView {
    fun showErrorView(statusCode: Int?)
    fun showLoadingView()
    fun showLayoutView()
}