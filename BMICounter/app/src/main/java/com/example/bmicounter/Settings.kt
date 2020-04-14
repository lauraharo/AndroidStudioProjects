package com.example.bmicounter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class Settings : AppCompatActivity() {


    var newHeight: Float = 0.0f;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setHeightButton.setOnClickListener { view ->
            newHeight = heightInput.text.toString().toFloat();
        }

        returnButton.setOnClickListener { view ->
            finish()
        }

    }

        override fun finish() {
            val data = Intent();
            data.putExtra("newHeight", newHeight);
            setResult(RESULT_OK, data);
            super.finish()
        }

}
