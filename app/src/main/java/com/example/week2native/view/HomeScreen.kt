package com.example.week2native.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week2native.viewmodel.TaskViewModel
import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import com.example.week2native.model.Task


@Composable
fun HomeScreen(viewModel: TaskViewModel = viewModel()) {
    val tasks by viewModel.tasks.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Text("Todo List", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Lista LazyColumnilla
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks) { task ->
                // taskin ulkonäölle
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { viewModel.selectTask(task) },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    // Tyylittelyä listalle
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),

                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Checkboxille
                            Checkbox(
                                checked = task.done,
                                onCheckedChange = { viewModel.toggleDone(task.id) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(task.title, style = MaterialTheme.typography.titleMedium)
                                Text(task.description, style = MaterialTheme.typography.bodyMedium)
                                Text("Due: ${task.dueDate}", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    // Delete buttonin tyylittelyä
                    Button(
                        onClick = { viewModel.removeTask(task.id) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text("Delete", color = MaterialTheme.colorScheme.onError)
                        }
                    }
                }
            }
        }



    if (selectedTask != null) {
    DetailDialog(task = selectedTask!!, onClose = { viewModel.closeDialog() }, onUpdate = { viewModel.updateTask(it) })
    }
}