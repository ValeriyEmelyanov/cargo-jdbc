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

    override fun getAll(pageIndex: Int): List<CargoDto> =
        cargoRepository.getAll(pageIndex)
            .map { it.toDto() }

    override fun getById(id: Int): CargoDto =
        cargoRepository.findById(id)
            ?.toDto()
            ?: throw CargoNotFoundException(id)

    override fun create(cargoDto: CargoDto): CargoDto =
        cargoRepository.create(cargoDto.toModel())
            .toDto()

    override fun batchCreate(cargoDtos: List<CargoDto>): Int =
        cargoRepository.batchCreate(cargoDtos.map { dto -> dto.toModel() })
            .size

    override fun update(id: Int, cargoDto: CargoDto): CargoDto =
        cargoRepository.update(id, cargoDto.toModel())
            .toDto()

    override fun delete(id: Int) {
        cargoRepository.delete(id)
    }

    private fun Cargo.toDto() =
        CargoDto(
            id = id,
            title = title,
            brand = brand,
            passengerCount = passengerCount,
            loadCapacity = loadCapacity,
        )

    private fun CargoDto.toModel() =
        Cargo(
            id = id,
            title = title,
            brand = brand,
            passengerCount = passengerCount,
            loadCapacity = loadCapacity,
        )
}