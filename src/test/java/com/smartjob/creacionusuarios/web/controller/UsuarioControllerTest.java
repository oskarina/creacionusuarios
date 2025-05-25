package com.smartjob.creacionusuarios.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreacion() throws Exception {
        String created = "2025-05-24T20:30:00";
        String json = """
                {
                "name": "Juan Rodriguez",
                "email": "juan@rodriguez.org",
                "password": "hunter2",
                "phones": [
                {
                "number": "1234567",
                "citycode": "1",
                "contrycode": "57"
                }
                ]
                }
                """;
        mockMvc.perform(post("/usuarios").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("37bdaee2-184e-430d-9629-19b60e45225f"))
                .andExpect(jsonPath("$.created").value(created))
                .andExpect(jsonPath("$.modified").value(created))
                .andExpect(jsonPath("$.lastLogin").value(created))
                .andExpect(jsonPath("$.token").value("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30"))
                .andExpect(jsonPath("$.isActive").value(true));
    }

    @Test
    void testCreacionEmptyRequest() throws Exception {
        String json = "abcdef";
        mockMvc.perform(post("/usuarios").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreacionCorreoInvalido() throws Exception {
        String json = """
                {
                "name": "Juan Rodriguez",
                "email": "correoinvalido.org",
                "password": "hunter2",
                "phones": [
                {
                "number": "1234567",
                "citycode": "1",
                "contrycode": "57"
                }
                ]
                }
                """;
        mockMvc.perform(post("/usuarios").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreacionDuplicado() throws Exception {
        String json = """
                {
                "name": "Juan Rodriguez",
                "email": "juan@rodriguez.org",
                "password": "hunter2",
                "phones": [
                {
                "number": "1234567",
                "citycode": "1",
                "contrycode": "57"
                }
                ]
                }
                """;
        mockMvc.perform(post("/usuarios").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        mockMvc.perform(post("/usuarios").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("El correo ya está registrado"));
    }

    @Test
    void testClaveInvalida() throws Exception {
        String json = """
                {
                "name": "Juan Rodriguez",
                "email": "juan@rodriguez.org",
                "password": "claveinvalida",
                "phones": [
                {
                "number": "1234567",
                "citycode": "1",
                "contrycode": "57"
                }
                ]
                }
                """;

        mockMvc.perform(post("/usuarios").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
