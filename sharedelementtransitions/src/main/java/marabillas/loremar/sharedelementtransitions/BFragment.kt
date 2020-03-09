package marabillas.loremar.sharedelementtransitions

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform

class BFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_b, container, false).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                findViewById<ImageView>(R.id.image_b)?.transitionName = "shared"
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStart() {
        super.onStart()
        view?.postDelayed({
            activity?.findViewById<ImageView>(R.id.image_b)?.let {
                fragmentManager
                    ?.beginTransaction()
                    ?.addSharedElement(it, it.transitionName)
                    ?.replace(R.id.main, AFragment())
                    ?.commit()
            }
        }, 3000)
    }
}