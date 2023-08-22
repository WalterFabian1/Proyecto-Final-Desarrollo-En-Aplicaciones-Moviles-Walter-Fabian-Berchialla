package com.walterfabianberchialla.trabajointegrador

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gustavosds.trabajointegrador.view.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    private lateinit var viewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun checkInitialText() = runTest {
        viewModel.resultText.value?.let {
            assertEquals("Hello world!", it.result)
        }
    }

    //Test of Text1 and Text2 is not Equals
    @Test
    fun checkText1andText2isNotEquals() = runTest {
        viewModel.compareTexts("Text1", "Text2")
        viewModel.resultText.value?.let {
            assertEquals("Son diferentes", it.result)
        }
    }

    // Test of Text1 and Text2 is Equals
    @Test
    fun checkText1andText2isEquals() = runTest {
        launch {
            viewModel.compareTexts("Text2", "Text2")
        }
        advanceUntilIdle()

        val result = viewModel.resultText.value?.result
        assertEquals("Son iguales", result)
    }

    @Test
    fun checkText1isEmpty() = runTest {
        viewModel.compareTexts("", "Text2")
        viewModel.resultText.value?.let {
            assertEquals("No puede ser vacio", it.result)
        }
    }

    @Test
    fun checkText2isEmpty() = runTest {
        viewModel.compareTexts("Text1", "")
        viewModel.resultText.value?.let {
            assertEquals("No puede ser vacio", it.result)
        }
    }

    @Test
    fun checkText1andText2isEmpty() = runTest {
        viewModel.compareTexts("", "")
        viewModel.resultText.value?.let {
            assertEquals("No pueden ser vacios", it.result)
        }
    }

}