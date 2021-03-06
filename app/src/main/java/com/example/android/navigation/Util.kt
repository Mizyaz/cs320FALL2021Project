package com.example.android.navigation

import android.content.res.Resources
import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.example.android.navigation.database.Task

fun formatTasks(tasks: List<Task>, resources: Resources): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append(resources.getString(R.string.data))
        tasks.forEach {
            append("<br>")
            append(resources.getString(R.string.descryption))
            append("\t${it.Descryption}<br>")
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

fun formatTask(task: Task): Spanned {
    val sb = StringBuilder()
    sb.apply {
        append("<br>")
        append("Description: ")
        append("\t${task.Descryption}<br>")
        append("<br>")
    }
    if(!task.StartDate.equals(" ")) {
        sb.apply {
            append("Start Date: ")
            append("\t${task.StartDate}<br>")
            append("<br>")
            append("End Date: ")
            append("\t${task.EndDate}<br>")
            append("<br>")
        }
    }
    if(!task.PriorityLevel.equals(" ")){
        sb.apply {
            append("Priority Level: ")
            append("\t${task.PriorityLevel}<br>")
            append("<br>")
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sb.toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sb.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}

