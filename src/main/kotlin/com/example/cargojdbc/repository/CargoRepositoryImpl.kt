package com.example.cargojdbc.repository

import com.example.cargojdbc.model.Cargo
import com.example.cargojdbc.util.getIntOrNull
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
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

    override fun create(cargo: Cargo): Cargo {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            "insert into cargo (title, passenger_count) values (:title, :passenger_count)",
            MapSqlParameterSource(
                mapOf(
                    "title" to cargo.title,
                    "passenger_count" to cargo.passengerCount,
                )
            ),
            keyHolder,
            listOf("id", "title", "passenger_count").toTypedArray()
        )
        return Cargo(
            id = keyHolder.keys?.getValue("id") as Int,
            title = keyHolder.keys?.getValue("title") as String,
            passengerCount = keyHolder.keys?.getValue("passenger_count") as Int,
        )
    }

    override fun update(id: Int, cargo: Cargo): Cargo {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            "update cargo set title = :title, passenger_count = :passenger_count where id = :id",
            MapSqlParameterSource(
                mapOf(
                    "id" to id,
                    "title" to cargo.title,
                    "passenger_count" to cargo.passengerCount,
                )
            ),
            keyHolder,
            listOf("id", "title", "passenger_count").toTypedArray()
        )
        return Cargo(
            id = keyHolder.keys?.getValue("id") as Int,
            title = keyHolder.keys?.getValue("title") as String,
            passengerCount = keyHolder.keys?.getValue("passenger_count") as Int,
        )
    }

    override fun delete(id: Int) {
        jdbcTemplate.update(
            "delete from cargo where id = :id",
            mapOf(
                "id" to id,
            )
        )
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