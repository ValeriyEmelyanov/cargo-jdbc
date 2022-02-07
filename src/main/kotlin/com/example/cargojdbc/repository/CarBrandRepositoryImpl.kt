package com.example.cargojdbc.repository

import com.example.cargojdbc.model.CarBrand
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class CarBrandRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
) : CarBrandRepository {

    override fun findById(id: Int): CarBrand? =
        jdbcTemplate.query(
            "select * from car_brand where id = :id",
            mapOf("id" to id),
            ROW_MAPPER
        ).firstOrNull()

    private companion object {
        val ROW_MAPPER = RowMapper<CarBrand> { rs, _ ->
            CarBrand(
                id = rs.getInt("id"),
                title = rs.getString("title"),
            )
        }
    }
}