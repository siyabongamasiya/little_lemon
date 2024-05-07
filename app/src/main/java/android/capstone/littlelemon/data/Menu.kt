package android.capstone.littlelemon.data

import kotlinx.serialization.Serializable

@Serializable
data class Menu(
    val menu: List<Item>
)
