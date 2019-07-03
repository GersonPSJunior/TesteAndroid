package br.com.resourceit.nasa.bank_santander.utils

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText

object Validation {
    fun emailValidation(email: String): Boolean = email.matches(Patterns.EMAIL_ADDRESS.toRegex())

    fun requiredField(field: String): Boolean = field.trim().isEmpty() || field.trim().isBlank()

    fun unMask(s: String): String {
        return s.replace(".", "").replace("-", "")
            .replace("/", "").replace("(", "")
            .replace(")", "")
    }

    fun insert(editText: EditText) {


        editText.addTextChangedListener(object : TextWatcher {

            var isUpdating: Boolean = true
            lateinit var old: String
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var str: String = unMask(s.toString())
                var mask = if (str.length > 10) MASK_TEL_NINE else MASK_TEL
                var mascara = ""
                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }
                var i = 0
                for (m in mask) {
                    if (m != '#' && (str.length > old.length)) {
                        mascara += m
                        continue
                    }
                    try {
                        mascara += str[i]
                    } catch (e: Exception) {
                        break
                    }
                    i++
                }
                isUpdating = true
                editText.setText(mascara)
                editText.setSelection(mascara.length)
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
        })
    }


}