package api.java.backend.service.master.Impl;

import api.java.backend.domain.master.Brand;
import api.java.backend.domain.master.Category;
import api.java.backend.domain.master.Product;
import api.java.backend.dto.product.CreateProductDto;
import api.java.backend.dto.product.ReadProductDto;
import api.java.backend.dto.product.UpdateProductDto;
import api.java.backend.exception.global.ResourceNotFoundException;
import api.java.backend.mapper.AutoMapper;
import api.java.backend.repository.master.BrandRepository;
import api.java.backend.repository.master.CategoryRepository;
import api.java.backend.repository.master.ProductRepository;
import api.java.backend.service.master.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public ReadProductDto create(CreateProductDto request) {
        Brand brandFromDb = brandRepository.findById(request.getBrand_id())
                .orElseThrow(()-> new ResourceNotFoundException("El id proporcionado de la marca no es válida"));


        Category categoryFromDb = categoryRepository.findById(request.getCategory_id())
                .orElseThrow(()-> new ResourceNotFoundException("El id proporcionado de la categoria no es válida"));

        Product product = AutoMapper.copyProperties(request, Product.class);
        product.setCategory(categoryFromDb);
        product.setBrand(brandFromDb);

        ReadProductDto productDto = AutoMapper.copyProperties(product, ReadProductDto.class);
        productDto.setCategory(categoryFromDb.getName());
        productDto.setBrand(brandFromDb.getName());

        return productDto;
    }

    @Override
    public ReadProductDto update(UpdateProductDto request) {
        Brand brandFromDb = brandRepository.findById(request.getBrand_id())
                .orElseThrow(()-> new ResourceNotFoundException("El id proporcionado de la marca no es válida"));


        Category categoryFromDb = categoryRepository.findById(request.getCategory_id())
                .orElseThrow(()-> new ResourceNotFoundException("El id proporcionado de la categoria no es válida"));

        Product product = AutoMapper.copyProperties(request, Product.class);
        product.setCategory(categoryFromDb);
        product.setBrand(brandFromDb);

        ReadProductDto productDto = AutoMapper.copyProperties(product, ReadProductDto.class);
        productDto.setCategory(categoryFromDb.getName());
        productDto.setBrand(brandFromDb.getName());

        return productDto;
    }

    @Override
    public void deletById(Long id) {
        Product productFromDb = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El producto no existe"));
        productRepository.deleteById(id);
    }

    @Override
    public ReadProductDto findById(Long id) {
        Product productFromDb = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("El producto no existe"));

        ReadProductDto productDto = AutoMapper.copyProperties(productFromDb, ReadProductDto.class);
        productDto.setCategory(productFromDb.getCategory().getName());
        productDto.setBrand(productFromDb.getBrand().getName());

        return null;
    }

    @Override
    public List<ReadProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        List<ReadProductDto> productDtoList = new ArrayList<>();

        for(Product product: products){
            var v = AutoMapper.copyProperties(product,ReadProductDto.class);
            v.setBrand(product.getBrand().getName());
            v.setCategory(product.getCategory().getName());
            productDtoList.add(v);
        }

        return productDtoList;
    }
}
