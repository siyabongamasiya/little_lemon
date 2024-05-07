package android.capstone.littlelemon.screens

import android.annotation.SuppressLint
import android.capstone.littlelemon.data.Item
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
import android.capstone.littlelemon.Repos.AppDatabase
import android.capstone.littlelemon.data.itemRoom
import android.capstone.littlelemon.profile
import android.capstone.littlelemon.utils.AppFunctions
import android.provider.ContactsContract.Profile
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController){
    val cont = LocalContext.current
    var category by remember {
        mutableStateOf("")
    }
    var search by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)

    val menuItems  by database.itemDao().getAll().observeAsState(listOf())


    Scaffold(
        topBar = {
            Header(withImage = true, navController = navController)

            }, content = {padding ->

                Column(modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())){


                        Hero(
                            Modifier
                                .background(Color(0xFF495E57))){newSearch ->
                            search = newSearch
                        }

                        Subsection{newCategory ->
                            category = newCategory
                        }

                    }
                    LazyColumn(modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxWidth()) {

                        items(menuItems){
                            if (category.equals(it.itemcategory,true)
                                || category.isBlank()) {

                                //create search term
                                val searchitem = "${it.itemName}" +
                                        " ${it.itemDescription} " +
                                        "${it.itemPrice} " +
                                        "${it.itemcategory}"

                                //check if search term has search
                                if (searchitem.contains(search,true)) {
                                    Item(item = it)
                                }
                            }
                        }
                    }
                }




        })


    LaunchedEffect(key1 = 0) {
        AppFunctions.getAndDisplayItems(cont)
    }
}

@Composable
fun Header(withImage : Boolean,navController: NavHostController?){
    Row(horizontalArrangement = Arrangement.SpaceEvenly) {

        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .weight(0.7f)
                .padding(5.dp)
                .size(40.dp))

        if (withImage){
            Image(painter = painterResource(id = R.drawable.profile),
                contentDescription = "profile",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp)
                    .weight(0.3f)
                    .clickable {
                        navController!!.navigate(profile.route)
                    }
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Hero(modifier: Modifier,onChangeSearch : (search : String) -> Unit){
    var (text,setText)  = rememberSaveable {
        mutableStateOf("")
    }

    Column(modifier = modifier) {
        Row(modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically) {

            Text(
                buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color(0xFFF4CE14),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp)) {
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
                        fontFamily = FontFamily.Default,)) {
                        append("We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.")
                    }
            },
                modifier = Modifier.weight(0.7f)
            )

            Image(painter = painterResource(id = R.drawable.hero),
                contentDescription = "hero",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .weight(0.3f))
        }

        TextField(
            value = text,
            onValueChange = {string ->
                setText(string)
                onChangeSearch(string)
            },
            placeholder = {
                          Text(text = "Search")
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
        )
    }


}

@Composable
fun Subsection(onChangeCategory : (category : String) -> Unit){
    Column(modifier = Modifier
        .padding(start = 20.dp, end = 20.dp)
        .fillMaxWidth()) {

        Text(text = "Order for delivery",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
            ),
            textAlign = TextAlign.Center,
        )

        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.horizontalScroll(rememberScrollState(),
                true)) {

            MenuButton("Starters",onChangeCategory)
            MenuButton("Mains",onChangeCategory)
            MenuButton("Desserts",onChangeCategory)
            MenuButton("Sides",onChangeCategory)

        }
        Divider(modifier = Modifier
            .background(Color.Black)
            .height(2.dp))
    }

}

@Composable
fun MenuButton(name : String,onChangeCategory : (category : String) -> Unit){
    Row(modifier = Modifier.padding(top = 5.dp, end = 5.dp)) {

        Button(onClick = {
            onChangeCategory(name)
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF495E57)
            ),) {

            Text(
                text = name,
                maxLines = 1,
            )

        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Item(item : itemRoom){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )
                ){
                    append(item.itemName + "\n")
                }

                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                )
                ){
                    append(item.itemDescription + "\n")
                }

                withStyle(style = SpanStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.ExtraBold,
                )
                ){
                    append("$${item.itemPrice}" + "\n")
                }
            },
            modifier = Modifier
                .padding(5.dp)
                .weight(0.7f)
        )


        GlideImage( model = item.itemimage,contentDescription = "item image",
            modifier = Modifier
                .size(50.dp)
                .weight(0.3f),
            contentScale = ContentScale.Fit )
    }

    Divider(modifier = Modifier
        .background(Color.Black)
        .height(1.dp))
}

