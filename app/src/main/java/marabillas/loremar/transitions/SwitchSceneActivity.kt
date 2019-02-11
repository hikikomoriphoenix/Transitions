package marabillas.loremar.transitions

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.TransitionManager
import android.view.ViewGroup

class SwitchSceneActivity : AppCompatActivity() {
    private var root: ViewGroup? = null
    private val transition = ChangeBounds()
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch_scenes_a)
    }

    override fun onStart() {
        super.onStart()

        root = findViewById(android.R.id.content)
        switchToSceneB()
    }

    private fun switchToSceneA() {
        val targetScene = Scene.getSceneForLayout(root, R.layout.activity_switch_scenes_a, this)
        handler.postDelayed({
            TransitionManager.go(targetScene, transition)
            switchToSceneB()
        }, 2000)
    }

    private fun switchToSceneB() {
        val targetScene = Scene.getSceneForLayout(root, R.layout.activity_switch_scenes_b, this)
        handler.postDelayed({
            switchToSceneA()
            TransitionManager.go(targetScene, transition)
        }, 2000)
    }
}