package com.example.exo2

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.intervention_view.view.*
import java.util.*

class InterventionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0,
    _id: Int,
    _date : Date,
    _nom:String,
    _type:String
    ) : LinearLayout(context, attrs, defStyle, defStyleRes) {
    init {
        LayoutInflater.from(context).inflate(R.layout.intervention_view, this, true)
        var idView = findViewById<TextView>(R.id.id)
        idView.text = _id.toString()
        var dateView = findViewById<TextView>(R.id.date)
        dateView.text = _date.toString()
        var nomView = findViewById<TextView>(R.id.nom)
        nomView.text = _nom
        var typeView = findViewById<TextView>(R.id.type)
        typeView.text = _type
    }
}