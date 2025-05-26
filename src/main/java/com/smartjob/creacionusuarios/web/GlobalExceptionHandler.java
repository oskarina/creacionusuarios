package com.smartjob.creacionusuarios.web;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value()); // <--- ESTADO 500
        body.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        // Intenta detectar si la violación es por el email.
        // Esto puede variar ligeramente según la base de datos y el mensaje exacto.
        // Aquí se asume que el mensaje de la excepción subyacente contendrá "email" o el nombre de tu índice único.
        // Ex: org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException: Unique index or primary key violation: "PUBLIC.UK_6DOT27A6D2Y0N80J2E3A0E5C9_INDEX_2 ON PUBLIC.USUARIOS(EMAIL)"
        if (ex.getCause() != null && ex.getCause().getMessage().toLowerCase().contains("email")) {
            body.put("mensaje", "El correo ya está registrado"); // <--- MENSAJE ESPECÍFICO
        } else {
            body.put("mensaje", "Ocurrió un error de base de datos inesperado."); // Mensaje genérico para otras violaciones
        }

        body.put("path", "/usuarios"); // O el path de la request

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Puedes añadir otros manejadores de excepciones aquí si lo necesitas
}