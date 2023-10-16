package com.dingmouren.shadecraft.ext

import android.content.Context
import android.widget.Toast

inline fun Context.toast(text: CharSequence) =
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
