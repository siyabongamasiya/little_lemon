package android.capstone.littlelemon.screens

import android.capstone.littlelemon.Item
import android.capstone.littlelemon.MenuItems
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import android.capstone.littlelemon.R
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun Home(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){

        Header(true)
        Hero(MenuItems())

    }
}

@Composable
fun Header(withImage : Boolean){
    Row(horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.White)) {

        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .weight(0.7f)
                .size(40.dp))

        if (withImage){
            Image(painter = painterResource(id = R.drawable.profile),
                contentDescription = "profile",
                modifier = Modifier
                    .clip(CircleShape)
                    .weight(0.3f)
                    .size(80.dp)
                //.clickable {  }
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hero(items : MenuItems){
    var (text,setText)  = rememberSaveable {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .background(Color(0xFF495E57))
        .fillMaxWidth()) {
        Row(modifier = Modifier.padding(20.dp)) {

            Text(
                buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color(0xFFF4CE14),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp)) {
                    append("Little Lemon \n")
                }

                withStyle(style = SpanStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp)) {
                    append("Chicago \n\n")
                }

                    withStyle(style = SpanStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Default,
                        fontSize = 15.sp)) {
                        append("We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.")
                    }
            },
                modifier = Modifier.weight(0.5f)
            )

            Image(painter = painterResource(id = R.drawable.hero),
                contentDescription = "hero",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(180.dp)
                    .weight(0.5f))
        }

        TextField(
            value = text,
            onValueChange = {
                setText
            },
            placeholder = {
                          Text(text = "Search")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }

    ItemList(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()){
        val cont = LocalContext.current

        LazyColumn {
            //Toast.makeText(cont,"kk",Toast.LENGTH_LONG).show()
            items(items.list ){

                Item(item = it)

            }
        }
    }
}

@Composable
fun ItemList(modifier: Modifier,itemslist : @Composable() () -> Unit){
    Column(modifier = modifier) {
        Text(text = "Order for delivery",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Row() {
            MenuButton("Starters",
                Modifier
                    .padding(5.dp)
                    .weight(0.33f))
            MenuButton("Mains",
                Modifier
                    .padding(5.dp)
                    .weight(0.33f))
            MenuButton("Desserts",
                Modifier
                    .padding(5.dp)
                    .weight(0.33f))
            MenuButton("Sides",
                Modifier
                    .padding(5.dp)
                    .weight(0.33f))
        }

        Divider(modifier = Modifier
            .background(Color.Black)
            .height(2.dp))

        itemslist.invoke()
    }
}

@Composable
fun MenuButton(name : String,modifier: Modifier){
    Row(modifier = modifier) {

        Text(
            text = name,
            modifier = Modifier
                .background(Color(0xFF495E57), shape = CircleShape)
                .padding(8.dp)
        )
    }
}

@Composable
fun Item(item : Item){
    Row(modifier = Modifier.padding(top = 10.dp)) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                ){
                    append(item.itemName + "\n")
                }

                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp
                )
                ){
                    append(item.itemDescription + "\n")
                }

                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 15.sp
                )
                ){
                    append(item.itemPrice + "\n")
                }
            },
            modifier = Modifier.padding(5.dp)
                .weight(0.7f)
        )

        Image(painter = painterResource(id = R.drawable.hero),
            contentDescription = "item image",
            modifier = Modifier.size(50.dp)
                .weight(0.3f),
            contentScale = ContentScale.Fit)
    }

    Divider(modifier = Modifier
        .background(Color.Black)
        .height(1.dp))
}

