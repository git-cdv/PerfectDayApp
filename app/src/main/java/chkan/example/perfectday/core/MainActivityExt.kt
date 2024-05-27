package chkan.example.perfectday.core

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreenViewProvider


fun SplashScreenViewProvider.startExitAnimation() {
    val zoomX = ObjectAnimator.ofFloat(
        this.iconView,
        View.SCALE_X,
        0.4f,
        0.0f
    )
    zoomX.interpolator = OvershootInterpolator()
    zoomX.duration = 500L
    zoomX.doOnEnd { this.remove() }

    val zoomY = ObjectAnimator.ofFloat(
        this.iconView,
        View.SCALE_Y,
        0.4f,
        0.0f
    )
    zoomY.interpolator = OvershootInterpolator()
    zoomY.duration = 500L
    zoomY.doOnEnd { this.remove() }

    zoomX.start()
    zoomY.start()
}
