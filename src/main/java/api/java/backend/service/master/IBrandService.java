package api.java.backend.service.master;

import api.java.backend.dto.brand.CreateBrandDto;
import api.java.backend.dto.brand.ReadBrandDto;
import api.java.backend.dto.brand.UpdateBrandDto;

import java.util.List;

public interface IBrandService {
    ReadBrandDto create(CreateBrandDto request);
    ReadBrandDto update(UpdateBrandDto request);
    void deleteById(Long id);
    ReadBrandDto findById(Long id);
    List<ReadBrandDto> getAll();
}
