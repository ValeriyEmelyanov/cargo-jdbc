package com.example.cargojdbc.dto

data class CargoDto(
    val id: Int = 0,
    val title: String,
    val passengerCount: Int? = null,
)
