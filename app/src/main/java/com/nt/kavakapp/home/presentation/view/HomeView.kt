package com.nt.kavakapp.home.presentation.view

import com.nt.kavakapp.base.BaseView
import com.nt.kavakapp.home.core.domain.HomeModel

interface HomeView : BaseView {
    fun drawScreen(data: List<HomeModel?>, grid: Boolean)
}