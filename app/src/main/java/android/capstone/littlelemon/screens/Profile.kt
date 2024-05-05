package android.capstone.littlelemon.screens

import android.content.Context
import android.media.tv.TvContract.Channels.Logo
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun Profile(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        
        Header(withImage = false)
        Details()

    }

}

@Preview
@Composable
fun Details(){
    Box(modifier = Modifier.fillMaxSize()
        .background(Color.White)) {
        val context = LocalContext.current
        val sharedPreferences = context.getSharedPreferences("loggin", Context.MODE_PRIVATE)
        val firstname = sharedPreferences.getString("firstname","firstname")
        val lastname = sharedPreferences.getString("lastname","lastname")
        val email = sharedPreferences.getString("firstname","email")

        Column(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()) {
            Text(text = "Personal Information",
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                ),
                modifier = Modifier.padding(bottom = 80.dp)
            )

            detail(detail = "First Name", detailValue = firstname!!)
            detail(detail = "Last Name", detailValue = lastname!!)
            detail(detail = "Email", detailValue = email!!)



        }

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14)),
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 50.dp)
                .align(Alignment.BottomEnd),
            onClick = { /*TODO*/ }) {
            Text(text = "Logout")
        }
    }




}

@Composable
fun detail(detail : String,detailValue: String){
    Column(modifier = Modifier.padding(bottom = 30.dp)) {
        Text(
            text = detail,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            )
        )

        Text(
            text = detailValue!!,
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp
            ),
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(5.dp)
                )
                .fillMaxWidth()
                .padding(5.dp)
        )
    }
}
