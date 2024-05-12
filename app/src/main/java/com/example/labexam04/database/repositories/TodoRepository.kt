package com.example.labexam04.database.repositories

import com.example.labexam04.database.TodoDatabase
import com.example.labexam04.database.entities.Todo

class TodoRepository(
    private val db: TodoDatabase
) {
    suspend fun insert(todo: Todo) = db.getTodoDao().insertTodo(todo)
    suspend fun delete(todo:Todo) = db.getTodoDao().deleteTodo(todo)
    fun getAllTodoItems():List<Todo> = db.getTodoDao().getAllTodoItems()
}