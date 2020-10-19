package lt.seasonfive.moods.Intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import lt.seasonfive.moods.R
import lt.seasonfive.moods.databinding.FragmentIntroGetStartedBinding

class IntroGetStartedFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentIntroGetStartedBinding>(
            inflater,
            R.layout.fragment_intro_get_started,
            container,
            false
        )

        binding.nextFAB.setOnClickListener {
            activity?.finish()
        }

        return binding.root
    }
}