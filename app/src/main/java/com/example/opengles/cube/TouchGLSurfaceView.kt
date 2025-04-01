package com.example.opengles.cube

import android.content.Context
import android.opengl.GLSurfaceView
import android.view.MotionEvent

class TouchGLSurfaceView(context: Context?) : GLSurfaceView(context) {
    private var renderer: TexturedCubeRenderer? = null

    var prevX: Float = 0f
    var prevY: Float = 0f
    var curX: Float = 0f
    var curY: Float = 0f
    var angleY: Float = 0f
    var angleX: Float = 0f

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //Log.i("터치 이벤트", "터치 이벤트발생");		
        val action = event.getAction()
        if (action == MotionEvent.ACTION_UP) {
            angleY = 0f
            angleX = angleY
            curY = angleX
            curX = curY
            prevY = curX
            prevX = prevY
            prevY = 0f
            prevX = prevY
        } else if (action == MotionEvent.ACTION_MOVE) {
            //Log.i("터치 이벤트", "Move 이벤트발생");			
            if (prevX == 0f && prevY == 0f) {
                prevX = event.getX()
                prevY = event.getY()
                return true
            }
            curX = event.getX()
            curY = event.getY()
            if (curX - prevX > 0) {
                this.renderer!!.angleY = (5.let { angleY += it; angleY })
            } else if (curX - prevX < 0) {
                this.renderer!!.angleY = (5.let { angleY -= it; angleY })
            }
            /*			if(curY - prevY >0) {				
        this.renderer.angleX=(angleX+=2);			
        }else if(curY - prevY <0) {// 위로				
        this.renderer.angleX=(angleX-=2);			}*/
        }
        return true
    }

    override fun setRenderer(renderer: Renderer?) {
        super.setRenderer(renderer)
        this.renderer = renderer as TexturedCubeRenderer
    }
}
