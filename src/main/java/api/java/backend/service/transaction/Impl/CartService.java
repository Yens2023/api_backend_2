package api.java.backend.service.transaction.Impl;

import api.java.backend.domain.master.Product;
import api.java.backend.domain.security.User;
import api.java.backend.domain.transaction.Cart;
import api.java.backend.dto.cart.CreateCartDto;
import api.java.backend.dto.cart.ReadCartDto;
import api.java.backend.dto.cart.UpdateCartDto;
import api.java.backend.exception.global.ResourceNotFoundException;
import api.java.backend.mapper.AutoMapper;
import api.java.backend.repository.master.ProductImagesRepository;
import api.java.backend.repository.master.ProductRepository;
import api.java.backend.repository.security.UserRepository;
import api.java.backend.repository.transaction.CartRepository;
import api.java.backend.service.transaction.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ProductImagesRepository productImagesRepository;

    @Override
    public ReadCartDto create(CreateCartDto request) {

        if(!userRepository.existsById(request.getUser_id())){
            throw new ResourceNotFoundException("El usuario no es válido");
        }
        User user = new User();
        user.setId(request.getUser_id());

        Product productFromDb = productRepository.findById(request.getProduct_id())
                .orElseThrow(()-> new ResourceNotFoundException("El producto no existe"));


        Cart cartFromDb = AutoMapper.copyProperties(request,Cart.class);
        cartFromDb.setUser(user);
        cartFromDb.setProduct(productFromDb);
        cartFromDb.setPrice(productFromDb.getPrice());

        cartRepository.save(cartFromDb);
        ReadCartDto response = AutoMapper.copyProperties(cartFromDb,ReadCartDto.class);
        return response;
    }

    @Override
    public ReadCartDto update(UpdateCartDto request) {

        Cart cartFromDb = cartRepository.findByUser_IdAndProduct_Id(request.getUser_id(), request.getProduct_id())
                .orElseThrow(()-> new ResourceNotFoundException("No es posible actualizar el producto"));

        Double total = request.getQuantity() * cartFromDb.getPrice();
        cartFromDb.setQuantity(request.getQuantity());
        cartFromDb.setTotal(total);
        cartRepository.save(cartFromDb);

        ReadCartDto response = AutoMapper.copyProperties(cartFromDb,ReadCartDto.class);
        return response;
    }

    @Override
    public void deleteById(Long id) {
        if(!cartRepository.existsById(id)){
            throw new ResourceNotFoundException("El id proporcionado no es válido");
        }
        cartRepository.deleteById(id);
    }

    @Override
    public List<ReadCartDto> getAllByUserId(Long id) {
        List<Cart> cartsFromDb = cartRepository.findByUser_Id(id);
        List<ReadCartDto> carts = AutoMapper.copyListProperties(cartsFromDb, ReadCartDto.class);
        return carts;
    }
}
