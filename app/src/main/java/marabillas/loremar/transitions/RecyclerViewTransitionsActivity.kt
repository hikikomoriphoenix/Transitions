package marabillas.loremar.transitions

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewTransitionsActivity : AppCompatActivity() {
    private val data = ArrayList<String>()
    private var rv: RecyclerView? = null
    private var root: ViewGroup? = null
    private val handler = Handler(Looper.getMainLooper())
    private val transitionSet: TransitionSet = TransitionSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_transitions)

        rv = findViewById(R.id.recyclerview)
        rv?.adapter = RecyclerViewAdapter(data)
        rv?.layoutManager = LinearLayoutManager(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            transitionSet.addTransition(Slide(Gravity.TOP))
        }
        transitionSet.addTransition(Fade())
        transitionSet.duration = 1500
    }

    override fun onStart() {
        super.onStart()

        root = findViewById(android.R.id.content)
        fillData()
    }

    private fun fillData() {
        handler.postDelayed({
            TransitionManager.beginDelayedTransition(root, transitionSet)
            val arr = listOf("RED", "BLUE", "GREEN", "YELLOW", "BLACK", "WHITE", "INDIGO", "SCARLET")
            data.addAll(arr)
            rv?.adapter?.notifyDataSetChanged()
            emptyData()
        }, 2000)
    }

    private fun emptyData() {
        handler.postDelayed({
            TransitionManager.beginDelayedTransition(root, transitionSet)
            data.clear()
            rv?.adapter?.notifyDataSetChanged()
            fillData()
        }, 2000)
    }
}