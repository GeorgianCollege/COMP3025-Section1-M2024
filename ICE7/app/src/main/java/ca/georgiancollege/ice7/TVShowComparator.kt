package ca.georgiancollege.ice7

import androidx.recyclerview.widget.DiffUtil

// This is a utility class that helps the RecyclerView Adapter determine
// how to efficiently update the list of TVShows when the data changes
class TVShowComparator: DiffUtil.ItemCallback<TVShow>()
{
    // this method checks if two TVShows have the same ID
    override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean
    {
        return oldItem.id == newItem.id
    }

    // this method checks if two TVShows have the same content
    override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean
    {
        return oldItem == newItem
    }
}