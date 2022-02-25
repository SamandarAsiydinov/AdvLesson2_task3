package uz.context.advlesson2_task3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uz.context.advlesson2_task3.R
import uz.context.advlesson2_task3.model.Model

class MyAdapter(
    context: Context,
    private val list: ArrayList<Model>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = list[position]

        if (holder is MyViewHolder) {
            holder.apply {
                textName.text = item.name
                textAge.text = item.age
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textName: TextView = view.findViewById(R.id.textName)
        val textAge: TextView = view.findViewById(R.id.textAge)
    }
}