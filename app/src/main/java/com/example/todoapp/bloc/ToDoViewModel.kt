package com.example.todoapp.bloc

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.todoapp.data.Task

class TodoViewModel : ViewModel() {
    // Mutable state for the list of tasks, managing task data
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks // Exposed immutable StateFlow

    private var nextId = 1 // Simple incremental ID generator for tasks

    // Add a new task
    fun addTask(name: String) {
        val newTask = Task(id = nextId++, name = name)
        _tasks.value += newTask
    }

    // Edit an existing task by ID
    fun editTask(id: Int, newName: String) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == id) task.copy(name = newName) else task
        }
    }

    // Delete a task by ID
    fun deleteTask(id: Int) {
        _tasks.value = _tasks.value.filterNot { it.id == id }
    }
}
