package com.example.cargojdbc.exception

import org.springframework.http.HttpStatus

class CargoNotFoundException(id: Int) : BaseException(
    errorCode = "cargo.not.found",
    message = "Cargo with id = $id not found",
    status = HttpStatus.NOT_FOUND,
)