package br.com.resourceit.nasa.bank_santander

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

object BottomNavigationConfig{
    fun adjustGravity(v: View) {
        if (v.id == com.google.android.material.R.id.smallLabel) {
            val parent = v.parent as ViewGroup
            parent.setPadding(0, 0, 0, 0)

            val params = parent.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.CENTER
            parent.layoutParams = params
        }

        if (v is ViewGroup) {
            val vg = v as ViewGroup

            for (i in 0 until vg.childCount) {
                adjustGravity(vg.getChildAt(i))
            }
        }
    }
}
