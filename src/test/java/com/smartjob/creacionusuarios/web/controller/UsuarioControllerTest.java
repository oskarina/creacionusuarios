package com.smartjob.creacionusuarios.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreacion() throws Exception {
        mockMvc.perform(post("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario creado!"));
    }

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void printMappings() {
        System.out.println(webApplicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods());
    }

}
