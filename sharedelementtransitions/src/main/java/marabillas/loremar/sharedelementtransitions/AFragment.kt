package marabillas.loremar.sharedelementtransitions

import android.os.Build
import android.os.Bundle
import android.transition.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionSet().apply {
            addTransition(ChangeBounds())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                addTransition(ChangeTransform())
                addTransition(ChangeImageTransform())
            }
            duration = 1000
        }
        enterTransition = Fade().apply { duration = 1000 }
        exitTransition = Fade().apply { duration = 1000 }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postponeEnterTransition()
        return inflater.inflate(R.layout.fragment_a, container, false).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                findViewById<FloatingActionButton>(R.id.button_a).transitionName = "shared"
            }
            doOnPreDraw { startPostponedEnterTransition() }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStart() {
        super.onStart()
        view?.postDelayed({
            activity?.findViewById<FloatingActionButton>(R.id.button_a)?.let {
                fragmentManager
                    ?.beginTransaction()
                    ?.setReorderingAllowed(true)
                    ?.addSharedElement(it, it.transitionName)
                    ?.replace(R.id.main, BFragment())
                    ?.commit()
            }
        }, 3000)
    }
}