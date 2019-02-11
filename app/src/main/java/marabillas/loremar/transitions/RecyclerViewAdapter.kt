package marabillas.loremar.transitions

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class RecyclerViewAdapter(private var data: ArrayList<String>?) : RecyclerView.Adapter<RecyclerViewAdapter.RVHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.activity_recyclerview_transitions_item, parent, false)
        return RVHolder(view)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {
        val text = holder.itemView.findViewById<TextView>(R.id.rv_trans_item_text)
        text.text = data?.get(position)
    }

    class RVHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}