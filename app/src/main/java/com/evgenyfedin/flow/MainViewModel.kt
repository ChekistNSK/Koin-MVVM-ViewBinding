package com.evgenyfedin.flow

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _message = MutableStateFlow("Android LiveData")
    val message: StateFlow<String> get() = _message.asStateFlow()

    private val _messageVisible = MutableStateFlow(true)
    val messageVisible: StateFlow<Boolean> get() = _messageVisible.asStateFlow()

    fun setUserMessage(msg: String) {
        _message.value = msg
    }

    fun setMessageVisible(visible: Boolean) {
        _messageVisible.value = visible
    }
}