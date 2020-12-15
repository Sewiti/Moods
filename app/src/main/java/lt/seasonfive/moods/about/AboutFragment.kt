package lt.seasonfive.moods.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lt.seasonfive.moods.R


class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedinstancestate: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }
}
