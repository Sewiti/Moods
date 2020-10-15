package lt.seasonfive.moods

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_intro.view.*
import java.util.*

class JournalAdapter(private var dates: List<String>, private var descriptions: List<String>,
                     private var images: List<Int>) : RecyclerView.Adapter<JournalAdapter.JournalViewHolder>() {

    inner class JournalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemDate: TextView = itemView.findViewById(R.id.item_dateTime)
        val itemDescription: TextView = itemView.findViewById(R.id.item_description)
        val itemImage: ImageView = itemView.findViewById(R.id.item_image)

        init {
            itemView.setOnClickListener { view: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "Clicked on item ${position + 1}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JournalViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.journal_item_layout, parent, false)
        return JournalViewHolder(v)
    }

    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        holder.itemDate.text = dates[position]
        holder.itemDescription.text = descriptions[position]
        holder.itemImage.setImageResource(images[position])
    }




}