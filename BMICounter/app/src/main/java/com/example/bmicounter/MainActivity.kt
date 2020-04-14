package com.example.bmicounter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.preference.PreferenceManager.getDefaultSharedPreferences
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    var REQUEST_CODE = 1;
    var bmiArray = ArrayList<String>()
    var height = 0F
    var weight = 0F


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                 super.onActivityResult(requestCode, resultCode, data)
                 if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
                     if (data!!.hasExtra("newHeight")) {
                         height = data.extras!!.getFloat("newHeight")/100
                         checkIfHeightIsSet(height*100)
                     }
                     else{
                         height = 0F
                     }
                 }

                 countBmiButton.setOnClickListener{ view ->
                     if(weightInput.text.isNotBlank()){
                         weight = weightInput.text.toString().toFloat()
                     }
                     val bmi = "%.2f".format(weight/(height*height))
                     bmiResultText2.text = bmi
                     bmiArray.add(bmi)
                     if(bmiArray.size > 10){
                         bmiArray = bmiArray.drop(1) as ArrayList<String>
                     }
                     setList("savedBmis", bmiArray)
                 }
             }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        checkIfHeightIsSet(height)

        savedBmisButton.setOnClickListener{view ->
            val intent = Intent(this@MainActivity, SavedBMIs::class.java)
            startActivity(intent)
        }
    }

    fun <T> setList(key: String?, list: List<T>?) {
        val gson = Gson()
        val json = gson.toJson(list)
        println("Json: " + json);
        set(key, json)
    }

    operator fun set(key: String?, value: String?) {
        val sharedPreferences = getDefaultSharedPreferences(applicationContext)
        println("Context: " + applicationContext);
        val editor: Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val intent = Intent(this@MainActivity, Settings::class.java)
        startActivityForResult(intent, REQUEST_CODE)
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun checkIfHeightIsSet(height: Float){
        if(height.toInt() == 0){
            heightTextView.text = "Height is not set, please set height in the settings tab."
        }
        else{
            heightTextView.text = "Height: " + height.toString() + " cm"
        }
    }
}
