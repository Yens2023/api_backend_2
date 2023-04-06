package api.java.backend.controller.v1;

import api.java.backend.dto.cart.CreateCartDto;
import api.java.backend.dto.cart.ReadCartDto;
import api.java.backend.dto.cart.UpdateCartDto;
import api.java.backend.dto.custom.APIResponse;
import api.java.backend.service.transaction.ICartService;
import api.java.backend.utils.SD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    ICartService cartService;

    @PostMapping()
    public ResponseEntity<APIResponse> register(@RequestBody @Valid CreateCartDto request){
        var result = cartService.create(request);

        var response = APIResponse
                .<ReadCartDto>builder()
                .status(SD.SUCCESS)
                .results(result)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<APIResponse> update(@RequestBody @Valid UpdateCartDto request){
        var result = cartService.update(request);

        var response = APIResponse
                .<ReadCartDto>builder()
                .status(SD.SUCCESS)
                .results(result)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable @Positive Long id){

        cartService.deleteById(id);
        var response = APIResponse
                .builder()
                .status(SD.SUCCESS)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/getAllByUserId/{id}")
    public ResponseEntity<APIResponse> getAllByUserId(@PathVariable @Positive Long id){
        var result = cartService.getAllByUserId(id);
        var response  =APIResponse
                .<List<ReadCartDto>>builder()
                .results(result)
                .status(SD.SUCCESS)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
