package com.example.dicodingevent.util

import android.content.Intent
import android.net.Uri
import android.content.Context



fun String.trimStartEnd() : String = this.trimStart().trimEnd()


fun openBrowser(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}
