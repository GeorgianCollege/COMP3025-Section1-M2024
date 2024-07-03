package ca.georgiancollege.ice7

import androidx.room.*

interface TVShowDao {
    // Inserts a new TVShow into the database
    // If the TVSHow with the same ID already exists, it will be replaced
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TVShow)

    // Updates an existing TVShow in the database
    @Update
    suspend fun update(tvShow: TVShow)

    // Deletes a TVShow from the database
    @Delete
    suspend fun delete(tvShow: TVShow)

    // Retrieves all TVShows from the database
    @Query("SELECT * FROM tv_shows")
    suspend fun getAllTVShows(): List<TVShow>

    // Retrieves a TVShow by its ID from the database
    @Query("SELECT * FROM tv_shows WHERE id = :id")
    suspend fun getTVShowById(id: Int): TVShow?

}