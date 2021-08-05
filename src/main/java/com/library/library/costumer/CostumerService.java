package com.library.library.costumer;

import com.library.library.book.BookDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CostumerService {

    private ModelMapper modelMapper;

    private CostumerRepository repository;

    public CostumerDto createCostumer(CreateCostumerCommand command) {
        Costumer costumer = new Costumer(command.getName());
        repository.save(costumer);
        return modelMapper.map(costumer, CostumerDto.class);
    }

    public List<CostumerDto> getCostumers() {
        return repository.findAll().stream()
                .map(c -> modelMapper.map(c, CostumerDto.class))
                .collect(Collectors.toList());
    }

    public void deleteCostumerById(long id) {
        repository.deleteById(id);
    }
}
