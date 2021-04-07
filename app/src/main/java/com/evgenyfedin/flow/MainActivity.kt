package com.evgenyfedin.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.evgenyfedin.flow.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.message.collect { message ->
                    viewBinding.textView.text = message
                }
            }
        }

        lifecycleScope.launch {
            viewModel.messageVisible.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { visible ->
                    viewBinding.checkBox.isChecked = visible
                    viewBinding.textView.isInvisible = !visible
                }
        }

        viewBinding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setMessageVisible(isChecked)
        }
    }
}