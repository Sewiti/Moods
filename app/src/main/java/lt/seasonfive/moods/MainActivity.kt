package lt.seasonfive.moods

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import lt.seasonfive.moods.calendar.CalendarFragment
import lt.seasonfive.moods.database.Mood
import lt.seasonfive.moods.database.MoodDao
import lt.seasonfive.moods.database.Objects.MoodDatabase
import lt.seasonfive.moods.intro.IntroActivity
import lt.seasonfive.moods.journal.JournalFragment
import lt.seasonfive.moods.settings.SettingsFragment
import lt.seasonfive.moods.databinding.ActivityMainBinding
import lt.seasonfive.moods.statistics.StatisticsFragment


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var database: MoodDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        database = MoodDatabase.getInstance(application).moodDatabaseDao

        if (checkIfFirstRun()) {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
        }

        replaceFragment(JournalFragment())
        setupBottomNav()
        setupFAB()
    }


    private fun setupFAB() {
        binding.bottomFab.setOnClickListener { view: View ->
            val intent = Intent(this, MoodActivity::class.java)
            startActivityForResult(intent, 1)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

//        Toast.makeText(baseContext, "RESULT" + requestCode, Toast.LENGTH_SHORT).show()

        if (resultCode != RESULT_OK)
            return

        val mood = Mood()
        val id = data?.extras?.getLong("id", -1L)!!
        if (id >= 0L) {
            mood.id = id
        }

        mood.date = data?.extras?.getLong("date")!!
        mood.description = data.extras?.getString("description")!!
        mood.moodQuality = data.extras?.getInt("moodQuality")!!

//        Toast.makeText(baseContext, "" + mood.date, Toast.LENGTH_SHORT).show()

        MainScope().launch {
            withContext(Dispatchers.IO) {
                if (id >= 0L)
                    database.update(mood)
                else
                    database.insert(mood)
            }
        }

//        when (requestCode) {
//            NEW_ITEM_REQUEST_CODE -> {
//                val mood = Mood()
//
//                mood.date = data?.extras?.getLong("date")!!
//                mood.description = data.extras?.getString("description")!!
//                mood.moodQuality = data.extras?.getInt("moodQuality")!!
//
//                MainScope().launch {
//                    withContext(Dispatchers.IO) {
//                        database.insert(mood)
//                    }
//                }
//            }
//            EDIT_ITEM_REQUEST_CODE -> {
//                MainScope().launch {
//                    withContext(Dispatchers.IO) {
//                        val id = data?.extras?.getLong("moodId")!!
//                        val mood = database.get(id)!!
//
//                        mood.date = data.extras?.getLong("date")!!
//                        mood.description = data.extras?.getString("description")!!
//                        mood.moodQuality = data.extras?.getInt("moodQuality")!!
//
//                        database.update(mood)
//                    }
//                }
//            }
//        }
    }
}