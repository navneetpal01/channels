package com.example.channels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModelExample4 : ViewModel() {
//    private var channel = Channel<Language>()

    private var channel : ReceiveChannel<Language> = Channel()

    init {
        viewModelScope.launch {
            //Will return type ReceiveChannel and whe don't have to close it either it can do that automatically
            //Only the latest one will be printed here - JavaScript
            channel = produce(capacity = CONFLATED){
                send(Language.Kotlin)
                Log.d("System", "Kotlin Sent!")
                send(Language.Java)
                Log.d("system", "Java Sent!")
                send(Language.Python)
                Log.d("system", "Python Sent!")
                send(Language.JavaScript)
                Log.d("system", "JavaScript Sent!")
            }
        }
    }

    init {
        viewModelScope.launch {
           channel.consumeEach {
               //Only the latest one will be printed here - JavaScript
               Log.d("system", "${it.name}")
           }
        }
    }
}

//TODO CHANNEL TYPES
// Buffered -> Done
// Conflated -> Done
// Rendezvous
// Unlimited