package com.example.todoapp.ui.widgets


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todoapp.data.Task

@Composable
fun TaskItem(
    task: Task,
    onToggleComplete: () -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Display task name with a strikethrough if completed
        Text(
            text = task.name,
            style = MaterialTheme.typography.bodyLarge.copy(
                textDecoration = if (task.completed) TextDecoration.LineThrough else TextDecoration.None
            )
        )

        Row {
            Checkbox(
                checked = task.completed,
                onCheckedChange = { onToggleComplete() }
            )
            TextButton(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}

