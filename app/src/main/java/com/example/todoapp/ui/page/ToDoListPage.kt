package com.example.todoapp.ui.page


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.bloc.TodoViewModel
import com.example.todoapp.ui.widgets.TaskItem

@Composable
fun TodoListPage() {
    val viewModel: TodoViewModel = viewModel()
    val tasks by viewModel.tasks.collectAsState()
    var taskName by remember { mutableStateOf("") }
    var warningMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Aligns title and other components centrally
    ) {
        // Centered title label
        Text(
            text = "Todo List",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center, // Centers text within Text composable
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Text field for new task input
        OutlinedTextField(
            value = taskName,
            onValueChange = { taskName = it },
            label = { Text("New Task Name") },
            textStyle = TextStyle(
                color = Color.Black
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Show warning if task name is empty
        if (warningMessage.isNotEmpty()) {
            Text(text = warningMessage, color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Button to add task with validation
        Button(
            onClick = {
                if (taskName.isBlank()) {
                    warningMessage = "Task name cannot be empty"
                } else {
                    warningMessage = ""
                    viewModel.addTask(taskName)
                    taskName = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display task list
        LazyColumn {
            items(tasks.size) { index ->
                val task = tasks[index]
                TaskItem(
                    task = task,
                    onDelete = { viewModel.deleteTask(task.id) },
                    onEdit = { newName ->
                        if (newName.isBlank()) {
                            warningMessage = "Task name cannot be empty"
                        } else {
                            warningMessage = ""
                            viewModel.editTask(task.id, newName)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
