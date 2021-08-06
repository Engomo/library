package com.library.library.costumer;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/costumers")
@Tag(name = "Management of costumers")
public class CostumerController {

    private CostumerService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CostumerDto createCostumer(@RequestBody CreateCostumerCommand command) {
        return service.createCostumer(command);
    }

    @GetMapping
    public List<CostumerDto> getCostumers(){
        return service.getCostumers();
    }

    @DeleteMapping("/{id}")
    public void deleteCostumerById(@PathVariable("id") long id) {
        service.deleteCostumerById(id);
    }

    @GetMapping("/{id}")
    public CostumerDto getCostumerById(@PathVariable("id") long id) {
       return service.getCostumerById(id);
    }
}
