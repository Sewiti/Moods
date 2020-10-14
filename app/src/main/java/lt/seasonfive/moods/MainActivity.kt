package lt.seasonfive.moods

import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import lt.seasonfive.moods.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        navMenuItemSelect()

        fabSelect()
    }


    private fun fabSelect() {
        binding.bottomFab.setOnClickListener {view: View ->
            Toast.makeText(this, "Fab button pressed", Toast.LENGTH_LONG).show()
        }
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


    fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.navHostFragment, fragment)

        fragmentTransaction.commit()
    }
}