package com.example.mediaappniklas2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mediaappniklas2.interfaces.LoginForm
import com.example.mediaappniklas2.ui.theme.MediaAppNiklas2Theme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            MediaAppNiklas2Theme{
                LoginForm()
            }
        }
    }
}