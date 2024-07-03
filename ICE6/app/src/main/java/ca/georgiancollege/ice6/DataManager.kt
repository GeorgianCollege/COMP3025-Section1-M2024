package ca.georgiancollege.ice6

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

/**
 * The DataManager is a Singleton and provides access to JSON Data
 */
class DataManager private constructor()
{
    /**
     * Get the text from a resource
     *
     * @param context The context (e.g., this)
     * @param resourceId The resource ID
     * @return The text from the resource
     */
    fun getTextFromResource(context: Context, resourceId: Int): String
    {
        return context.resources.openRawResource(resourceId)
            .bufferedReader()
            .use { it.readText()}
    }

    /**
     * Get the text from an asset
     *
     * @param context The context (e.g., this)
     * @param fileName The file name
     */
    fun getTextFromAsset(context: Context, fileName: String): String
    {
        return context.resources.assets.open(fileName)
            .bufferedReader()
            .use { it.readText()}
    }

    /**
     * Deserialize the JSON data into a list of type ContactModel
     *
     * @param context The context (e.g., this)
     * @return The list of type ContactModel
     */
    fun deserializeJSON(context: Context): List<ContactModel>?
    {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(List::class.java, ContactModel::class.java)
        val adapter: JsonAdapter<List<ContactModel>> = moshi.adapter(listType)
        val contactListRawString = getTextFromResource(context, R.raw.contacts)
        val contactList: List<ContactModel>? = adapter.fromJson(contactListRawString)
        return contactList
    }

    companion object
    {

        val instance: DataManager by lazy { DataManager() }

        /*
        private var m_instance: DataManager? = null

        fun instance(): DataManager
        {
            if (m_instance == null)
            {
                m_instance = DataManager()
            }
            return m_instance!!
        }

         */
    }
}