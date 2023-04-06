package api.java.backend.controller.v1;

import api.java.backend.dto.custom.APIResponse;
import api.java.backend.dto.productimage.CreateProductImageDto;
import api.java.backend.dto.productimage.ReadProductImageDto;
import api.java.backend.dto.productimage.UpdateProductImageDto;
import api.java.backend.service.master.IProductImagesService;
import api.java.backend.utils.SD;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/v1/productimage")
public class ProductImageController {
    private IProductImagesService productImagesService;

    public ProductImageController(IProductImagesService productImagesService) {
        this.productImagesService = productImagesService;
    }
    @PostMapping()
    public ResponseEntity<APIResponse> register(@RequestBody @Valid CreateProductImageDto request){
        var result = productImagesService.create(request);

        var response = APIResponse
                .<ReadProductImageDto>builder()
                .status(SD.SUCCESS)
                .results(result)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<APIResponse> update(@RequestBody @Valid UpdateProductImageDto request){
        var result = productImagesService.update(request);

        var response = APIResponse
                .<ReadProductImageDto>builder()
                .status(SD.SUCCESS)
                .results(result)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable @Positive Long id){
        productImagesService.deleteById(id);

        var response = APIResponse
                .builder()
                .status(SD.SUCCESS)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
    @GetMapping("/findByProductId/{id}")
    public ResponseEntity<APIResponse> findByProductId(@PathVariable @Positive Long id){
        var result = productImagesService.findByProductId(id);
        var response  =APIResponse
                .<List<ReadProductImageDto>>builder()
                .results(result)
                .status(SD.SUCCESS)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getById(@PathVariable @Positive Long id){
        var result = productImagesService.findById(id);
        var response  =APIResponse
                .<ReadProductImageDto>builder()
                .results(result)
                .status(SD.SUCCESS)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
