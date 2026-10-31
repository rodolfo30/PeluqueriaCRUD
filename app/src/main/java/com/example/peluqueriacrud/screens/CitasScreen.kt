package com.example.peluqueriacrud.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.example.peluqueriacrud.components.MiPropiaCardCitas
import com.example.peluqueriacrud.data.listaCitas
import com.example.peluqueriacrud.models.Cita
import com.iessanalberto.dam2.tareasapp.navigation.AppScreens
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitasScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "CITAS",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFFF9100)
                    )
                },
                navigationIcon = {
                    /*TODO BORRAR ESTE TODO CUANDO HAYA LOGIN*/
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
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(AppScreens.NewCitaScreen.route)},
                containerColor = Color(0xFFFF9100),
                contentColor = Color.Black
                ) {
                Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "Añadir Cita")
            }
        }
    ) { paddingValues ->
        CitasScreenBodyContent(
            modifier = Modifier.padding(paddingValues = paddingValues),
            navController = navController
        )
    }
}

@Composable
fun CitasScreenBodyContent(modifier: Modifier, navController: NavController) {
    val context = LocalContext.current
    var fechaFiltro by remember { mutableStateOf<Date?>(null) }
    val formatoFecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    val calendar = Calendar.getInstance().apply {
        time = fechaFiltro ?: Date()
    }

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            fechaFiltro = cal.time
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // Filtrar por fecha y ordenar por hora (horas más tempranas primero)
    val citasFiltradas = listaCitas
        .filter { cita ->
            fechaFiltro == null || formatoFecha.format(cita.fecha) == formatoFecha.format(fechaFiltro!!)
        }
        .sortedWith(compareBy<Cita> { formatoFecha.format(it.fecha) }.thenBy { it.hora })

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable { datePickerDialog.show() }
            ) {
                OutlinedTextField(
                    value = if (fechaFiltro != null) formatoFecha.format(fechaFiltro!!) else "Todas las fechas",
                    onValueChange = {},
                    readOnly = true,
                    enabled = false,
                    label = { Text("Filtrar por fecha") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.DateRange,
                            contentDescription = "Seleccionar fecha"
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledTrailingIconColor = Color(0xFFFF9100)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            if (fechaFiltro != null) {
                IconButton(onClick = { fechaFiltro = null }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "Limpiar filtro",
                        tint = Color.Red
                    )
                }
            }
        }
        if (citasFiltradas.isEmpty()) {
            Text(
                text = "No hay citas programadas",
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Gray,
                fontSize = 16.sp
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(citasFiltradas) { cita ->
                    MiPropiaCardCitas(cita = cita, onClick = {
                        navController.navigate(route = AppScreens.EditDeleteCitasScreen.route + "/${cita.id}")
                    })
                }
            }
        }
    }
}
