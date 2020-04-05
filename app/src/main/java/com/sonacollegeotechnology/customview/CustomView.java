package com.sonacollegeotechnology.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View {

    private int shapeWidth = 300;
    private int shapeHeight = 300;
    private Paint paintShape;
    private String[] shapeValues = { "Square", "Circle" , "Triangle"};
    private int currentShapeIndex = 0;
    public CustomView(Context context , AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            currentShapeIndex = (++currentShapeIndex) % shapeValues.length;
            postInvalidate();
            return true;
        }
        return result;
    }

    public String getSelectedShape(){
        return shapeValues[currentShapeIndex];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paintShape = new Paint();
        paintShape.setStyle(Paint.Style.FILL);
        paintShape.setColor(Color.RED);
        paintShape.setTextSize(30);

        switch(currentShapeIndex){
            case 0 :
                canvas.drawRect(0,0,shapeWidth,shapeHeight,paintShape);
                break;
            case 1:
                canvas.drawCircle(shapeWidth / 2,shapeHeight / 2,shapeWidth / 2,paintShape);
                break;
            case 2:
                Path trianglePath = new Path();
                trianglePath.moveTo(0,shapeHeight);
                trianglePath.lineTo(shapeWidth,shapeHeight);
                trianglePath.lineTo(shapeWidth/2,0);
                canvas.drawPath(trianglePath,paintShape);
                break;
            default:
                Log.e(getContext().toString(),"Can't Draw");
        }
    }
}
