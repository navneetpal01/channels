package com.example.channels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModelExample2 : ViewModel() {
//    private var channel = Channel<Language>()

    private var channel : ReceiveChannel<Language> = Channel()

    init {
        viewModelScope.launch {
            //Will return type ReceiveChannel and whe don't have to close it either it can do that automatically
            //It is a hot flow if their isn't a receiver the value will be lost
            channel = produce {
                send(Language.Kotlin)
                send(Language.Java)
                send(Language.Python)
                send(Language.JavaScript)
            }
        }
    }

    init {
        viewModelScope.launch {
            Log.d("ViewModel", "${channel.isClosedForReceive}")
            channel.consumeEach {
                Log.d("ViewModel", "${it.name}")
            }
            Log.d("ViewModel", "${channel.isClosedForReceive}")
        }
    }
}

