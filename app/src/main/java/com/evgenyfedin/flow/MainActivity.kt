package com.evgenyfedin.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.evgenyfedin.flow.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)

        viewModel.message.onEach { message ->
            viewBinding.textView.text = message
        }.launchThenStarted(lifecycleScope)

        viewModel.messageVisible.onEach { visible ->
            viewBinding.checkBox.isChecked = visible
            viewBinding.textView.isVisible = visible
        }.launchThenStarted(lifecycleScope)

        viewBinding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setMessageVisible(isChecked)
        }
    }
}