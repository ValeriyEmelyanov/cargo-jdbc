package com.example.cargojdbc.repository

import com.example.cargojdbc.model.Cargo
import com.example.cargojdbc.util.getIntOrNull
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.sql.Types

@Repository
class CargoRepositoryImpl(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
) : CargoRepository {
    override fun getAll(pageIndex: Int): List<Cargo> =
        jdbcTemplate.query(
            "select * from cargo order by id limit :limit offset :offset",
            mapOf(
                "limit" to PAGE_SIZE,
                "offset" to pageIndex * PAGE_SIZE,
            ),
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
            "insert into cargo (title, passenger_count, load_capacity) " +
                    "values (:title, :passenger_count, :load_capacity)",
            cargo.toCreateMapSqlParameterSource(),
            keyHolder,
            listOf("id", "title", "passenger_count", "load_capacity").toTypedArray()
        )
        return keyHolder.toCargo()
    }

    override fun batchCreate(cargos: List<Cargo>): IntArray {
        val batchValues = cargos.map { cargo ->
            cargo.toCreateMapSqlParameterSource()
        }.toTypedArray()
        val done = jdbcTemplate.batchUpdate(
            "insert into cargo (title, passenger_count, load_capacity) " +
                    "values (:title, :passenger_count, :load_capacity)",
            batchValues
        )
        return done
    }

    override fun update(id: Int, cargo: Cargo): Cargo {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            "update cargo set title = :title, passenger_count = :passenger_count, load_capacity = :load_capacity " +
                    "where id = :id",
            cargo.toUpdateMapSqlParameterSource(id),
            keyHolder,
            listOf("id", "title", "passenger_count", "load_capacity").toTypedArray()
        )
        return keyHolder.toCargo()
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
        const val PAGE_SIZE = 5
        val ROW_MAPPER = RowMapper<Cargo> { rs, _ ->
            Cargo(
                id = rs.getInt("id"),
                title = rs.getString("title"),
                passengerCount = rs.getIntOrNull("passenger_count"),
                loadCapacity = rs.getIntOrNull("load_capacity"),
            )
        }
    }

    private fun GeneratedKeyHolder.toCargo() =
        Cargo(
            id = keys?.getValue("id") as Int,
            title = keys?.getValue("title") as String,
            passengerCount = keys?.getValue("passenger_count") as Int?,
            loadCapacity = keys?.getValue("load_capacity") as Int?,
        )

    private fun Cargo.toCreateMapSqlParameterSource() =
        MapSqlParameterSource()
            .addValue("title", title, Types.VARCHAR)
            .addValue("passenger_count", passengerCount, Types.INTEGER)
            .addValue("load_capacity", loadCapacity, Types.INTEGER)

    private fun Cargo.toUpdateMapSqlParameterSource(_id: Int) =
        toCreateMapSqlParameterSource()
            .addValue("id", _id, Types.INTEGER)
}