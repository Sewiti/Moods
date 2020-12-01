package lt.seasonfive.moods

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import lt.seasonfive.moods.database.Mood
import lt.seasonfive.moods.databinding.FragmentCalendarBinding

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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)

        selectDate()

        return binding.root
    }

    private fun selectDate() {
        val cv = binding.calendarView

        cv.setOnDateChangeListener { view, year, month, dayOfMonth ->
            moods = mutableListOf()

//            postToList(month.toString(), dayOfMonth.toString())

//            binding.calendarItemList.adapter = JournalAdapter(moods)

            Toast.makeText(activity, "Paspaudei", Toast.LENGTH_SHORT).show()
        }
    }

//    private fun postToList(month: String, day: String) {
//        for (i in 1..5)
//            addItem(month, day, R.mipmap.ic_launcher_round)
//    }
//
//    private fun addItem(date: String, description: String, image: Int) {
//        datesList.add(date)
//        descriptionsList.add(description)
//        imagesList.add(image)
//    }
}