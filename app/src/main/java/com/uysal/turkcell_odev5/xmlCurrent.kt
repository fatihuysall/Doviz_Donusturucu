package com.uysal.turkcell_odev5

import android.util.Log
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

lateinit var tarih: String
lateinit var date: String
lateinit var bulten_No: String


class xmlCurrent {



    fun xmlCurrency(): List<Currency> {
        val arr = mutableListOf<Currency>()

        val url = "https://www.tcmb.gov.tr/kurlar/today.xml"
        val doc: Document = Jsoup.connect(url).timeout(30000).ignoreContentType(true).get()
        val elements: Elements = doc.getElementsByTag("Currency")

    val elements1: Elements = doc.getElementsByTag("Tarih_Date")

        tarih = elements1.get(0).attributes().get("Tarih")
        date = elements1.get(0).attributes().get("Date")
        bulten_No = elements1.get(0).attributes().get("Bulten_No")


        for (item in elements) {

            val Isim = item.getElementsByTag("Isim").text()
            val ForexBuying = item.getElementsByTag("ForexBuying").text()
            val ForexSelling = item.getElementsByTag("ForexSelling").text()
            val BanknoteBuying = item.getElementsByTag("BanknoteBuying").text()
            val BanknoteSelling = item.getElementsByTag("BanknoteSelling").text()

            val currency =
                Currency(Isim, ForexBuying, ForexSelling, BanknoteBuying, BanknoteSelling);
            arr.add(currency)
        }
        return arr
    }
}