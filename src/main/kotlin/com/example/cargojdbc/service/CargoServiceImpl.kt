package com.example.cargojdbc.service

import com.example.cargojdbc.dto.CargoDto
import com.example.cargojdbc.exception.CargoNotFoundException
import com.example.cargojdbc.model.Cargo
import com.example.cargojdbc.repository.CargoRepository
import org.springframework.stereotype.Service

@Service
class CargoServiceImpl(
    private val cargoRepository: CargoRepository,
) : CargoService {

    override fun getAll(): List<CargoDto> = cargoRepository.getAll()
        .map { it.toDto() }

    override fun getById(id: Int): CargoDto = cargoRepository.findById(id)
        ?.toDto()
        ?: throw CargoNotFoundException("Cargo with id=$id not found")

    override fun create(cargoDto: CargoDto): CargoDto {
        TODO("Not yet implemented")
    }

    override fun update(id: Int, cargoDto: CargoDto): CargoDto {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    private fun Cargo.toDto() =
        CargoDto(
            id = id,
            title = title,
            passengerCount = passengerCount,
        )
}