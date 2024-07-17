package ca.georgiancollege.section1_ice9

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ca.georgiancollege.section1_ice9.databinding.TextRowItemBinding

class TVShowListAdapter(private val onItemClicked: (TVShow) -> Unit):
ListAdapter<TVShow, TVShowViewHolder>(TVShowComparator())
{
    // Called when a new ViewHolder (Custom TableView Cell) is created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        // Inflate the layout for each row
        val binding = TextRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // Return a new ViewHolder instance with the binding object
        return TVShowViewHolder(binding)
    }

    // Called by the RecyclerView to display the data at a specific position
    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        // Get the TVShow at the current position
        val current = getItem(position)
        // Bind the TVShow data to the ViewHolder's UI elements
        holder.bind(current)
        // Set a click listener for the row item to handle item clicks
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
    }
}