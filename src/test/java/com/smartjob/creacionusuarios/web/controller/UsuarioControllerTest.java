package com.smartjob.creacionusuarios.web.controller;

import com.smartjob.creacionusuarios.persistence.UsuarioSpringRepository;
import org.junit.jupiter.api.BeforeEach;
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
    private UsuarioSpringRepository usuarioSpringRepository;

    @BeforeEach
    void limpiarBD() {
        usuarioSpringRepository.deleteAll();
    }

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
                "cityCode": "1",
                "countryCode": "57"
                }
                ]
                }
                """;
        mockMvc.perform(post("/usuarios").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                //.andExpect(jsonPath("$.created").value(created))
                //.andExpect(jsonPath("$.modified").value(created))
                //.andExpect(jsonPath("$.lastLogin").value(created))
                .andExpect(jsonPath("$.token").value("KrljkMfb40Od500MmwsXZw=="))
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
                "cityCode": "1",
                "countryCode": "57"
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
                "cityCode": "1",
                "countryCode": "57"
                }
                ]
                }
                """;
        mockMvc.perform(post("/usuarios").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        mockMvc.perform(post("/usuarios").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is5xxServerError())
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
