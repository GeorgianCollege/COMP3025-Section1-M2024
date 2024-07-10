package ca.georgiancollege.section1_ice8

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.section1_ice8.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TVShowViewModel by viewModels()

    private lateinit var dataManager: DataManager

    private val adapter = TVShowListAdapter{ tvShow: TVShow ->
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("tvShowId", tvShow.id)
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Firestore
        FirebaseFirestore.setLoggingEnabled(true)

        // creates an alias for the DataManager instance
        dataManager = DataManager.instance()

        binding.firstRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.firstRecyclerView.adapter = adapter

        // Observe the TVShows LiveData to update the UI
        viewModel.tvShows.observe(this) { tvShows ->
            adapter.submitList(tvShows)
        }

        viewModel.loadAllTVShows()

        binding.addButton.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume()
    {
        super.onResume()
        viewModel.loadAllTVShows()
    }
}