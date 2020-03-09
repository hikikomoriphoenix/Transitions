package marabillas.loremar.sharedelementtransitions

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlin.math.roundToInt

class BFragment : DaggerFragment() {
    @Inject
    lateinit var aFragment: AFragment
    private val imgId = View.generateViewId()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform(requireContext()).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                pathMotion = MaterialArcMotion()
            }
            scrimColor = Color.TRANSPARENT
            duration = 1000
        }
        enterTransition = Fade().apply { duration = 1000 }
        exitTransition = Hold().apply { duration = 1000 }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*return inflater.inflate(R.layout.fragment_b, container, false).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                findViewById<ImageView>(R.id.image_b)?.transitionName = "shared"
            }
        }*/
        return FrameLayout(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            background = ColorDrawable(Color.WHITE)
            val img = FrameLayout(requireContext()).apply {
                layoutParams = ViewGroup.LayoutParams(
                    MATCH_PARENT,
                    320 * resources.displayMetrics.density.roundToInt()
                )
                background = ResourcesCompat.getDrawable(resources, R.drawable.city_buildings, null)
                id = imgId
            }
            ViewCompat.setTransitionName(img, "shared")
            addView(img)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStart() {
        super.onStart()
        view?.postDelayed({
            activity?.findViewById<FrameLayout>(imgId)?.let {
                fragmentManager
                    ?.beginTransaction()
                    ?.addSharedElement(it, it.transitionName)
                    ?.replace(R.id.main, aFragment)
                    ?.commit()
            }
        }, 3000)
    }
}