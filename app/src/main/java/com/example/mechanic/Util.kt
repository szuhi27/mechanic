package com.example.mechanic

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mechanic.database.Cars
import java.lang.StringBuilder

fun formatCars(cars: List<Cars>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply{
        append(resources.getString(R.string.title))
        cars.forEach{
            append("<br>")
            append(resources.getString(R.string.brand))
            append("\t${it.brand}<br>")
            append(resources.getString(R.string.model))
            append("\t${it.model}<br>")
            append(resources.getString(R.string.creation))
            append("\t${it.creationYear.toString()}<br>")
            append(resources.getString(R.string.problem))
            append("\t${it.problem}<br><br>")
        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        return Html.fromHtml(sb.toString())
    }
}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)