package android.capstone.littlelemon.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "items")
data class itemRoom(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val itemName : String = "item",
    val itemDescription : String = "description",
    val itemPrice : String = "price",
    val itemimage : String = "url",
    val itemcategory : String = "category"
)
