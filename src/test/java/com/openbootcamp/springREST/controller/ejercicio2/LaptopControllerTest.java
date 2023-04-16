package com.openbootcamp.springREST.controller.ejercicio2;

import com.openbootcamp.springREST.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;
    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Laptop> laptops = Arrays.asList(response.getBody());
        assertTrue(laptops.size() > 0);
    }

    @Test
    void findOneById() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Laptop laptop = response.getBody();
        assertTrue(laptop.getId() != null);

    }

    @Test
    void create() {
        String json = """
                {
                    "marca": "Hp",
                    "modelo": "Hp1",
                    "disco": "SSD",
                    "ram": 16,
                    "pantalla": "HD"
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json);
        ResponseEntity<String> response = testRestTemplate.exchange("/api/laptops",HttpMethod.POST, request,String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        String responseBody = response.getBody();
        assertEquals(request , "La laptop se creo correctamente.");

    }

    @Test
    void update() {
        String json = """
                {
                    "marca": "Hp2",
                    "modelo": "Hp3",
                    "disco": "SSD",
                    "ram": 18,
                    "pantalla": "HD"
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops",HttpMethod.PUT, request,Laptop.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Laptop responseBody = response.getBody();
        assertEquals(responseBody.getMarca() , "Hp2");
        assertEquals(responseBody.getModelo() , "Hp3");
        assertEquals(responseBody.getDisco() , "SSD");
        assertEquals(responseBody.getRam() , 18);
        assertEquals(responseBody.getPantalla() , "HD");
    }

    @Test
    void delete() {
        String json = """
                {
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json);
        ResponseEntity<Laptop> laptop = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);
        ResponseEntity<String> response = testRestTemplate.exchange("/api/laptops/1",HttpMethod.DELETE, request,String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(response.getBody(),"La laptop" + laptop.getBody().getModelo() + " se elimino correctamente.");
    }

    @Test
    void deleteAll() {
    }
}