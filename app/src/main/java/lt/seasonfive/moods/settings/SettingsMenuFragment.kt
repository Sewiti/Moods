package lt.seasonfive.moods.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import lt.seasonfive.moods.R
import lt.seasonfive.moods.databinding.FragmentSettingsMenuBinding


class SettingsMenuFragment : Fragment() {
    private lateinit var binding: FragmentSettingsMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_settings_menu, container, false)

        return binding.root
    }
}