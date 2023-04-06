package api.java.backend.service.transaction;


import api.java.backend.dto.cart.CreateCartDto;
import api.java.backend.dto.cart.ReadCartDto;
import api.java.backend.dto.cart.UpdateCartDto;

import java.util.List;

public interface ICartService {
    ReadCartDto create(CreateCartDto request);
    ReadCartDto update(UpdateCartDto request);
    void deleteById(Long id);
    List<ReadCartDto> getAllByUserId(Long id);
}
