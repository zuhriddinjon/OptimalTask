package uz.task.optimaltask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*
import uz.task.optimaltask.R
import java.util.*

class CustomAdapter(val modelList: List<Model>, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    ItemMoveCallback.ItemTouchHelperContract {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(modelList.get(position));
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.row_item, parent, false))
    }

    override fun getItemCount(): Int {
        return modelList.size;
    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(model: Model) {
            itemView.txt.text = model.name

            val id = context.resources.getIdentifier(
                model.name.toLowerCase(),
                "drawable",
                context.packageName
            )
            itemView.img.setImageResource(id)
        }
    }

    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(modelList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(modelList, i, i - 1)
            }
        }

        notifyItemMoved(fromPosition, toPosition)
    }

}