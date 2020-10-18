package lt.seasonfive.moods

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import lt.seasonfive.moods.databinding.FragmentIntroGetStartedBinding
import lt.seasonfive.moods.databinding.FragmentIntroWelcomeBinding

class IntroWelcomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentIntroWelcomeBinding>(
            inflater,
            R.layout.fragment_intro_welcome,
            container,
            false
        )

        binding.nextFAB.setOnClickListener {view ->
            view.findNavController().navigate(IntroWelcomeFragmentDirections.actionIntroWelcomeFragmentToIntroGetStartedFragment())
        }

        return binding.root
    }
}