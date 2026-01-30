package com.example.week2native

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.week2native.view.HomeScreen
import com.example.week2native.ui.theme.Week2NativeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week2NativeTheme {
                HomeScreen()
            }
        }
    }
}
