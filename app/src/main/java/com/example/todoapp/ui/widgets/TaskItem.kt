package com.example.todoapp.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.Task

@Composable
fun TaskItem(
    task: Task,
    onDelete: () -> Unit,
    onEdit: (String) -> Unit
) {
    var isEditing by remember { mutableStateOf(false) }
    var editedTaskName by remember { mutableStateOf(task.name) }
    var editWarningMessage by remember { mutableStateOf("") } // Warning for empty edit

    if (isEditing) {
        // Editable text field for task name
        OutlinedTextField(
            value = editedTaskName,
            onValueChange = { editedTaskName = it },
            label = { Text("Edit Task Name") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                color = Color.Black
            ),
        )

        // Display warning if edit is empty
        if (editWarningMessage.isNotEmpty()) {
            Text(text = editWarningMessage, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Save button with validation
            TextButton(onClick = {
                if (editedTaskName.isBlank()) {
                    editWarningMessage = "Task name cannot be empty" // Show warning
                } else {
                    editWarningMessage = "" // Clear warning
                    onEdit(editedTaskName)
                    isEditing = false
                }
            }) {
                Text("Save")
            }
            // Cancel button
            TextButton(onClick = { isEditing = false }) {
                Text("Cancel")
            }
        }
    } else {
        // Display task name with Edit and Delete actions
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = task.name, style = MaterialTheme.typography.bodyLarge)

            Row {
                // Edit button
                TextButton(onClick = { isEditing = true }) {
                    Text("Edit")
                }
                // Delete button
                TextButton(onClick = onDelete) {
                    Text("Delete")
                }
            }
        }
    }
}
