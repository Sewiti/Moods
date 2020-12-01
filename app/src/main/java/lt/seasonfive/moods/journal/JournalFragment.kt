package lt.seasonfive.moods.journal

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import lt.seasonfive.moods.database.Objects.MoodDatabase
import lt.seasonfive.moods.R
import lt.seasonfive.moods.databinding.FragmentJournalBinding
import lt.seasonfive.moods.MainActivity
import lt.seasonfive.moods.MoodActivity


class JournalFragment : Fragment() {
    // TODO: Grab this from MainActivity or export Constants to a separate file
    val EDIT_ITEM_REQUEST_CODE = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentJournalBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_journal, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = MoodDatabase.getInstance(application).moodDatabaseDao

        val viewModelFactory = JournalViewModelFactory(dataSource, application)

        val journalViewModel =
            ViewModelProvider(this, viewModelFactory).get(JournalViewModel::class.java)

        binding.journalViewModel = journalViewModel


        journalViewModel.navigateToMood.observe(viewLifecycleOwner, { mood ->
            mood?.let {
                val intent = Intent(context, MoodActivity::class.java)

                intent.putExtra("id", mood.id)
                intent.putExtra("date", mood.date)
                intent.putExtra("description", mood.description)
                intent.putExtra("moodQuality", mood.moodQuality)

                startActivityForResult(intent, EDIT_ITEM_REQUEST_CODE)

                journalViewModel.doneNavigating()
            }
        })


        val adapter = JournalAdapter(MoodListener { moodId ->
            journalViewModel.onMoodClicked(moodId)
        })

        binding.journalItemList.adapter = adapter

        journalViewModel.moods.observe(viewLifecycleOwner, {
            it?.let {
                adapter.addAndSubmitList(it)
            }
        })

        binding.lifecycleOwner = this


        return binding.root
    }
}