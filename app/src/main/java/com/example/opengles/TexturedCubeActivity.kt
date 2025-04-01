package com.example.opengles

import android.app.Activity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.example.opengles.cube.TexturedCubeRenderer
import com.example.opengles.cube.TouchGLSurfaceView

class TexturedCubeActivity : Activity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val view = TouchGLSurfaceView(this)
        val renderer = TexturedCubeRenderer(this)
        view.setRenderer(renderer)
        setContentView(view)


    }

}
