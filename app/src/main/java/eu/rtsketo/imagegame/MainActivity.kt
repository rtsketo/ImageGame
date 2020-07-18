package eu.rtsketo.imagegame

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.WindowInsets
import android.widget.Button
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams
import androidx.constraintlayout.widget.ConstraintLayout.generateViewId
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var dir = 1

        val img = ImageView(this)
            .apply { id = View.generateViewId()
                layoutParams = LayoutParams(dp(120), dp(120))
                setImageResource(R.drawable.ic_launcher_foreground) }

        val btn = Button(this).apply {
            layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            id = View.generateViewId()
            setOnClickListener { dir *= -1
                img.animate().setDuration(200)
                    .translationXBy(dir * 100f) }
            text = "Click Me!" }

        setContentView(ConstraintLayout(this).also {
            it.addView(btn); it.addView(img)
            ConstraintSet().apply {
                clone(it)

                connect(img.id, TOP, PARENT_ID, TOP)
                connect(img.id, LEFT, PARENT_ID, LEFT)
                connect(img.id, BOTTOM, PARENT_ID, BOTTOM)
                connect(img.id, RIGHT, PARENT_ID, RIGHT)


                connect(btn.id, LEFT, PARENT_ID, LEFT)
                connect(btn.id, RIGHT, PARENT_ID, RIGHT)
                connect(btn.id, TOP, img.id, BOTTOM, dp(50))
            }.applyTo(it)
        })
    }
}

infix fun Context.dp(x: Number) = x.run {
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, toFloat(),
        resources.displayMetrics).toInt()
        .coerceAtLeast(1) }