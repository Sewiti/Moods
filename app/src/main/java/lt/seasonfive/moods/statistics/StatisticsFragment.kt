package lt.seasonfive.moods.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import lt.seasonfive.moods.R
import lt.seasonfive.moods.database.Objects.MoodDatabase
import lt.seasonfive.moods.databinding.FragmentStatisticsBinding


class StatisticsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentStatisticsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_statistics, container, false
        )

        val application = requireNotNull(this.activity).application

        val dataSource = MoodDatabase.getInstance(application).moodDatabaseDao

        val viewModelFactory = StatisticsViewModelFactory(dataSource, application)

        val statisticsViewModel =
            ViewModelProvider(this, viewModelFactory).get(StatisticsViewModel::class.java)

        statisticsViewModel.onCreate()

        statisticsViewModel.total.observe(viewLifecycleOwner, {
            it?.let {
                binding.totalTextView.text = getString(R.string.total_stats, it)
            }
        })

        statisticsViewModel.avgMoodAllTime.observe(viewLifecycleOwner, {
            it?.let {
                val img = getImage(it)
                binding.imageViewAvgMood.setImageResource(img)
            }
        })

        statisticsViewModel.avgMoodLast7D.observe(viewLifecycleOwner, {
//            Toast.makeText(context,"Avg: " + it, Toast.LENGTH_LONG).show()

            it?.let {

                val img = getImage(it)
                binding.imageViewAvgMood7D.setImageResource(img)
            }
        })

        binding.lifecycleOwner = this

        return binding.root
    }

    fun getImage(mood: Float): Int {
        var img = R.drawable.mood_poop

        if (mood <= 0.5) {
            img = R.drawable.mood_wink
        } else if (mood <= 1.5) {
            img = R.drawable.mood_love
        } else if (mood <= 2.5) {
            img = R.drawable.mood_relaxed
        } else if (mood <= 3.5) {
            img = R.drawable.mood_desperate
        }

        return img
    }
}
