package lt.seasonfive.moods

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import lt.seasonfive.moods.databinding.FragmentJournalBinding


class JournalFragment : Fragment() {

    private lateinit var binding: FragmentJournalBinding
    private var datesList = mutableListOf<String>()
    private var descriptionsList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_journal, container, false
        )

        // Set recycler view adapter
        binding.journalItemList.adapter = JournalAdapter(datesList, descriptionsList, imagesList)

        postToList()

        return binding.root
    }

    private fun postToList() {
        for (i in 1..10)
            addItem("Kazkokia data", "Cia yra desc apie mano mood", R.mipmap.ic_launcher_round)
    }

    private fun addItem(date: String, description: String, image: Int) {
        datesList.add(date)
        descriptionsList.add(description)
        imagesList.add(image)
    }
}