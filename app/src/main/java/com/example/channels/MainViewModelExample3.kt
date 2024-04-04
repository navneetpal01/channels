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

class MainViewModelExample3 : ViewModel() {
//    private var channel = Channel<Language>()

    private var channel : ReceiveChannel<Language> = Channel()

    init {
        viewModelScope.launch {
            //Will return type ReceiveChannel and whe don't have to close it either it can do that automatically
            //Can only send two at once and will wait until they get received and free up some space
            channel = produce(capacity = 2){
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
            Log.d("system", "${channel.receive()} ")
            delay(3000)
            Log.d("system", "_________")
            Log.d("system", "${channel.receive()} ")
            delay(3000)
            Log.d("system", "_________")
            Log.d("system", "${channel.receive()} ")
            delay(3000)
            Log.d("system", "_________")
            Log.d("system", "${channel.receive()} ")
            delay(3000)
            Log.d("system", "_________")
        }
    }
}

//TODO CHANNEL TYPES
// Buffered -> Done
// Conflated
// Rendezvous
// Unlimited