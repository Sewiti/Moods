package lt.seasonfive.moods.journal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lt.seasonfive.moods.database.Mood
import lt.seasonfive.moods.databinding.JournalItemLayoutBinding

class JournalAdapter(val clickListener: MoodListener) : ListAdapter<DataItem,
        RecyclerView.ViewHolder>(MoodDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addAndSubmitList(list: List<Mood>?) {
        adapterScope.launch {
            val items = list?.map { DataItem.MoodItem(it) }

            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val item = getItem(position) as DataItem.MoodItem
                holder.bind(clickListener, item.mood)
            }
        }
    }

    class ViewHolder private constructor(val binding: JournalItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: MoodListener, item: Mood) {
            binding.mood = item
            binding.clickListener = clickListener
            binding.itemCardView.setOnLongClickListener {
                clickListener.onLongClick(item)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = JournalItemLayoutBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minumum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class MoodDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

class MoodListener(val clickListener: (moodId: Long) -> Unit, val longClickListener: (moodId: Long) -> Boolean) {
    fun onClick(mood: Mood) = clickListener(mood.id)
    fun onLongClick(mood: Mood) = longClickListener(mood.id)
}

sealed class DataItem {
    data class MoodItem(val mood: Mood): DataItem() {
        override val id = mood.id
    }

    abstract val id: Long
}