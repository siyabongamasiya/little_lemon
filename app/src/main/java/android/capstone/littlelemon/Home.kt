package android.capstone.littlelemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun Home(){
    Column(verticalArrangement = Arrangement.Center,
        modifier = Modifier.background(Color.Green)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = "Home",
            textAlign = TextAlign.Center)
    }
}