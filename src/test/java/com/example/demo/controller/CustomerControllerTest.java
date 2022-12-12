package com.example.demo.controller;

import com.example.demo.dto.CustomerDto;
import com.example.demo.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@WebAppConfiguration
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_insert_then_bad_request() throws Exception {
        final CustomerDto customerDto = new CustomerDto();
        customerDto.setName("ali");
        customerDto.setSurname("veli");
        final UserDto user = new UserDto();
        user.setUserName("ali");
        user.setPassword("veli");
        customerDto.setUser(user);
        mockMvc.perform(post("/customer/add").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(0)
    void test_get_then_no_content() throws Exception {
        mockMvc.perform(get("/customer/10/orders").param("page", "0")
                        .param("size", "5")
                        .param("customerId", "10"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


}
