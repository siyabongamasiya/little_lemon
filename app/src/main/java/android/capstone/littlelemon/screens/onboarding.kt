package android.capstone.littlelemon.screens

import android.capstone.littlelemon.R
import android.capstone.littlelemon.home
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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

        Box(modifier = Modifier
            .background(Color(0xFF495E57))
            .height(100.dp)
            .fillMaxWidth()
            ){

            Text(text = "Lets get to know you!!",
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
                )
        }


        TextField(value = firstName,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            onValueChange = {text -> firstName = text},
            placeholder = { Text(text = "Enter First Name")},
            modifier = Modifier.padding(20.dp))
        TextField(value = lastName,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            onValueChange = {text -> lastName = text},
            placeholder = {Text(text = "Enter Last Name")},
            modifier = Modifier.padding(20.dp))
        TextField(value = email,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            onValueChange = {text -> email = text},
            placeholder = {Text(text = "Enter Email")},
            modifier = Modifier.padding(20.dp))

        Button(onClick = {

            //validate if all fields are not blank
            if (
                firstName.isBlank() ||
                lastName.isBlank() ||
                email.isBlank()){
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14))) {
            Text(text = "Register")
        }
    }
}
