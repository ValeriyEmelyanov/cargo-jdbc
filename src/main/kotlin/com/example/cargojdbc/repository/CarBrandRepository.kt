package com.example.cargojdbc.repository

import com.example.cargojdbc.model.CarBrand

interface CarBrandRepository {

    fun findById(id:Int): CarBrand?

}