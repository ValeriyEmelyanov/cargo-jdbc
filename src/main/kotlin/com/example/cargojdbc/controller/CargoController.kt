package com.example.cargojdbc.controller

import com.example.cargojdbc.dto.CargoDto
import com.example.cargojdbc.service.CargoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
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
@Api(description = "Контроллер для работы с транспортом")
class CargoController(
    private val cargoService: CargoService
) {

    @GetMapping
    @ApiOperation("Получение полного списка транспорта")
    fun getAll(@RequestParam(name = "page", defaultValue = "0", required = false) pageIndex: Int): List<CargoDto> =
        cargoService.getAll(pageIndex)

    @GetMapping("/{id}")
    @ApiOperation("Получение транспортного средства по его идентификатору")
    fun getById(@PathVariable id: Int): CargoDto = cargoService.getById(id)

    @PostMapping
    @ApiOperation("Создание нового транспортного средства")
    fun create(@RequestBody cargoDto: CargoDto) = cargoService.create(cargoDto)

    @PostMapping("/batch")
    @ApiOperation("Пакетное создание новых транспортных средств")
    fun batchCreate(@RequestBody cargoDtos: List<CargoDto>) = cargoService.batchCreate(cargoDtos)

    @PutMapping("/{id}")
    @ApiOperation("Обновление существующего транспортного средства")
    fun update(@PathVariable id: Int, @RequestBody cargoDto: CargoDto) = cargoService.update(id, cargoDto)

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление транспортного средства по его идентификатору")
    fun delete(@PathVariable id: Int) {
        cargoService.delete(id)
    }
}
