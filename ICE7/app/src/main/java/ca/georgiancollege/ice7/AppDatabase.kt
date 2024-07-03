package ca.georgiancollege.ice7

import android.content.Context
import androidx.room.*

// This annotation marks the AppDatabase class as a Room database
@Database(entities = [TVShow::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase()
{
    // This method returns the TVShowDao interface
    abstract fun tvShowDao(): TVShowDao

    companion object
    {
        // Volatile annotation ensures that the value of m_instance is always up-to-date
        @Volatile
        private var m_instance: AppDatabase? = null

        // This function returns the singleton instance of the AppDatabase
        // if the instance is null it creates a new instance using Room.databaseBuilder
        fun getDatabase(context: Context): AppDatabase
        {
            // multiple  threads can ask for the database at the same time,
            // so we use synchronized to ensure only one thread can access the database at a time
            return m_instance ?: synchronized(this) {
                // creates a new instance of the database if it doesn't exist
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "media_database" // name of the database file
                ).build()
                m_instance = instance

                return instance
            }
        }
    }
}