package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.ui.page.TodoListPage
import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "todoList") {
                    composable("todoList") { TodoListPage() }
                }
            }
        }
    }
}
