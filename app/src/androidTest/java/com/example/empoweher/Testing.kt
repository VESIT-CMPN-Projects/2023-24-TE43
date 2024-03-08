package com.example.empoweher

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.empoweher.screen.notes.CreateNote
import org.junit.Rule
import org.junit.Test

class Testing {

    @get:Rule
    val rule= createComposeRule()

    @Test
    fun enterTest(){
        rule.setContent { CreateNote()}

        //Do something
        rule.onNodeWithText("Scan").performClick()

    }
}