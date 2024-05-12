package com.example.myapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.labexam04.MainActivityData
import com.example.labexam04.R
import com.example.labexam04.database.entities.Todo
import com.example.labexam04.database.repositories.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoAdapter(
    private var items: List<Todo>,
    private val repository: TodoRepository,
    private val viewModel: MainActivityData,
    private val context: Context
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_item, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.cbTodo.text = items[position].item

        holder.ivDelete.setOnClickListener {
            val isChecked = holder.cbTodo.isChecked
            if (isChecked) {
                CoroutineScope(Dispatchers.IO).launch {
                    repository.delete(items[position])
                    val data = repository.getAllTodoItems()
                    withContext(Dispatchers.Main) {
                        viewModel.setData(data)
                    }
                }
                Toast.makeText(context, "Item Deleted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Select the item to delete", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cbTodo: CheckBox = view.findViewById(R.id.cbTodo)
        val ivDelete: ImageView = view.findViewById(R.id.ivDelete)
    }



}

