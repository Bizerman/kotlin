package com.example.kotlin_dz1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

val ItemBoxColor = compositionLocalOf { Color.Blue }
val GridWidth = compositionLocalOf {3}
class ListofNumbers : ViewModel() {
    val data = mutableStateListOf(1)
    val removed_data = ArrayDeque<Int>()
    fun addItem() {
        if (removed_data.isEmpty()) data.add(data.size + 1) else {
            data.add(removed_data.last())
            removed_data.remove(removed_data.last())
        }
    }
    fun deleteItem(item: Int){
        removed_data.add(item)
        data.remove(item)
    }

}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppContent()
        }
    }
}


