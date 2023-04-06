package api.java.backend.service.master;

import api.java.backend.dto.category.CreateCategoryDto;
import api.java.backend.dto.category.ReadCategoryDto;
import api.java.backend.dto.category.UpdateCategoryDto;

import java.util.List;

public interface ICategoryService {
    ReadCategoryDto create(CreateCategoryDto request);
    ReadCategoryDto update(UpdateCategoryDto request);
    void deleteById(Long id);
    ReadCategoryDto findById(Long id);
    List<ReadCategoryDto> getAll();
}
