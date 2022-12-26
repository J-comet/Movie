package hs.project.movie.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindActivity<T : ViewDataBinding>(@LayoutRes private val layoutResId: Int) : BaseActivity() {
    lateinit var binding : T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        init()
    }
    abstract fun init()
}