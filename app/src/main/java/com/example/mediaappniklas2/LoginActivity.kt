package com.example.mediaappniklas2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mediaappniklas2.navcontroller.NavHost
import com.example.mediaappniklas2.ui.theme.MediaAppNiklas2Theme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            MediaAppNiklas2Theme{
                NavHost()
            }
        }
    }
}