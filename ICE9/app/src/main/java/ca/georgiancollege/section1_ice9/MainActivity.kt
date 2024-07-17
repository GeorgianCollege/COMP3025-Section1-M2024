package ca.georgiancollege.section1_ice9

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.section1_ice9.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TVShowViewModel by viewModels()
    private lateinit var auth: FirebaseAuth
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

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
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

        binding.addTvShowFAB.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }

        // set up the logout button
        binding.logoutButton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onResume()
    {
        super.onResume()
        viewModel.loadAllTVShows()
    }
}