package ca.georgiancollege.section1_ice9

import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.section1_ice9.databinding.TextRowItemBinding

// TVShowViewHolder is a ViewHolder class that holds a reference to the TextRowItemBinding
// and displays the TVShow data in the UI for each row
class TVShowViewHolder (val binding: TextRowItemBinding):
    RecyclerView.ViewHolder(binding.root)
{
        // This method binds the TVShow data to the UI elements in the ViewHolder
        fun bind(tvShow: TVShow)
        {
            // Sets the text for the title, genre, and rating TextView
            binding.title.text = tvShow.title
            binding.genre.text = tvShow.genre
            binding.rating.text = tvShow.rating.toString()
        }
}