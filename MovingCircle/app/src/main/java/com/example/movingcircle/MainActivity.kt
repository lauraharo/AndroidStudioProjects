package com.example.movingcircle

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener  {

    private lateinit var sensorManager: SensorManager
    private var mPos: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mPos = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    override fun onResume() {
        super.onResume()
        mPos?.also { position ->
            sensorManager.registerListener(this, position, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.getType() == Sensor.TYPE_ACCELEROMETER) {
            movingCircleView.drawOnSensorEvent(event)
        }
        val posX = event?.values?.get(0)
        val posY = event?.values?.get(1)
        val posZ = event?.values?.get(2)
        xCoordinatesText.text = "X: " + "%.2f".format(posX)
        yCoordinatesText.text = "Y: " + "%.2f".format(posY)
        zCoordinatesText.text = "Z: " + "%.2f".format(posZ)
    }
}
