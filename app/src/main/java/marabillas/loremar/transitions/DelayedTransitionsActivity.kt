package marabillas.loremar.transitions

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.TransitionManager
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat

class DelayedTransitionsActivity : AppCompatActivity() {
    private val handler = Handler(Looper.getMainLooper())
    private var root: ViewGroup? = null
    private var view: View? = null
    private var circleSize: Int? = null
    private var rectSize: Int? = null
    private var rectBg: Int? = null
    private var circleDrawable: Drawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delayed_transitions)

        val dm = resources.displayMetrics
        circleSize = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, dm))
        rectSize = Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300f, dm))

        rectBg = ResourcesCompat.getColor(resources, R.color.colorPrimary, null)
        circleDrawable = ResourcesCompat.getDrawable(resources, R.drawable.circle, null)
    }

    override fun onStart() {
        super.onStart()

        root = findViewById(android.R.id.content)
        view = findViewById(R.id.delayed_trans_view)
        toRect()
    }

    private fun toRect() {
        handler.postDelayed({
            TransitionManager.beginDelayedTransition(root)
            view?.background = rectBg?.let { ColorDrawable(it) }
            val params = view?.layoutParams
            params?.width = rectSize
            params?.height = rectSize
            view?.layoutParams = params
            toCircle()
        }, 2000)
    }

    private fun toCircle() {
        handler.postDelayed({
            TransitionManager.beginDelayedTransition(root)
            view?.background = circleDrawable
            val params = view?.layoutParams
            params?.width = circleSize
            params?.height = circleSize
            view?.layoutParams = params
            toRect()
        }, 2000)
    }
}