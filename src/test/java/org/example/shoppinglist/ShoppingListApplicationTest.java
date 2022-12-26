package org.example.shoppinglist;

import org.example.shoppinglist.controller.RESTController;
import org.example.shoppinglist.model.Product;
import org.example.shoppinglist.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShoppingListApplicationTest {
    @Autowired
    private RESTController restController;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void restApi() throws Exception {
        assertThat(restController).isNotNull();
    }

    @Test
    public void listOfProducts() throws Exception {
        this.mockMvc.perform(post("/api/products/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\": \"testProduct\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("testProduct")));
    }

    @Test
    public void getProducts() throws Exception {
        this.mockMvc.perform(get("/api/products/"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}