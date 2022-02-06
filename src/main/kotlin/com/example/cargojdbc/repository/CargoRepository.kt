package com.example.cargojdbc.repository

import com.example.cargojdbc.model.Cargo

interface CargoRepository {

    fun getAll(pageIndex: Int): List<Cargo>

    fun findById(id: Int): Cargo?

    fun create(cargo: Cargo): Cargo

    fun batchCreate(cargos: List<Cargo>): IntArray

    fun update(id: Int, cargo: Cargo): Cargo

    fun delete(id: Int)

}