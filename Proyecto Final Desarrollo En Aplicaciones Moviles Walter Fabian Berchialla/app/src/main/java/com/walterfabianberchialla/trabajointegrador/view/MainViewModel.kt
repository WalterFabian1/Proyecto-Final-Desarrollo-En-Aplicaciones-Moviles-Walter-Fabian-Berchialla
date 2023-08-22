package com.gustavosds.trabajointegrador.view

import android.view.View
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavosds.trabajointegrador.model.ViewText
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val resultText: LiveData<ViewText> get() = _resultText

    private var _resultText = MutableLiveData<ViewText>(ViewText("Hello world!"))

    fun compareTexts(textView1: String, textView2: String) {
        if (textView1.isNotEmpty() && textView2.isNotEmpty()) {
            if (textView1 == textView2) _resultText.value = ViewText("Son iguales")

            else _resultText.value = ViewText("Son diferentes")

        } else {
            if (textView1.isEmpty() && textView2.isEmpty()) _resultText.value =
                ViewText("No pueden ser vacios")

            else _resultText.value = ViewText("No puede ser vacio")
        }
    }

    private fun updateResult(result: String) {
        viewModelScope.launch {
            _resultText.value = ViewText(result)
        }
    }

}