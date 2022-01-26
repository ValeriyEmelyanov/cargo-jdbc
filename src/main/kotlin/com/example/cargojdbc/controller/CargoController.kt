package com.example.cargojdbc.controller

import com.example.cargojdbc.dto.CargoDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cargo")
class CargoController {

    @GetMapping
    fun  getAll(): List<CargoDto> =
        listOf(
            CargoDto(
                id = 1,
                title = "Газель"
            ),
            CargoDto(
                id = 2,
                title = "Газель 2"
            ),
        )

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Int): CargoDto =
        CargoDto(
            id = 1,
            title = "Газель"
        )

    @PostMapping
    fun create(@RequestBody cargoDto: CargoDto) =
        CargoDto(
            id = cargoDto.id,
            title = "Новый транспорт: ${cargoDto.title}"
        )

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody cargoDto: CargoDto) =
        CargoDto(
            id = id,
            title = "Измененный транспорт: ${cargoDto.title}"
        )

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) {

    }
}
