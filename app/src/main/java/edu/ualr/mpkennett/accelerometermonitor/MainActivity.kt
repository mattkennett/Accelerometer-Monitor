package edu.ualr.mpkennett.accelerometermonitor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(), SensorEventListener {
    // We need a SensorManager and a Sensor to get readings
    // from the accelerometer
    var mySensorManager: SensorManager? = null
    var myAccelerometer: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // We need to set up our SensorManager and Sensor in
        // onCreate().  Note that our View will not start getting
        // readings from the accelerometer until we run the
        // registerListener() method on our SensorManager
        mySensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        myAccelerometer = mySensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onResume() {
        super.onResume()

        // This function call will let our SensorManager begin receiving
        // readings from our accelerometer
        mySensorManager!!.registerListener(this, myAccelerometer, SensorManager.SENSOR_DELAY_UI)

    }

    override fun onPause() {
        super.onPause()

        // This function call releases the accelerometer to save
        // battery life.  When our app isn't in the foreground, we
        // don't need to continue receiving updates from the accelerometer
        mySensorManager!!.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // We have to implement this method because SensorEventListener
        // is an abstract class, but we don't need to be worried about
        // the accuracy changing on the accelerometer
    }

    override fun onSensorChanged(event: SensorEvent?) {
        // This is the function that will be updated every time our
        // accelerometer sends an update to our app
        Log.d("UALR_UTIL", "X Reading: " + event!!.values[0].toString())
        Log.d("UALR_UTIL", "Y Reading: " + event!!.values[1].toString())
        Log.d("UALR_UTIL", "Z Reading: " + event!!.values[2].toString())
    }
}
