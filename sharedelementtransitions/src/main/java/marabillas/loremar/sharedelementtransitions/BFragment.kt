package marabillas.loremar.sharedelementtransitions

import android.os.Build
import android.os.Bundle
import android.transition.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment

class BFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionSet().apply {
                addTransition(ChangeBounds())
                addTransition(Fade())
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    addTransition(ChangeTransform())
                    addTransition(ChangeImageTransform())
                }
            }
            .addTransition(ChangeBounds())
            .addTransition(Fade())
        sharedElementReturnTransition = Fade()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postponeEnterTransition()
        return inflater.inflate(R.layout.fragment_b, container, false).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                findViewById<ImageView>(R.id.image_b)?.transitionName = "shared"
            }
            doOnPreDraw { startPostponedEnterTransition() }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStart() {
        super.onStart()
        view?.postDelayed({
            activity?.findViewById<ImageView>(R.id.image_b)?.let {
                fragmentManager
                    ?.beginTransaction()
                    ?.setReorderingAllowed(true)
                    ?.addSharedElement(it, it.transitionName)
                    ?.replace(R.id.main, AFragment())
                    ?.commit()
            }
        }, 1000)
    }
}