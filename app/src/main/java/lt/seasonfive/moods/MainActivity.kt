package lt.seasonfive.moods

import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import lt.seasonfive.moods.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var currentFragment: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navMenuItemSelect()
    }


    private fun navMenuItemSelect() {
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.journalFragment -> {
                    replaceFragment(JournalFragment())
                    true
                }
                R.id.statisticsFragment -> {
                    replaceFragment(StatisticsFragment())
                    true
                }
                R.id.calendarFragment -> {
                    replaceFragment(CalendarFragment())
                    true
                }
                R.id.settingsFragment -> {
                    replaceFragment(SettingsFragment())
                    true
                }
                else -> false
            }
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.navHostFragment, fragment)

        fragmentTransaction.commit()
    }
}