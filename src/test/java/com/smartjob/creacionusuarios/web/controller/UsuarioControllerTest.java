package com.smartjob.creacionusuarios.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        String dateString = "2025-05-24 20:30:00";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date created = formatter.parse(dateString);

        mockMvc.perform(post("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("Oskarina"))
                .andExpect(jsonPath("$.created").value(created))
                .andExpect(jsonPath("$.modified").value(created))
                .andExpect(jsonPath("$.lastLogin").value(created))
                .andExpect(jsonPath("$.token").value("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30"))
                .andExpect(jsonPath("$.isActive").value(true));
    }

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void printMappings() {
        System.out.println(webApplicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods());
    }

}
