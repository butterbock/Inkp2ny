package com.inkp2ny.smartinkopslista

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.inkp2ny.smartinkopslista.ui.screens.MainScreen
import com.inkp2ny.smartinkopslista.ui.theme.SmartInkopslistaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val app = application as SmartInkopslistaApp

        setContent {
            SmartInkopslistaTheme {
                MainScreen(
                    repository = app.shoppingRepository,
                    budgetPreferences = app.budgetPreferences,
                )
            }
        }
    }
}
