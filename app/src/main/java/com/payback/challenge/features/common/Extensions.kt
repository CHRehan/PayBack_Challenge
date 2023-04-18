package com.payback.challenge.features.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import java.text.DecimalFormat

fun Long?.orZero() = this ?: 0

fun Long.format(): String = DecimalFormat("#,###.##").format(this)

fun Context.openBrowser(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}
