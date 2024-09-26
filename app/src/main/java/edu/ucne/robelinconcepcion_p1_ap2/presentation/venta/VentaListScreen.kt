package edu.ucne.robelinconcepcion_p1_ap2.presentation.venta

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.robelinconcepcion_p1_ap2.data.local.entities.VentaEntity

@Composable
fun VentaListScreen(
    viewModel: VentaViewModel = hiltViewModel(),
    onVentaClick: (Int) -> Unit,
    createVenta: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    VentaListBodyScreen(
        uiState,
        onVentaClick,
        createVenta,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VentaListBodyScreen(
    uiState: Uistate,
    onVentaClick: (Int) -> Unit,
    createVenta: () -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = createVenta,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Añadir Venta")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(35.dp))
            Text("Lista", style = MaterialTheme.typography.headlineMedium)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(
                    modifier = Modifier.weight(2f),
                    text = "ID",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Text(
                    modifier = Modifier.weight(2f),
                    text = "Cliente",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Text(
                    modifier = Modifier.weight(2f),
                    text = "Galones",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Text(
                    modifier = Modifier.weight(2f),
                    text = "Total",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray

                )
            }
            HorizontalDivider()
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(uiState.ventas) { venta ->
                    VentaRow(venta, onVentaClick)
                }
            }
        }
    }
}

@Composable
fun VentaRow(
    venta: VentaEntity,
    onVentaClick: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable{onVentaClick(venta.ventaId!!)}
    ){
        Text(modifier = Modifier.weight(2f), text = venta.ventaId.toString())
        Text(
            modifier = Modifier.weight(2f),
            text = venta.cliente,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(modifier = Modifier.weight(2f), text = venta.galon.toString())
        Text(modifier = Modifier.weight(2f), text = venta.total.toString())
    }
    HorizontalDivider()
}

