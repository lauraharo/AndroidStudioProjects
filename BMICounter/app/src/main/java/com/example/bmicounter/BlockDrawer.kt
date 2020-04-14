package com.example.bmicounter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class BlockDrawer(context: Context?) : View(context) {
    var xCoordinates = arrayListOf<String>();
    var yCoordinates = arrayListOf<String>();
    var bmis = arrayListOf<String>();

    val references = arrayOf(
        "Severe Thinness < 16",
        "Moderate Thinness	16 - 17",
        "Mild Thinness	17 - 18.5",
        "Normal	18.5 - 25",
        "Overweight	25 - 30",
        "Obese Class I	30 - 35",
        "Obese Class II	35 - 40",
        "Obese Class III 40 <")

    val referenceColors = arrayOf(
        "#2F9CEC", //Severe Thinness	< 16
        "#2F9CB4", //Moderate Thinness	16 - 17
        "#2F9C8A", //Mild Thinness	17 - 18.5
        "#2F9C44", //Normal	18.5 - 25
        "#FFCF40", //Overweight	25 - 30
        "#FF9240", //Obese Class I	30 - 35
        "#FF5440", //Obese Class II	35 -
        "#FF172C" //Obese Class III 40 <
    )


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var paint = Paint();
        paint.textSize = 30F
        if(canvas != null){
            setReferenceValues(canvas)
        }

        for((index, x) in xCoordinates.withIndex()) {
            paint.setColor(Color.parseColor(getColorForBmiBlock(bmis.get(index).toFloat())))
            canvas?.drawRect(x.toFloat(), yCoordinates.get(index).toFloat(), x.toFloat() + 50F, 800F, paint)
            canvas?.drawText(bmis.get(index), x.toFloat()-10F, 830F, paint)
        }
    }

    fun setCoordinates(x: ArrayList<String>, y:ArrayList<String>){
        xCoordinates = x
        yCoordinates = y

    }

    fun setValues(newBmis: ArrayList<String>){
        bmis = newBmis
    }

    fun setReferenceValues(canvas: Canvas){
        var paint = Paint();
        var textPaint = Paint();
        textPaint.color = Color.BLACK
        textPaint.textSize = 30F
        var left = 100F
        var right = 150F
        var top = 900F
        var bottom = 950F
        for(i in 0..7){
            paint.setColor(Color.parseColor(referenceColors.get(i)))
            canvas?.drawRect(left, top, right, bottom, paint)
            canvas?.drawText(references.get(i), right + 20, bottom - 15, textPaint)
            top += 70
            bottom += 70
        }
    }

    fun getColorForBmiBlock(bmi: Float): String{
        var returnValue: String = ""
        if(bmi < 16){
            returnValue = referenceColors.get(0);
        }
        else if (bmi >= 16 && bmi < 17){
            returnValue = referenceColors.get(1);
        }
        else if (bmi >= 17 && bmi < 18.5){
            returnValue = referenceColors.get(2);
        }
        else if (bmi >= 18.5 && bmi < 25){
            returnValue = referenceColors.get(3);
        }
        else if (bmi >= 25 && bmi < 30){
            returnValue = referenceColors.get(4);
        }
        else if (bmi >= 30 && bmi < 35){
            returnValue = referenceColors.get(5);
        }
        else if (bmi >= 35 && bmi < 40){
            returnValue = referenceColors.get(6);
        }
        else if (bmi > 40){
            returnValue = referenceColors.get(7);
        }
        else{
            returnValue = "FFFFFFFF"
        }
        return returnValue
    }

}