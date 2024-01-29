package com.example.myapplication4

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class TaskListActivity : AppCompatActivity() {

    private lateinit var assignedPersonTextView: TextView
    private lateinit var taskListView: ListView
    private lateinit var backButton: Button

    private lateinit var tasksList: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        assignedPersonTextView = findViewById(R.id.assignedPersonTextView)
        taskListView = findViewById(R.id.taskListView)
        backButton = findViewById(R.id.backButton)

        val assignedPerson = intent.getStringExtra("assignedPerson") ?: ""
        tasksList = intent.getStringArrayListExtra("tasksList") ?: arrayListOf()

        assignedPersonTextView.text = "Zadania dla: $assignedPerson"

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tasksList)
        taskListView.adapter = adapter

        backButton.setOnClickListener {
            navigateBackToMainActivity()
        }

        taskListView.setOnItemClickListener { _, _, position, _ ->
            showOptionsDialog(position)
        }
    }

    private fun showOptionsDialog(position: Int) {
        val options = arrayOf("Edytuj", "Usuń")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Opcje")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> editTask(position)
                1 -> removeTask(position)
            }
            dialog.dismiss()
        }
        builder.show()
    }

    private fun editTask(position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edytuj zadanie")

        val input = EditText(this)
        input.setText(tasksList[position])
        builder.setView(input)

        builder.setPositiveButton("Zapisz") { _, _ ->
            val editedTask = input.text.toString()
            tasksList[position] = editedTask
            adapter.notifyDataSetChanged()
        }

        builder.setNegativeButton("Anuluj") { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun removeTask(position: Int) {
        tasksList.removeAt(position)
        adapter.notifyDataSetChanged()
    }

    private fun navigateBackToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}





