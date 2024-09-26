package edu.ucne.robelinconcepcion_p1_ap2.presentation.venta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.robelinconcepcion_p1_ap2.data.local.entities.VentaEntity
import edu.ucne.robelinconcepcion_p1_ap2.data.repository.VentaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VentaViewModel @Inject constructor(
    private val ventaRepository: VentaRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(Uistate())
    val uiState = _uiState.asStateFlow()


    init {
        getVentas()
    }


    fun save() {
        viewModelScope.launch {

            if (_uiState.value.cliente.isNullOrBlank() || _uiState.value.galon <= 0) {
                _uiState.update {
                    it.copy(errorMessage = "Necesita Completar Todos los Campos")
                }
            } else {
                ventaRepository.save(_uiState.value.toEntity())
                nuevo()
            }
        }
    }

    fun nuevo() {
        _uiState.update {
            it.copy(
                cliente = "",
                galon = 0.0,
                        descuentoGalon = 0.0,
                        precio = 0.0,
                        totalDescuento = 0.0,
                        total = 0.0
            )
        }
    }

    fun select(ventaId: Int) {
        viewModelScope.launch {
            if (ventaId > 0) {
                val venta = ventaRepository.find(ventaId)
                _uiState.update {
                    it.copy(
                        ventaId = venta?.ventaId,
                        cliente = venta?.cliente ?: "",
                        galon = venta?.galon ?: 0.0,
                        descuentoGalon = venta?.descuentoGalon ?: 0.0,
                        precio = venta?.precio ?: 0.0,
                        totalDescuento = venta?.totalDescuento ?: 0.0,
                        total = venta?.total ?: 0.0,
                    )
                }

            }
        }
    }


    fun onClienteChange(cliente: String) {
        _uiState.update {
            it.copy(cliente = cliente)
        }
    }

    fun onGalonChange(galon: Double) {
        _uiState.update {
            it.copy(galon = galon)
        }
        actualizarDescuentoYTotal()

    }

    fun onDescuentoGalonChange(descuentoGalon: Double) {
        _uiState.update {
            it.copy(descuentoGalon = descuentoGalon)
        }
        actualizarDescuentoYTotal()

    }

    fun onPrecioChange(precio: Double) {
        _uiState.update {
            it.copy(precio = precio)
        }

    }

    fun onTotalDescuentoChange(totalDescuento: Double) {
        _uiState.update {
            it.copy(totalDescuento = totalDescuento)
        }

    }

    fun onTotalChange(total: Double) {
        _uiState.update {
            it.copy(total = total)
        }

    }
    private fun actualizarDescuentoYTotal() {
        val precioPorGalon = 100.0

        val precio = _uiState.value.galon * precioPorGalon

        val totalDescuento = _uiState.value.descuentoGalon * _uiState.value.galon

        val total = precio - totalDescuento

        _uiState.update {
            it.copy(
                precio = precio,
                totalDescuento = totalDescuento,
                total = total
            )
        }
    }



    fun delete() {
        viewModelScope.launch {
            ventaRepository.delete(_uiState.value.toEntity())
        }
    }


    private fun getVentas() {
        viewModelScope.launch {
            ventaRepository.getVentas().collect { venta ->
                _uiState.update {
                    it.copy(ventas = venta)
                }
            }
        }
    }
}

data class Uistate(
    val ventaId: Int? = null,
    val cliente: String = "",
    val galon: Double = 0.0,
    val descuentoGalon: Double = 0.0,
    val precio: Double = 0.0,
    val totalDescuento: Double = 0.0,
    val total: Double = 0.0,
    val ventas: List<VentaEntity> = emptyList(),
    val errorMessage: String? = null
)
fun Uistate.toEntity() = VentaEntity(
    ventaId = ventaId,
    cliente = cliente,
    galon = galon,
    descuentoGalon = descuentoGalon,
    precio = precio,
    totalDescuento = totalDescuento,
    total = total
)

