package ca.georgiancollege.section1_ice9

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.util.Log
import kotlinx.coroutines.tasks.await

class DataManager private constructor()
{
    private val db: FirebaseFirestore = Firebase.firestore

    companion object
    {
        private const val TAG = "DataManager"

        @Volatile
        private var m_instance: DataManager? = null

        fun instance (): DataManager
        {
            if(m_instance == null)
            {
                synchronized(this)
                {
                    if (m_instance == null) {
                        m_instance = DataManager()
                    }
                }
            }
            return m_instance!!
        }
    }

    // Function to insert a TVShow in Firestore
    suspend fun insert(tvShow: TVShow){
        try
        {
            db.collection("tvShows").document(tvShow.id).set(tvShow).await()
        }
        catch(e: Exception)
        {
            Log.e(TAG, "Error inserting TVShow: ${e.message}", e)
        }
    }

    // Function to update a TVShow in Firestore
    suspend fun update(tvShow: TVShow){
        try
        {
            db.collection("tvShows").document(tvShow.id).set(tvShow).await()
        }
        catch(e: Exception)
        {
            Log.e(TAG, "Error updating TVShow: ${e.message}", e)
        }
    }

    // Function to delete a TVShow from Firestore
    suspend fun delete(tvShow: TVShow) {
        try
        {
            db.collection("tvShows").document(tvShow.id).delete().await()
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting TVShow: ${e.message}", e)
        }
    }

    // Function to get all TVShows from Firestore
    suspend fun getAllTVShows(): List<TVShow> {
        return try {
            val result = db.collection("tvShows").get().await()
            result?.toObjects(TVShow::class.java) ?: emptyList()
        }
        catch(e: Exception)
        {
            Log.e(TAG, "Error getting TVShows: ${e.message}", e)
            emptyList()
        }
    }

    // Function to get a TVShow by ID from Firestore
    suspend fun getTVShowById(id: String): TVShow? {
        return try {
            val result = db.collection("tvShows").document(id).get().await()
            result?.toObject(TVShow::class.java)
        }
        catch (e: Exception)
        {
            Log.e(TAG, "Error getting TVShow by ID: ${e.message}", e)
            null
        }
    }

    // User CRUD Operations
    // Function to insert a User in Firestore
    suspend fun insertUser(user: User){
        try
        {
            db.collection("users").document(user.id).set(user).await()
        }
        catch(e: Exception)
        {
            Log.e(TAG, "Error inserting User: ${e.message}", e)
        }
    }

    // Function to get a User by ID
    suspend fun getUserById(id: String): User? {
        return try {
            val result = db.collection("users").document(id).get().await()
            result?.toObject(User::class.java)
        }
        catch (e: Exception)
        {
            Log.e(TAG, "Error getting User by ID: ${e.message}", e)
            null
        }
    }
}