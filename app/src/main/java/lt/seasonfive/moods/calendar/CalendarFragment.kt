package lt.seasonfive.moods.calendar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import lt.seasonfive.moods.MoodActivity
import lt.seasonfive.moods.R
import lt.seasonfive.moods.database.Mood
import lt.seasonfive.moods.database.Objects.MoodDatabase
import lt.seasonfive.moods.databinding.FragmentCalendarBinding
import lt.seasonfive.moods.journal.JournalAdapter
import lt.seasonfive.moods.journal.MoodListener

class CalendarFragment : Fragment() {
    private lateinit var binding: FragmentCalendarBinding
    private var moods = mutableListOf<Mood>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentCalendarBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_calendar, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = MoodDatabase.getInstance(application).moodDatabaseDao

        val viewModelFactory = CalendarViewModelFactory(dataSource, application)

        val calendarViewModel =
            ViewModelProvider(this, viewModelFactory).get(CalendarViewModel::class.java)

        calendarViewModel.onCreate()

        binding.calendarView.setOnDateChangeListener {view, year, month, dayOfMonth ->
            calendarViewModel.onDateSelected(view, year, month, dayOfMonth)
        }

        calendarViewModel.navigateToMood.observe(viewLifecycleOwner, { mood ->
            mood?.let {
                val intent = Intent(context, MoodActivity::class.java)

                intent.putExtra("id", mood.id)
                intent.putExtra("date", mood.date)
                intent.putExtra("description", mood.description)
                intent.putExtra("moodQuality", mood.moodQuality)

                startActivityForResult(intent, 2)

                calendarViewModel.doneNavigating()
            }
        })


        val adapter = JournalAdapter(MoodListener { moodId ->
            calendarViewModel.onMoodClicked(moodId)
        })

        binding.calendarItemList.adapter = adapter

        calendarViewModel.moods.observe(viewLifecycleOwner, {
            it?.let {
                adapter.addAndSubmitList(it)
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }
}