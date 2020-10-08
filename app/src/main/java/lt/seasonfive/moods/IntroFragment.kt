package lt.seasonfive.moods

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlinx.android.synthetic.*
import lt.seasonfive.moods.databinding.FragmentIntroBinding

class IntroFragment : Fragment() {

    private lateinit var binding: FragmentIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //TODO: CHECK IF FIRST LAUNCH
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_intro, container, false)

        binding.floatingActionButton.setOnClickListener {view: View ->
            view.findNavController().navigate(IntroFragmentDirections.actionIntroFragmentToJournalFragment())
        }

        return binding.root
    }
}