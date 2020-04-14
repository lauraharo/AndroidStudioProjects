package com.example.bmicounter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_saved_b_m_is.*
import kotlinx.android.synthetic.main.activity_settings.*

class SavedBMIs : AppCompatActivity() {

    lateinit var blockDrawer: BlockDrawer
    var xCoordinates = arrayListOf<String>();
    var yCoordinates = arrayListOf<String>();
    val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_b_m_is)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val bmiEntry = sharedPref.getString("savedBmis", "")
        val itemType = object : TypeToken<List<String>>() {}.type
        val bmiEntries= gson.fromJson<ArrayList<String>>(bmiEntry, itemType)

        for((index, entry) in bmiEntries.withIndex()){
            val x = (index + 1) * 100F
            val y = 800F - (entry.toFloat()*15F)
            xCoordinates.add(x.toString())
            yCoordinates.add(y.toString())
        }

        blockDrawer = BlockDrawer(this)
        setContentView(blockDrawer);
        blockDrawer.setCoordinates(xCoordinates, yCoordinates);
        blockDrawer.setValues(bmiEntries);

    }
}
