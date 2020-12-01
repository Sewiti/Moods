package lt.seasonfive.moods

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import lt.seasonfive.moods.intro.IntroActivity
import lt.seasonfive.moods.journal.JournalFragment
import lt.seasonfive.moods.settings.SettingsFragment
import lt.seasonfive.moods.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if (checkIfFirstRun()) {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
        }

        replaceFragment(JournalFragment())
        setupBottomNav()
        setupFAB()
    }


    private fun setupFAB() {
        binding.bottomFab.setOnClickListener {view: View ->
            val intent = Intent(this, MoodActivity::class.java)
            startActivity(intent)
        }
    }


    private fun setupBottomNav() {
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.journalFragmentMenuItem -> {
                    replaceFragment(JournalFragment())
                    true
                }
                R.id.statisticsFragmentMenuItem -> {
                    replaceFragment(StatisticsFragment())
                    true
                }
                R.id.calendarFragmentMenuItem -> {
                    replaceFragment(CalendarFragment())
                    true
                }
                R.id.settingsFragmentMenuItem -> {
                    replaceFragment(SettingsFragment())
                    true
                }
                else -> false
            }
        }
    }


    fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.frameLayout, fragment)

        fragmentTransaction.commit()
    }

    private fun checkIfFirstRun(): Boolean {
        val PREFS_NAME = "moodsPrefsFile"
        val PREFS_VERSION_CODE = "version_code"
        val DOESNT_EXIST = -1

        val currentVersionCode = BuildConfig.VERSION_CODE

        val prefs = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedVersionCode = prefs!!.getInt(PREFS_VERSION_CODE, DOESNT_EXIST)

        if (currentVersionCode == savedVersionCode) {
            return false
        } else if (savedVersionCode == DOESNT_EXIST) {
            prefs.edit().putInt(PREFS_VERSION_CODE, currentVersionCode).apply()
        }

        return true
    }
}