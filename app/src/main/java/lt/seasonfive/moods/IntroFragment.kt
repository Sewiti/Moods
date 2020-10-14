package lt.seasonfive.moods

import android.content.Context
import android.opengl.Visibility
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
        super.onCreate(savedInstanceState)

        // Makes bottom navigation invisible
        (activity as MainActivity).binding.coordinatorBottom.visibility = View.GONE

        // Does not replace if the is started for the first time
        replaceFragment(JournalFragment())
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


    private fun replaceFragment(fragment: Fragment) {
        if (!checkIfFirstRun()) {
            val fragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()

            fragmentTransaction.replace(R.id.navHostFragment, fragment)

            fragmentTransaction.commit()
        }
    }


    private fun checkIfFirstRun(): Boolean {
        val PREFS_NAME = "moodsPrefsFile"
        val PREFS_VERSION_CODE = "version_code"
        val DOESNT_EXIST = -1

        val currentVersionCode = BuildConfig.VERSION_CODE

        val prefs = activity?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedVersionCode = prefs!!.getInt(PREFS_VERSION_CODE, DOESNT_EXIST)

        if (currentVersionCode == savedVersionCode) {
            return false
        } else if (savedVersionCode == DOESNT_EXIST) {
            prefs.edit().putInt(PREFS_VERSION_CODE, currentVersionCode).apply()
        }

        return true
    }
}