package com.nt.kavakapp.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.nt.kavakapp.databinding.BaseLayoutBinding

abstract class BaseActivity<V : BaseView, P : BasePresenter<V>> : AppCompatActivity(), BaseView {
    private lateinit var binding : BaseLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BaseLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
    }

    protected abstract fun getLayoutResourceId(): Int

    protected fun getStringResource(res: Int): String {
        return getString(res)
    }

    override fun showLoadingView() {
        runOnUiThread {
            binding.baseErrorView.visibility = View.GONE
            binding.baseLayoutView.visibility = View.GONE
            binding.baseLoadingSpinner.visibility = View.VISIBLE
        }
    }

    override fun showLayoutView() {
        runOnUiThread {
            binding.baseErrorView.visibility = View.GONE
            binding.baseLayoutView.visibility = View.VISIBLE
            binding.baseLoadingSpinner.visibility = View.GONE
        }
    }

    override fun showErrorView(statusCode: Int?) {
        runOnUiThread {
            binding.baseErrorView.visibility = View.VISIBLE
            binding.baseLayoutView.visibility = View.GONE
            binding.baseLoadingSpinner.visibility = View.GONE
        }
    }

    protected fun finishFlow() {
        finish()
    }
}
