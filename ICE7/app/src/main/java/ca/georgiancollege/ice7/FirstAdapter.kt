package ca.georgiancollege.ice7

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.ice7.databinding.TextRowItemBinding

class FirstAdapter(private val dataSet: Array<TVShow>) :
    RecyclerView.Adapter<FirstAdapter.ViewHolder>() {

    // inner class ViewHolder which extends RecyclerView.ViewHolder
    class ViewHolder(val binding: TextRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    // configures the view holder and its layout
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the layout with view binding
        val binding = TextRowItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    // binds the data to the view holder
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Use view binding to set the text
        viewHolder.binding.title.text = dataSet[position].title
        viewHolder.binding.subTitle.text = dataSet[position].subTitle
    }

    override fun getItemCount() = dataSet.size
}
