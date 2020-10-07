package lt.seasonfive.moods

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.*
import lt.seasonfive.moods.databinding.FragmentIntroBinding

class IntroFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //TODO: CHECK IF FIRST LAUNCH
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentIntroBinding>(
            inflater, R.layout.fragment_intro, container, false)

        binding.floatingActionButton.setOnClickListener {view: View ->
            view.findNavController().navigate(IntroFragmentDirections.actionIntroFragmentToJournalFragment())
//            Navigation.createNavigateOnClickListener(R.id.journalFragment, null)
        }

        return binding.root
//        return inflater.inflate(R.layout.fragment_intro, container, false)
    }
}