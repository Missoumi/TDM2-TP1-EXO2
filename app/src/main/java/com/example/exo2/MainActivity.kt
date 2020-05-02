package com.example.exo2

import android.Manifest
import android.app.PendingIntent.getActivity
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.InputStream
import java.util.*


class MainActivity : AppCompatActivity() {

    val FILE_NAME = "intervention1.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var listIntervention = findViewById<LinearLayout>(R.id.interventionList)
        var intervention1 = Intervention(1, Date(2019, 4, 4), "Abdellatif", "Type 1")
        var intervention2 = Intervention(2, Date(2018, 5, 14), "Missoumi", "Type 2")
        var intervention3 = Intervention(3, Date(2017, 5, 24), "Attala", "Type 3")
        val gson = Gson()
        val interventions = arrayOf(intervention1, intervention2, intervention3)
        if (isExternalStorageAvailable) {
            val arrayInterventionType = object : TypeToken<Array<Intervention>>() {}.type
            val path = Environment.getExternalStorageDirectory()

            val permissionCheck = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE), 200)
            }
//            val jsonInter: String = gson.toJson(interventions, arrayInterventionType)
//
//            val file = File(path, FILE_NAME)
//            file.printWriter().use { out ->
//                out.println(jsonInter)
//            }

            val inputStream: InputStream = File(path, FILE_NAME).inputStream()
            val json = inputStream.bufferedReader().use { it.readText() }

            Toast.makeText(this, json, Toast.LENGTH_SHORT).show()

            val inters:Array<Intervention> = gson.fromJson(json, arrayInterventionType)
            inters.forEachIndexed { index, inter ->
                    listIntervention.addView(InterventionView(this,
                        _id = inter.id,
                        _date = inter.date,
                        _nom = inter.nom,
                        _type = inter.type
                    ))
            }


        } else {
            Toast.makeText(this, "we can't use storage check permission", Toast.LENGTH_SHORT).show()
        }


    }

    private val isExternalStorageAvailable: Boolean
        get() {
            val extStorageState = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED.equals(extStorageState)
        }

}

