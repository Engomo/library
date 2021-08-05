package com.library.library.costumer;

import com.library.library.book.BookDto;
import com.library.library.book.CreateBookCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from costumers")
public class CostumerControllerIT {

    CostumerDto costumer1;
    CostumerDto costumer2;

    @Autowired
    TestRestTemplate template;

    @BeforeEach
    void setUp() {
        costumer1 = template.postForObject("/api/costumers", new CreateCostumerCommand("Jack"), CostumerDto.class);
        costumer2 = template.postForObject("/api/costumers", new CreateCostumerCommand("Jane"), CostumerDto.class);
    }

    @Test
    void testCreateCostumer() {
        CostumerDto result = template.postForObject("/api/costumers", new CreateCostumerCommand("Jack"), CostumerDto.class);

        assertEquals("Jack", result.getName());
    }

    @Test
    void testGetBooks() {

        List<CostumerDto> excepted = template.exchange("/api/costumers", HttpMethod.GET, null, new ParameterizedTypeReference<List<CostumerDto>>() {
        }).getBody();

        assertEquals(2, excepted.size());
        assertThat(excepted)
                .extracting(CostumerDto::getName)
                .containsExactly("Jack", "Jane");
    }

    @Test
    void testDeleteCostumerById () {
        long id = costumer1.getId();
        template.delete("/api/costumers/" + id);

        List<CostumerDto> excepted = template.exchange("/api/costumers", HttpMethod.GET, null, new ParameterizedTypeReference<List<CostumerDto>>() {
        }).getBody();

        assertEquals(1, excepted.size());
        assertThat(excepted)
                .extracting(CostumerDto::getName)
                .containsExactly("Jane");
    }
}
