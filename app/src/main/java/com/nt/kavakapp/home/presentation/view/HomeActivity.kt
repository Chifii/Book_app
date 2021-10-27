package com.nt.kavakapp.home.presentation.view

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.nt.kavakapp.R
import com.nt.kavakapp.base.BaseActivity
import com.nt.kavakapp.databinding.HomeScreenActivityLayoutBinding
import com.nt.kavakapp.home.core.domain.HomeModel
import com.nt.kavakapp.home.core.service.HomeRepository
import com.nt.kavakapp.home.presentation.presenter.HomePresenter


class HomeActivity : BaseActivity<HomeView, HomePresenter>(), HomeView {

    private var presenter = HomePresenter(this, HomeRepository())
    private lateinit var binding: HomeScreenActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.getScreen()
        binding = HomeScreenActivityLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        setupTabListener()
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.home_screen_activity_layout
    }

    override fun drawScreen(data: List<HomeModel?>, grid : Boolean) {
        val layoutManager: RecyclerView.LayoutManager = if (grid) {
            GridLayoutManager(this, 2, 1, false)
        } else {
            LinearLayoutManager(this, 0, false)
        }

        binding.booksRecycler.layoutManager = layoutManager

        val homeAdapter = HomeBooksAdapter(data)

        binding.booksRecycler.adapter = homeAdapter
        showLayoutView()
    }

    fun setupTabListener() {
        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> presenter.showBooksByGenre(tab.position)
                    1 -> presenter.showBooksByGenre(tab.position)
                    2 -> presenter.showBooksByGenre(tab.position)
                    3 -> presenter.showBooksByGenre(tab.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}