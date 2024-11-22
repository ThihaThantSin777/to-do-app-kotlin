package com.example.todoapp.bloc


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.data.database.DatabaseProvider
import com.example.todoapp.data.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val taskDao = DatabaseProvider.getDatabase(application).taskDao()

    // Flow to observe the list of tasks in the UI
    val tasks: Flow<List<Task>> = taskDao.getAllTasks()

    // Add a new task to the database
    fun addTask(name: String) {
        val newTask = Task(name = name)
        viewModelScope.launch {
            taskDao.insertTask(newTask)
        }
    }

    // Update a task's name or completion status
    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskDao.updateTask(task)
        }
    }

    // Delete a task from the database
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskDao.deleteTask(task)
        }
    }

    // Toggle task completion status
    fun toggleTaskCompletion(task: Task) {
        val updatedTask = task.copy(completed = !task.completed)
        updateTask(updatedTask)
    }
}
