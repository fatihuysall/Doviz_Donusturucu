package com.uysal.turkcell_odev5

import android.database.Cursor
import android.os.Bundle
import android.os.StrictMode
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {


    lateinit var textViewTitle: TextView
    lateinit var textViewUpdateDate: TextView
    lateinit var textViewForexBuy: TextView
    lateinit var textViewForexSell: TextView
    lateinit var textViewBankBuy: TextView
    lateinit var editTextMoney: EditText
    lateinit var textViewBankSell: TextView
    lateinit var imageView: ImageView




    override fun onCreate(savedInstanceState: Bundle?) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        textViewTitle = findViewById(R.id.textCurrency)
        textViewForexBuy = findViewById(R.id.textForexBuying)
        textViewForexSell = findViewById(R.id.textForexSelling)
        textViewBankBuy = findViewById(R.id.textBanknoteBuying)
        textViewBankSell = findViewById(R.id.textBanknoteSelling)
        textViewUpdateDate = findViewById(R.id.textUpdateDate)
        editTextMoney = findViewById(R.id.editTextMoney)
        imageView = findViewById(R.id.iconCurrent)
        var xml = xmlCurrent()
        val arr = xml.xmlCurrency()


        textViewUpdateDate.text =
            " Tarih: $tarih, Date: $date günü belirlenen gösterge niteliğindeki Türkiye Cumhuriyet Merkez Bankası kurları \nBulten No : $bulten_No"



        textViewTitle.setOnClickListener {
            var number = editTextMoney.text.toString()
            val popupMenu: PopupMenu = PopupMenu(this, textViewTitle)
            popupMenu.inflate(R.menu.custom_menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                changeBoxes(item, arr, number)

                true
            })
            popupMenu.show()



        }




    }

    private fun changeBoxes(
        item: MenuItem,
        arr: List<Currency>,
        number: String
    ) {
        when (item.itemId) {
            R.id.paraBirimi -> {
                textViewTitle.setText(item.title)
                imageView.setImageResource(R.drawable.usa_icon)
                applyList(item.title.toString(), arr, number)

            }
            R.id.paraBirimi1 -> {
                textViewTitle.setText(item.title)
                imageView.setImageResource(R.drawable.aud_icon)
                applyList(item.title.toString(), arr, number)

            }
            R.id.paraBirimi2 -> {

                textViewTitle.setText(item.title)
                imageView.setImageResource(R.drawable.dkk_icon)
                applyList(item.title.toString(), arr, number)
            }
            R.id.paraBirimi3 -> {

                textViewTitle.setText(item.title)
                imageView.setImageResource(R.drawable.eur_icon)
                applyList(item.title.toString(), arr, number)
            }
            R.id.paraBirimi4 -> {
                textViewTitle.setText(item.title)
                imageView.setImageResource(R.drawable.gbp_icon)
                applyList(item.title.toString(), arr, number)
            }
            R.id.paraBirimi5 -> {
                textViewTitle.setText(item.title)
                imageView.setImageResource(R.drawable.chf_icon)
                applyList(item.title.toString(), arr, number)

            }
            R.id.paraBirimi6 -> {

                textViewTitle.setText(item.title)
                imageView.setImageResource(R.drawable.sek_icon)
                applyList(item.title.toString(), arr, number)

            }
            R.id.paraBirimi7 -> {

                textViewTitle.setText(item.title)
                imageView.setImageResource(R.drawable.cad_icon)
                applyList(item.title.toString(), arr, number)
            }
            R.id.paraBirimi8 -> {

                textViewTitle.setText(item.title)
                imageView.setImageResource(R.drawable.kwd_icon)
                applyList(item.title.toString(), arr, number)
            }
            R.id.paraBirimi9 -> {

                textViewTitle.setText(item.title)
                imageView.setImageResource(R.drawable.nok_icon)
                applyList(item.title.toString(), arr, number)
            }
            R.id.paraBirimi10 -> {

                textViewTitle.setText(item.title)
                imageView.setImageResource(R.drawable.sar_icon)
                applyList(item.title.toString(), arr, number)
            }
            R.id.paraBirimi11 -> {

                textViewTitle.setText(item.title)
                imageView.setImageResource(R.drawable.jpy_icon)
                applyList(item.title.toString(), arr, number)
            }
        }
    }


    fun applyList(title: String, arr: List<Currency> , number : String) {

        val dec = DecimalFormat("#.0000")

        for (item in arr) {
            if (item.name == title) {
                var numberFormat =dec.format((item.forexBuying.toDouble() * number.toDouble()))
                textViewForexBuy.setText(numberFormat)
                numberFormat =dec.format((item.forexSelling.toDouble() * number.toDouble()))
                textViewForexSell.setText(numberFormat)
                numberFormat =dec.format((item.banknoteBuying.toDouble() * number.toDouble()))
                textViewBankBuy.setText(numberFormat)
                numberFormat =dec.format((item.banknoteSelling.toDouble() * number.toDouble()))
                textViewBankSell.setText(numberFormat)
            }
        }

    }
}

private fun Double.makeIndicesShort( i: Int): Any {
    var number = this
    var indices = i
    while (indices > 0 ){
        number =  number * 10
        indices--
    }
    number = number.toInt().toDouble()
    while (indices < i  ){
        number =  number / 10
        indices++
    }
return number
}

