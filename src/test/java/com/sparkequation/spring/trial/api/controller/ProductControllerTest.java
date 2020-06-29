package com.sparkequation.spring.trial.api.controller;

import com.sparkequation.spring.trial.api.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static com.sparkequation.spring.trial.api.TestData.products;
import static com.sparkequation.spring.trial.api.TestData.testProduct;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    private static final String URL = "/api/product/";

    private static final String ID = "42";

    @MockBean
    private ProductService service;

    @Autowired
    private MockMvc mockMvc;

    private String validJsonRequest;

    private String invalidJsonRequest;

    @BeforeEach
    void init() throws URISyntaxException, IOException {
        final ClassLoader classLoader = getClass().getClassLoader();

//        ObjectMapper mapper = new ObjectMapper();
//        mapper.

        validJsonRequest = Files.lines(Paths.get(classLoader.getResource("test_json/valid_product.json")
                .toURI()))
                .collect(Collectors.joining());
    }



    @Test
    void whenFindAllIdThenOk() throws Exception {

        given(service.getProducts()).willReturn(products);
        MockHttpServletRequestBuilder requestBuilder = get(URL);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void whenFindByIdThenOk() throws Exception {

        given(service.findProductById(eq(42))).willReturn(testProduct);
        MockHttpServletRequestBuilder requestBuilder = get(URL + ID)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    void whenPostValidProductThenOk() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validJsonRequest);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andReturn();
    }


}
