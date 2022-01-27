package com.example.cargojdbc.repository

import com.example.cargojdbc.model.Cargo
import com.example.cargojdbc.util.getIntOrNull
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class CargoRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
) : CargoRepository {
    override fun getAll(): List<Cargo> =
        jdbcTemplate.query(
            "select * from cargo order by title",
            ROW_MAPPER
        )

    override fun findById(id: Int): Cargo? =
        jdbcTemplate.query(
            "select * from cargo where id = :id",
            mapOf("id" to id),
            ROW_MAPPER
        ).firstOrNull()

    override fun create(cargoDto: Cargo): Cargo {
        TODO("Not yet implemented")
    }

    override fun update(id: Int, cargo: Cargo): Cargo {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }

    private companion object {
        val ROW_MAPPER = RowMapper<Cargo> { rs, _ ->
            Cargo(
                id = rs.getInt("id"),
                title = rs.getString("title"),
                passengerCount = rs.getIntOrNull("passenger_count"),
            )
        }
    }
}