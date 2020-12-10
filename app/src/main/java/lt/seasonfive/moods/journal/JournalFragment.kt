package lt.seasonfive.moods.journal

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import lt.seasonfive.moods.MoodActivity
import lt.seasonfive.moods.R
import lt.seasonfive.moods.database.Objects.MoodDatabase
import lt.seasonfive.moods.databinding.FragmentJournalBinding


class JournalFragment : Fragment() {
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


        journalViewModel.navigateToMood.observe(viewLifecycleOwner, { mood ->
            mood?.let {
                val intent = Intent(context, MoodActivity::class.java)

                intent.putExtra("id", mood.id)
                intent.putExtra("date", mood.date)
                intent.putExtra("description", mood.description)
                intent.putExtra("moodQuality", mood.moodQuality)

                startActivityForResult(intent, 2)

                journalViewModel.doneNavigating()
            }
        })



        val adapter = JournalAdapter(MoodListener(
            clickListener = { moodId ->
                journalViewModel.onMoodClicked(moodId)
            },
            longClickListener = { moodId ->
                val dialogClickListener =
                    DialogInterface.OnClickListener { dialog, which ->
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                journalViewModel.deleteMood(moodId)
                                dialog.dismiss()
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {
                                dialog.cancel()
                            }
                        }
                    }

                val builder = AlertDialog.Builder(requireContext(), R.style.MoodsAlertDialog)
                builder.setMessage("Delete this Mood?")
                    .setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener)
                    .setCancelable(true)
                    .show()

                true
            }
        ))

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