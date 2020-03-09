package marabillas.loremar.sharedelementtransitions

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlin.math.roundToInt

class AFragment : DaggerFragment() {
    @Inject
    lateinit var bFragment: BFragment

    private val fabId = View.generateViewId()

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
        return FrameLayout(requireContext()).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            background = ColorDrawable(Color.DKGRAY)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val fab = FrameLayout(requireContext()).apply {
                    val size = 56 * resources.displayMetrics.density
                    val params = FrameLayout.LayoutParams(size.roundToInt(), size.roundToInt())
                    params.bottomMargin = 56 * resources.displayMetrics.density.roundToInt()
                    params.rightMargin = 16 * resources.displayMetrics.density.roundToInt()
                    params.gravity = GravityCompat.END or Gravity.BOTTOM
                    layoutParams = params
                    background = ColorDrawable(Color.CYAN)
                    transitionName = "shared"
                    id = fabId
                }
                addView(fab)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStart() {
        super.onStart()
        view?.postDelayed({
            activity?.findViewById<FrameLayout>(fabId)?.let {
                fragmentManager
                    ?.beginTransaction()
                    ?.addSharedElement(it, it.transitionName)
                    ?.addToBackStack(null)
                    ?.replace(R.id.main, bFragment)
                    ?.commit()
            }
        }, 3000)
    }
}