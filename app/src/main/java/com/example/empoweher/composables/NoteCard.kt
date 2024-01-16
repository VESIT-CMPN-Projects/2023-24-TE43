package com.example.empoweher.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.empoweher.model.Note

@Composable
fun NoteCard(note: Note) {
    Text(text = note.note!!)
}