package uz.task.optimaltask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.task.optimaltask.databinding.RowItemBinding
import java.util.*

class CustomAdapter(private val models: List<Model>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(models[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    class ViewHolder(private val binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Model) {
            binding.txt.text = model.name

            val id = itemView.resources.getIdentifier(
                model.name.lowercase(Locale.getDefault()),
                "drawable",
                itemView.context.packageName
            )
            binding.img.setImageResource(id)
        }
    }

    interface IClickListener {
        fun onClick(pos: Int, aView: View)
    }

}