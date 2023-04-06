package api.java.backend.service.master;

import api.java.backend.dto.product.CreateProductDto;
import api.java.backend.dto.product.ReadProductDto;
import api.java.backend.dto.product.UpdateProductDto;

import java.util.List;

public interface IProductService {
    ReadProductDto create(CreateProductDto request);
    ReadProductDto update(UpdateProductDto request);
    void deletById(Long id);
    ReadProductDto findById(Long id);
    List<ReadProductDto> getAll();
}
