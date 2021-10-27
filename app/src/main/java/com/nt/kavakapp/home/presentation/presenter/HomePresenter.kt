package com.nt.kavakapp.home.presentation.presenter

import com.nt.kavakapp.base.BasePresenter
import com.nt.kavakapp.home.core.domain.HomeModel
import com.nt.kavakapp.home.core.domain.ResultModel
import com.nt.kavakapp.home.core.service.HomeRepository
import com.nt.kavakapp.home.presentation.view.HomeView
import com.nt.kavakapp.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.HttpException

class HomePresenter(
    private var view: HomeView,
    private var repository: HomeRepository,
) : BasePresenter<HomeView>() {

    private val scopeRecovery by lazy { CoroutineScope(Job() + Dispatchers.Main) }
    private lateinit var data: ResultModel
    private lateinit var bestSellers: ResultModel
    private var booksBySection = mutableListOf<HomeModel>()

    fun getScreen() {
        scopeRecovery.launch {
            when (val response = repository.getScreenDataFromApi()) {
                is Result.Success -> {
                    data = response.data
                    getBestSellers()
                }
                is Result.Error -> {
                    renderError(response.exception)
                }
            }
        }
    }

    private fun renderError(ex: HttpException) {
        view.showErrorView(ex.code())
    }

    fun showBooksByGenre(genre: Int) {
        if (booksBySection.isNotEmpty()){
            booksBySection.clear()
        }
        when (genre) {
            0 -> {
                showBestSellers()
            }
            1 -> {
                data.results?.books?.forEach { book ->
                    if (book.genre == "History") {
                        booksBySection.add(book)
                    }
                }
                view.drawScreen(booksBySection, true)
            }
            2 -> {
                data.results?.books?.forEach { book ->
                    if (book.genre == "Business") {
                        booksBySection.add(book)
                    }
                }
                view.drawScreen(booksBySection, true)
            }
            3 -> {
                data.results?.books?.forEach { book ->
                    if (book.genre == "Science") {
                        booksBySection.add(book)
                    }
                }
                view.drawScreen(booksBySection, true)
            }
        }
    }

    private fun getBestSellers() {
        scopeRecovery.launch {
            when (val response = repository.getBestSellers()) {
                is Result.Success -> {
                    bestSellers = response.data
                    showBestSellers()
                }
                is Result.Error -> {
                    ResultModel(null)
                }
            }
        }
    }

    private fun showBestSellers() {
        bestSellers.let {
            it.results?.best_sellers?.forEach { bestSellerId ->
                data.results?.books?.forEach { book ->
                    if (book.isbn == bestSellerId) {
                        booksBySection.add(book)
                    }
                }
            }
            view.drawScreen(booksBySection, false)
        }
    }

}