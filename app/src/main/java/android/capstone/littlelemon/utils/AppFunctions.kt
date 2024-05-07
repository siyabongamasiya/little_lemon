package android.capstone.littlelemon.utils

import android.capstone.littlelemon.Repos.AppDatabase
import android.capstone.littlelemon.data.Item
import android.capstone.littlelemon.data.Menu
import android.capstone.littlelemon.data.itemRoom
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

object AppFunctions {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    var database : AppDatabase? = null


    suspend fun getAndDisplayItems(context: Context) {
        //fetching data from api
        var response : Map<String, List<Item>> = client.
        get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body()


        //storing in room
        if (database == null){
            //clear database
            val database = AppDatabase.getDatabase(context)
            database.itemDao().deleteAll()

            //get items from api
            response.get("menu")?.forEach {
                val itemroom = itemRoom(
                    itemName = it.itemName,
                    itemDescription = it.itemDescription,
                    itemPrice = it.itemPrice,
                    itemimage = it.image,
                    itemcategory = it.category)

                //insert items
                database.itemDao().insert(itemroom)

            }
        }else{
            //clear database
            database!!.itemDao().deleteAll()

            //get items from api
            response.get("menu")?.forEach {
                val itemroom = itemRoom(
                    itemName = it.itemName,
                    itemDescription = it.itemDescription,
                    itemPrice = it.itemPrice,
                    itemimage = it.image,
                    itemcategory = it.category)

                //insert items
                database!!.itemDao().insert(itemroom)
            }
        }
    }

    fun logout(context: Context){
        val sharedPreferences = context.getSharedPreferences("loggin", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

}