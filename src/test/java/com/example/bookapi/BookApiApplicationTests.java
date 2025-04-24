package com.example.bookapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class BookApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createBook_ShouldReturnCreated() throws Exception {
        String bookJson = "{\"title\":\"Test Book\",\"author\":\"Author Name\",\"published\":true}";

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllBooks_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteBook_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getBooksByAuthor_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/books?author=Author Name"))
                .andExpect(status().isOk());
    }

    @Test
    void getBooksByPublishedStatus_ShouldReturnOk() throws Exception {
        mockMvc.perform(get("/books?published=true"))
                .andExpect(status().isOk());
    }
}