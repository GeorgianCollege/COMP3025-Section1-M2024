package ca.georgiancollege.ice7

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity annotation marks this class as a database table
@Entity(tableName = "tv_shows")
data class TVShow(
    // Primary key annotation marks this column as the primary key
    // and auto-generates the ID
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Column to store the title of the TVShow
    val title: String,

    // Column to store the genre of the TVShow
    val genre: String,

    // Column to store the rating of the TVShow
    val rating: Double
)

