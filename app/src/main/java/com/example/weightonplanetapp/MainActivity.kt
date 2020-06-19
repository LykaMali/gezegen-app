package com.example.weightonplanetapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.CheckBox
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val KILO_TO_POUND = 2.2045
    val MARS = 0.38
    val POUND_TO_KİLO = 0.45359237
    val VENUS = 0.91
    val Jupiter = 2.34


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewSonuc.text = savedInstanceState?.getString("sonuc")
        cbMars.setOnClickListener(this)
        cbVenus.setOnClickListener(this)
        cbJüpiter.setOnClickListener(this)
    }

    fun kiloToPound(kilo : Double) : Double{
        return kilo * KILO_TO_POUND
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("sonuc", textViewSonuc.text.toString())

    }



    fun poundToKilo(pound : Double) : Double {
        return pound * POUND_TO_KİLO
    }

    override fun onClick(v: View?) {
        v as CheckBox
        var isChecked:Boolean = v.isChecked

        if(!TextUtils.isEmpty(etKilo.text.toString())){
            var kullaniciKilo = etKilo.text.toString().toDouble()
            var kullaniciPound = kiloToPound(kullaniciKilo)

            when (v.id){
                R.id.cbMars -> if(isChecked){
                    cbJüpiter.isChecked=false
                    cbVenus.isChecked=false
                    hesapliAgirlikPound(kullaniciPound, v)
                }
                R.id.cbVenus -> if(isChecked){
                    cbJüpiter.isChecked=false
                    cbMars.isChecked=false
                    hesapliAgirlikPound(kullaniciPound, v)
                }
                R.id.cbJüpiter -> if(isChecked){
                    cbVenus.isChecked=false
                    cbMars.isChecked=false
                    hesapliAgirlikPound(kullaniciPound, v)
                }
            }

        }



    }

    fun hesapliAgirlikPound(pound: Double, checkBox: CheckBox){
        var sonuc : Double = 0.0

        when(checkBox.id){
            R.id.cbMars -> sonuc = pound * MARS
            R.id.cbVenus -> sonuc = pound * VENUS
            R.id.cbJüpiter -> sonuc = pound * Jupiter
            else -> sonuc = 0.0
        }
        var sonucToKilo = poundToKilo(sonuc)
        textViewSonuc.text=sonucToKilo.formatla(2)

    }

    fun Double.formatla(kactaneRakam:Int) = java.lang.String.format("%.${kactaneRakam}f", this)
}