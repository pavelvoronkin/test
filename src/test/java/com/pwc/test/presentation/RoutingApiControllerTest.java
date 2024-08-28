package com.pwc.test.presentation;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class RoutingApiControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @SneakyThrows
    void shouldReturnRouting() {
        // when
        mockMvc
                .perform(get("/routing/CZE/ITA"))

                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.route").isArray())
                .andExpect(jsonPath("$.route.length()").value(3))
                .andExpect(jsonPath("$.route[0]").value("CZE"))
                .andExpect(jsonPath("$.route[1]").value("AUT"))
                .andExpect(jsonPath("$.route[2]").value("ITA"));
    }

    @Test
    @SneakyThrows
    void shouldReturnBadRequestWhenRoutingImpossible() {
        // when
        mockMvc
                .perform(get("/routing/CZE/XYZ"))

                // then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid destination country XYZ"));
    }
}