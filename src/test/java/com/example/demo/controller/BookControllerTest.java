package com.example.demo.controller;

import com.example.demo.dto.BookDto;
import com.example.demo.dto.BookInsert;
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
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test_book_insert_then_ok() throws Exception {
        final BookInsert bookInsert = new BookInsert();
        bookInsert.setName("Budala");
        bookInsert.setQuantity(1L);
        bookInsert.setPrice(1D);
        mockMvc.perform(post("/book/add").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookInsert)))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void test_book_update_then_ok() throws Exception {
        final BookDto book = new BookDto();
        book.setName("Budala");
        book.setId(1L);
        book.setQuantity(1L);
        book.setPrice(1D);
        mockMvc.perform(post("/book/update").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void test_book_get_then_ok() throws Exception {
        mockMvc.perform(get("/book/add"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
