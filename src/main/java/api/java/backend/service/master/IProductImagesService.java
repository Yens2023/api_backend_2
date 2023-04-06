package api.java.backend.service.master;

import api.java.backend.dto.productimage.CreateProductImageDto;
import api.java.backend.dto.productimage.ReadProductImageDto;
import api.java.backend.dto.productimage.UpdateProductImageDto;

import java.util.List;

public interface IProductImagesService {
    ReadProductImageDto create (CreateProductImageDto request);
    ReadProductImageDto update (UpdateProductImageDto request);
    void deleteById(Long id);
    ReadProductImageDto findById(Long id);
    List<ReadProductImageDto> findByProductId(Long id);
}
