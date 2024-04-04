package com.example.channels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class MainViewModelExample1 : ViewModel() {
    private var channel = Channel<Language>()

    init {
        viewModelScope.launch {
            Log.d("ViewModel", "Kotlin send")
            channel.send(Language.Kotlin)
            Log.d("ViewModel", "Java send")
            channel.send(Language.Java)
            channel.close()
        }
    }

    init {
        viewModelScope.launch {
            Log.d("ViewModel", "${channel.isClosedForReceive}")
            //This Receive function will only grab one element from the channel not more than that.
            Log.d("ViewModel", "${channel.receive()}")
            Log.d("ViewModel", "${channel.receive()}")
            Log.d("ViewModel", "${channel.isClosedForReceive}")
        }
    }
}


enum class Language{
    Kotlin,
    Java,
    Python,
    JavaScript
}