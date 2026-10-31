package com.example.peluqueriacrud.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.peluqueriacrud.data.buscarCita
import com.example.peluqueriacrud.data.listaCitas
import com.example.peluqueriacrud.models.Cita
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditDeleteCitasScreen(navController: NavController, id: Int) {
    val context = LocalContext.current
    val cita = buscarCita(id = id)
    if (cita == null) {
        Toast.makeText(context, "Cita no encontrada", Toast.LENGTH_LONG).show()
        navController.popBackStack()
        return
    }
    var showDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "EDITAR / BORRAR CITA",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF9100)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver atrás",
                            tint = Color(0xFFFF9100)
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
                actions = {},
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            showDialog = true
                        },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Delete, contentDescription = "Borrar cita")
                    }
                }
            )
        }
    ) { innerPadding ->
        EditDeleteBodyContent(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            cita = cita,
            context = context
        )
    }

    if (showDialog) {
        AlertDialog(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Confirmar borrado"
                )
            },
            title = { Text(text = "Confirmar borrado") },
            text = { Text(text = "Estas seguro que quieres borrar la cita de: ${cita.nombre}?") },
            onDismissRequest = {
                showDialog = false
            },
            confirmButton = {
                TextButton(onClick = {
                    listaCitas.remove(cita)
                    Toast.makeText(context, "Cita eliminada", Toast.LENGTH_LONG).show()
                    navController.popBackStack()
                }) {
                    Text(text = "Borrar")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
}

@Composable
fun EditDeleteBodyContent(
    navController: NavController,
    cita: Cita,
    modifier: Modifier,
    context: Context
) {
    var nombre by remember { mutableStateOf(cita.nombre) }
    var descripcion by remember { mutableStateOf(cita.descripcion) }

    // fechas
    var fechaState by remember { mutableStateOf(cita.fecha) }
    var horaState by remember { mutableStateOf(cita.hora) }

    val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val calendar = Calendar.getInstance().apply { time = fechaState }
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val newCal = Calendar.getInstance()
            newCal.set(year, month, dayOfMonth)
            fechaState = newCal.time
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val formatoHora = DateTimeFormatter.ofPattern("HH:mm")
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            horaState = LocalTime.of(hourOfDay, minute)
        },
        horaState.hour,
        horaState.minute,
        true
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del cliente") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { datePickerDialog.show() }
        ) {
            OutlinedTextField(
                value = formatoFecha.format(fechaState),
                onValueChange = {},
                readOnly = true,
                enabled = false,
                label = { Text("Fecha") },
                trailingIcon = {
                    Icon(Icons.Filled.DateRange, contentDescription = "Seleccionar fecha")
                },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledBorderColor = MaterialTheme.colorScheme.outline,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { timePickerDialog.show() }
        ) {
            OutlinedTextField(
                value = horaState.format(formatoHora),
                onValueChange = {},
                readOnly = true,
                enabled = false,
                label = { Text("Hora") },
                trailingIcon = {
                    Icon(Icons.Filled.Edit, contentDescription = "Seleccionar hora")
                },
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledBorderColor = MaterialTheme.colorScheme.outline,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = {
                cita.nombre = nombre
                cita.descripcion = descripcion
                cita.fecha = fechaState
                cita.hora = horaState
                Toast.makeText(context, "Cita actualizada", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9100))
        ) {
            Text(text = "Guardar cambios", color = Color.Black)
        }
    }
}
