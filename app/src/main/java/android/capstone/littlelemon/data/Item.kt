package android.capstone.littlelemon.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    @SerialName("id")
    val id : String,
    @SerialName("title")
    val itemName : String,
    @SerialName("description")
    val itemDescription : String,
    @SerialName("price")
    val itemPrice : String,
    @SerialName("image")
    val image : String,
    @SerialName("category")
    val category : String
)
