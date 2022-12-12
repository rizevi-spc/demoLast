package com.example.demo.controller;

import com.example.demo.dto.CustomerOrderDto;
import com.example.demo.dto.OrderSearchRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
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
class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void test_insert_then_ok() throws Exception {
        final CustomerOrderDto customerOrderDto = new CustomerOrderDto();
        customerOrderDto.setPrice(1D);
        mockMvc.perform(post("/order/add").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerOrderDto)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void test_order_search_then_bad_request() throws Exception {
        final OrderSearchRequest orderSearch = new OrderSearchRequest();

        mockMvc.perform(post("/order/search").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderSearch)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void test_get_one_then_no_content() throws Exception {
        mockMvc.perform(get("/order/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
