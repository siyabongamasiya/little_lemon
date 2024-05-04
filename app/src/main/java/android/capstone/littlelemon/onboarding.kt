package android.capstone.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoarding(navController: NavHostController){
    var firstName by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val context = LocalContext.current

        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo image")
        Text(text = "Lets get to know you!!",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color(0xFF495E57))
                .fillMaxWidth())

        TextField(value = firstName,
            onValueChange = {text -> firstName = text},
            placeholder = { Text(text = "Enter First Name")},
            modifier = Modifier.padding(20.dp))
        TextField(value = lastName,
            onValueChange = {text -> lastName = text},
            placeholder = {Text(text = "Enter Last Name")},
            modifier = Modifier.padding(20.dp))
        TextField(value = email,
            onValueChange = {text -> email = text},
            placeholder = {Text(text = "Enter Email")},
            modifier = Modifier.padding(20.dp))

        Button(onClick = {
            if (firstName.isBlank() || lastName.isBlank() || email.isBlank()){
                Toast.makeText(context,"Please fill all the texts!!",Toast.LENGTH_SHORT).show()
            }else{
                val sharedPreferences = context.getSharedPreferences("loggin", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean("loggedin?",true)
                editor.putString("firstname",firstName)
                editor.putString("lastname",lastName)
                editor.putString("email",email)
                editor.apply()

                navController.navigate(home.route)
            }
                 },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14))) {
            Text(text = "Register")
        }
    }
}
