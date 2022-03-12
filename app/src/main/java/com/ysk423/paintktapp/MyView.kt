package com.ysk423.paintktapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

//Viewを継承したクラス
class MyView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var path :Path = Path()//線を引く、図形を書く、グラフィック描画
    public var paint : Paint = Paint()//色とか太さ
    private var drawX:Float=0F
    private var drawY:Float=0F

    //onDraw
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.BLUE//色
        paint.style = Paint.Style.STROKE//描画のスタイルを先にする
        paint.strokeWidth = 20F//幅
        canvas?.drawPath(path,paint)
    }

    //実際の描画（押したとき、タッチしたとき）
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //タッチポジション(x,y座標)
        drawX = event!!.x
        drawY = event.y

        when(event.action){
            MotionEvent.ACTION_DOWN -> path.moveTo(drawX,drawY)
            MotionEvent.ACTION_MOVE -> path.lineTo(drawX,drawY)
        }

        //再描画を実行させる呪文
        invalidate()

        //return super.onTouchEvent(event)
        return true
    }

    //クリア処理
    fun clearCanvas(){
        path.reset()
        invalidate()
    }

}