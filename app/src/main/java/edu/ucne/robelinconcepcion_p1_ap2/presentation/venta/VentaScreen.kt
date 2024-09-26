package edu.ucne.robelinconcepcion_p1_ap2.presentation.venta


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun VentaScreen(
    viewModel: VentaViewModel = hiltViewModel(),
    goBack:() -> Unit
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    VentaBodyScreen(
        uiState = uiState,
        onClienteChange = viewModel::onClienteChange,
        onGalonChange = viewModel::onGalonChange,
        onDescuentoGalonChange = viewModel::onDescuentoGalonChange,
        onPrecioChange = viewModel::onPrecioChange,
        onTotalDescuentoChange = viewModel::onTotalDescuentoChange,
        onTotalChange = viewModel::onTotalChange,
        onSaveVenta = viewModel::save,
        onNuevoVenta = viewModel::nuevo,
    )
}

@Composable
fun VentaBodyScreen(
    uiState: Uistate,
    onClienteChange: (String) -> Unit,
    onGalonChange: (Double) -> Unit,
    onDescuentoGalonChange: (Double) -> Unit,
    onPrecioChange: (Double) -> Unit,
    onTotalDescuentoChange: (Double) -> Unit,
    onTotalChange: (Double) -> Unit,
    onSaveVenta: () -> Unit,
    onNuevoVenta: () -> Unit,
){
    Scaffold{ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .fillMaxSize(),
        ){
            OutlinedTextField(
                label = {Text("Cliente")},
                value = uiState.cliente,
                onValueChange = onClienteChange,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Galon") },
                value = uiState.galon.toString(),
                onValueChange = { newValue ->
                    val galon = newValue.toDoubleOrNull() ?: 0.0
                    onGalonChange(galon)
                }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Descuento por Galon") },
                value = uiState.descuentoGalon.toString(),
                onValueChange = { newValue ->
                    val descuentoGalon = newValue.toDoubleOrNull() ?: 0.0
                    onDescuentoGalonChange(descuentoGalon)
                }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Precio") },
                value = uiState.precio.toString(),
                onValueChange = { newValue ->
                    val precio = newValue.toDoubleOrNull() ?: 0.0
                    onPrecioChange(precio)
                }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Total del Descuento") },
                value = uiState.totalDescuento.toString(),
                onValueChange = { newValue ->
                    val totalDescuento = newValue.toDoubleOrNull() ?: 0.0
                    onTotalDescuentoChange(totalDescuento)
                }
            )


            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Total") },
                value = uiState.galon.toString(),
                onValueChange = { newValue ->
                    val total = newValue.toDoubleOrNull() ?: 0.0
                    onTotalChange(total)
                }
            )

            Spacer (modifier = Modifier.padding(2.dp))
            uiState.errorMessage?.let {
                Text(text = it, color = Color.Red)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick={
                        onSaveVenta()
                    }
                ){
                    Icon(Icons.Default.Add, contentDescription = "Guardar")
                    Text("Guardar")
                }
                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick={
                        onNuevoVenta()
                    }
                ){
                    Icon(Icons.Default.Create, contentDescription = "Nuevo")
                    Text("Nuevo")
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}
