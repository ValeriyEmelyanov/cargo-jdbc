package com.example.cargojdbc.controller

import com.example.cargojdbc.dto.CargoDto
import com.example.cargojdbc.service.CargoService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cargo")
class CargoController(
    private val cargoService: CargoService
) {

    @GetMapping
    fun getAll(): List<CargoDto> = cargoService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): CargoDto = cargoService.getById(id)

    @PostMapping
    fun create(@RequestBody cargoDto: CargoDto) = cargoService.create(cargoDto)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody cargoDto: CargoDto) = cargoService.update(id, cargoDto)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) {
        cargoService.delete(id)
    }
}
