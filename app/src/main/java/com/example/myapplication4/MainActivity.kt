package com.example.myapplication4

// MainActivity.kt
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var taskNameEditText: EditText
    private lateinit var assignPersonSpinner: Spinner
    private lateinit var addTaskButton: Button
    private lateinit var checkTasksButton: Button

    private val people = arrayOf("Osoba 1", "Osoba 2", "Osoba 3")
    private val taskList = HashMap<String, MutableList<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskNameEditText = findViewById(R.id.taskNameEditText)
        assignPersonSpinner = findViewById(R.id.assignPersonSpinner)
        addTaskButton = findViewById(R.id.addTaskButton)
        checkTasksButton = findViewById(R.id.checkTasksButton)

        assignPersonSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, people)

        addTaskButton.setOnClickListener {
            addTask()
        }

        checkTasksButton.setOnClickListener {
            showPersonSelectionDialog()
        }
    }

    private fun addTask() {
        val taskName = taskNameEditText.text.toString().trim()
        val assignedPerson = people[assignPersonSpinner.selectedItemPosition]

        if (taskName.isNotEmpty()) {
            if (!taskList.containsKey(assignedPerson)) {
                taskList[assignedPerson] = mutableListOf()
            }
            taskList[assignedPerson]?.add(taskName)
            Toast.makeText(this, "Zadanie dodane dla: $assignedPerson", Toast.LENGTH_SHORT).show()

            // Opcjonalnie, możesz wyczyścić pola po dodaniu zadania
            taskNameEditText.text.clear()
        }
    }

    private fun showPersonSelectionDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Wybierz osobę")

        builder.setItems(people) { _, which ->
            val selectedPerson = people[which]
            showConfirmationDialog(selectedPerson)
        }

        builder.show()
    }

    private fun showConfirmationDialog(selectedPerson: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Potwierdź wybór")
        builder.setMessage("Czy na pewno chcesz sprawdzić zadania dla $selectedPerson?")

        builder.setPositiveButton("Potwierdź") { _, _ ->
            navigateToTaskListActivity(selectedPerson)
        }

        builder.setNegativeButton("Anuluj") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun navigateToTaskListActivity(selectedPerson: String) {
        // Przejście do aktywności TaskListActivity
        val intent = Intent(this, TaskListActivity::class.java)
        intent.putExtra("assignedPerson", selectedPerson)
        intent.putStringArrayListExtra("tasksList", ArrayList(taskList[selectedPerson]))
        startActivity(intent)
    }
}





