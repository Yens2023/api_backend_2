package api.java.backend.service.master.Impl;

import api.java.backend.domain.master.Category;
import api.java.backend.dto.category.CreateCategoryDto;
import api.java.backend.dto.category.ReadCategoryDto;
import api.java.backend.dto.category.UpdateCategoryDto;
import api.java.backend.exception.global.AlreadyExistsException;
import api.java.backend.exception.global.ResourceNotFoundException;
import api.java.backend.mapper.AutoMapper;
import api.java.backend.repository.master.CategoryRepository;
import api.java.backend.service.master.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public ReadCategoryDto create(CreateCategoryDto request) {
        if(categoryRepository.existsByName(request.getName())){
            throw new AlreadyExistsException("La categoria ingresada ya existe");
        }
        Category category = AutoMapper.copyProperties(request, Category.class);
        categoryRepository.save(category);
        ReadCategoryDto categoryDto = AutoMapper.copyProperties(category,ReadCategoryDto.class);

        return categoryDto;
    }

    @Override
    public ReadCategoryDto update(UpdateCategoryDto request) {
        Category categoryFromDb = categoryRepository.findById(request.getId())
                .orElseThrow(()-> new ResourceNotFoundException("La categoria no esta disponible"));

        if(categoryRepository.existsByNameAndIdNot(request.getName(), request.getId())){
            throw new AlreadyExistsException("La categoria ingresada ya existe");
        }
        categoryFromDb.setName(request.getName());
        categoryRepository.save(categoryFromDb);

        ReadCategoryDto categoryDto = AutoMapper.copyProperties(categoryFromDb, ReadCategoryDto.class);

        return categoryDto;
    }

    @Override
    public void deleteById(Long id) {
        Category categoryFromDb = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("La categoria no esta disponible"));

        categoryRepository.deleteById(id);
    }

    @Override
    public ReadCategoryDto findById(Long id) {
        Category categoryFromDb = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("La categoria no esta disponible"));
        ReadCategoryDto categoryDto = AutoMapper.copyProperties(categoryFromDb,ReadCategoryDto.class);

        return categoryDto;
    }

    @Override
    public List<ReadCategoryDto> getAll() {
        List<Category> categoriesFromDb = categoryRepository.findAll();
        List<ReadCategoryDto> categories = AutoMapper.copyListProperties(categoriesFromDb, ReadCategoryDto.class);
        return categories;
    }
}
