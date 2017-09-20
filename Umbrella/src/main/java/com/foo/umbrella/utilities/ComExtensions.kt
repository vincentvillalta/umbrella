package com.foo.umbrella.utilities

/**
 * Created by vincentvillalta on 9/19/17.
 */
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

fun Activity.launchActivity(destinationClass: Class<*>, flags: Int = 0) {
    val intent = Intent(this, destinationClass)
    intent.flags = flags
    startActivity(intent)
}
fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_LONG) = Toast.makeText(this, text, duration).show()
fun Context.getColorCompat(colorResId: Int) = ContextCompat.getColor(this, colorResId)
fun ViewGroup.inflate(layoutRes: Int): View = LayoutInflater.from(context).inflate(layoutRes, this, false)