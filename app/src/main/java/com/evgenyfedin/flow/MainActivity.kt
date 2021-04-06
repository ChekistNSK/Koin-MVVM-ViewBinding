package com.evgenyfedin.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.evgenyfedin.flow.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        this.viewModel.message.observe(this) { message ->
            viewBinding.textView.text = message
        }

        this.viewModel.messageVisible.observe(this) { visible ->
            viewBinding.checkBox.isChecked = visible
            viewBinding.textView.isVisible = visible
        }

        viewBinding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setMessageVisible(isChecked)
        }

    }
}