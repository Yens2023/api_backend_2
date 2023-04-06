package api.java.backend.service.master.Impl;

import api.java.backend.domain.master.Product;
import api.java.backend.domain.master.ProductImages;
import api.java.backend.dto.productimage.CreateProductImageDto;
import api.java.backend.dto.productimage.ReadProductImageDto;
import api.java.backend.dto.productimage.UpdateProductImageDto;
import api.java.backend.exception.global.ResourceNotFoundException;
import api.java.backend.mapper.AutoMapper;
import api.java.backend.repository.master.ProductImagesRepository;
import api.java.backend.repository.master.ProductRepository;
import api.java.backend.service.master.IProductImagesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImagesService implements IProductImagesService {
    @Autowired
    private ProductImagesRepository productImagesRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public ReadProductImageDto create(CreateProductImageDto request) {

        if(!productRepository.existsById(request.getProduct_id())){
            throw new ResourceNotFoundException("El id de producto no es válido");
        }
        Product productFromDb = new Product();
        productFromDb.setId(request.getProduct_id());

        ProductImages productImages = AutoMapper.copyProperties(request, ProductImages.class);
        productImages.setProduct(productFromDb);
        productImagesRepository.save(productImages);
        ReadProductImageDto response = AutoMapper.copyProperties(productImages, ReadProductImageDto.class);


        return response;
    }

    @Override
    public ReadProductImageDto update(UpdateProductImageDto request) {
        ProductImages productImagesFromDb = productImagesRepository.findById(request.getId())
                .orElseThrow(()-> new ResourceNotFoundException("El id de producto imagen no es válido"));

        BeanUtils.copyProperties(request, productImagesFromDb);
        productImagesRepository.save(productImagesFromDb);
        ReadProductImageDto response = AutoMapper.copyProperties(productImagesFromDb, ReadProductImageDto.class);
        return response;
    }

    @Override
    public void deleteById(Long id) {
        if(!productImagesRepository.existsById(id)){
            throw new ResourceNotFoundException("El id de producto imagen no es válido");
        }
        productImagesRepository.deleteById(id);
    }

    @Override
    public ReadProductImageDto findById(Long id) {
        ProductImages productImagesFromDb = productImagesRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El id de producto imagen no es válido"));

        ReadProductImageDto response = AutoMapper.copyProperties(productImagesFromDb, ReadProductImageDto.class);

        return  response;
    }

    @Override
    public List<ReadProductImageDto> findByProductId(Long id) {
        if(!productRepository.existsById(id)){
            throw new ResourceNotFoundException("El id de producto no es válido");
        }
        List<ProductImages> images = productImagesRepository.findByProduct_Id(id);
        List<ReadProductImageDto> response = AutoMapper.copyListProperties(images, ReadProductImageDto.class);
        return response;
    }
}
