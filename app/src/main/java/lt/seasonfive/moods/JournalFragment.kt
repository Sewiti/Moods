package lt.seasonfive.moods

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import lt.seasonfive.moods.Data.Objects.RoomDatabase
import lt.seasonfive.moods.Models.Mood
import lt.seasonfive.moods.databinding.FragmentJournalBinding


class JournalFragment : Fragment() {

    private lateinit var binding: FragmentJournalBinding
    private var moods = mutableListOf<Mood>()

    lateinit var database: RoomDatabase
    var disposable: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_journal, container, false
        )

        database = RoomDatabase.getInstance(requireContext())!!

        postToList()

        // Set recycler view adapter
        binding.journalItemList.adapter = JournalAdapter(moods)

        return binding.root
    }

    private fun postToList() {
        disposable = database.getMoodsDao()
            .getAllMoods()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                moods.clear()

                for (v in it)
                    moods.add(v)

                disposable = null
            }, {})

    //        for (i in 1..10)
//            addItem("Kazkokia data", "Cia yra desc apie mano mood", R.mipmap.ic_launcher_round)
    }

//    private fun addItem(date: String, description: String, image: Int) {
//        datesList.add(date)
//        descriptionsList.add(description)
//        imagesList.add(image)
//    }
}