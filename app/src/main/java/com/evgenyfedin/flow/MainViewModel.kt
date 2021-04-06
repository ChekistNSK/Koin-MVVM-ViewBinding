package com.evgenyfedin.flow

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _message = MutableLiveData<String>().apply { value = "Android LiveData" }
    val message: LiveData<String> get() = _message

    private val _messageVisible = MutableLiveData<Boolean>().apply { value = true }
    val messageVisible: LiveData<Boolean> get() = _messageVisible

    @MainThread
    fun setUserMessage(msg: String) {
        _message.value = msg
    }

    @MainThread
    fun setMessageVisible(visible: Boolean) {
        _messageVisible.value = visible
    }
}