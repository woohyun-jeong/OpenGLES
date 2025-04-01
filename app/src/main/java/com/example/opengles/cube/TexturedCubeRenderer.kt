package com.example.opengles.cube

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.GLSurfaceView
import android.opengl.GLU
import com.example.opengles.R
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

open class TexturedCubeRenderer(context: Context) : GLSurfaceView.Renderer {
    private val context: Context?
    private val cube: MultiTexturedCube

    // Set the background color to black ( rgba ).
    override fun onSurfaceCreated(gl: GL10, eglConfig: EGLConfig?) {
        gl.glClearColor(1.0f, 1.0f, 1.0f, 0.5f) // Enable Smooth Shading, default not really needed.
        gl.glShadeModel(GL10.GL_SMOOTH) // Depth buffer setup.
        gl.glClearDepthf(1.0f) // Enables depth testing.
        gl.glEnable(GL10.GL_DEPTH_TEST) // The type of depth testing to do.
        gl.glDepthFunc(GL10.GL_LEQUAL) // Really nice perspective calculations.
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST)
    }

    var angleX: Float = 0f
    var angleY: Float = 0f

    init {
        this.context = context
        val bitmap = arrayOfNulls<Bitmap>(6)
        bitmap[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.test1)
        bitmap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.test2)
        bitmap[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.test3)
        bitmap[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.test4)
        bitmap[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.test6)
        bitmap[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.test6)
        cube = MultiTexturedCube(bitmap)
    }

    override fun onDrawFrame(gl: GL10) {        // Clears the screen and depth buffer.
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT) // Replace the current matrix with the identity matrix
        gl.glLoadIdentity() // Translates 4 units into the screen.
        gl.glTranslatef(0f, 0f, -10f)
        gl.glRotatef(20f, 1f, 0f, 0f) // 카메라를 향해 약간 기울여서 윗면이 보이도록 한다
        gl.glRotatef(angleX, 1f, 0f, 0f)
        gl.glRotatef(angleY, 0f, 1f, 0f) // Draw our scene.
        cube.draw(gl)
        //         angle += 5;
    }

    override fun onSurfaceChanged(
        gl: GL10,
        width: Int,
        height: Int
    ) {        // Sets the current view port to the new size.
        gl.glViewport(0, 0, width, height) // Select the projection matrix
        gl.glMatrixMode(GL10.GL_PROJECTION) // Reset the projection matrix
        gl.glLoadIdentity() // Calculate the aspect ratio of the window
        GLU.gluPerspective(
            gl,
            45.0f,
            width.toFloat() / height.toFloat(),
            0.1f,
            1000.0f
        ) // Select the modelview matrix
        gl.glMatrixMode(GL10.GL_MODELVIEW) // Reset the modelview matrix
        gl.glLoadIdentity()
    }
}
