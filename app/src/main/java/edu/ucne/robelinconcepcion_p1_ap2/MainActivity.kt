package edu.ucne.robelinconcepcion_p1_ap2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.robelinconcepcion_p1_ap2.presentation.navigation.RobelinConcepcion_P1_AP2NavHost
import edu.ucne.robelinconcepcion_p1_ap2.ui.theme.RobelinConcepcion_P1_AP2Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RobelinConcepcion_P1_AP2Theme {
                val navHostController = rememberNavController()
                RobelinConcepcion_P1_AP2NavHost(navHostController = navHostController)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RobelinConcepcion_P1_AP2Theme {
        Greeting("Android")
    }
}


