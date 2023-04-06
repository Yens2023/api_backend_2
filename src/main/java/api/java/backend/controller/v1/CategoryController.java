package api.java.backend.controller.v1;

import api.java.backend.dto.category.CreateCategoryDto;
import api.java.backend.dto.category.ReadCategoryDto;
import api.java.backend.dto.category.UpdateCategoryDto;
import api.java.backend.dto.custom.APIResponse;
import api.java.backend.service.master.ICategoryService;
import api.java.backend.utils.SD;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ResponseEntity<APIResponse> register(@RequestBody @Valid CreateCategoryDto request){
        var result = categoryService.create(request);

        var response = APIResponse
                .<ReadCategoryDto>builder()
                .status(SD.SUCCESS)
                .results(result)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<APIResponse> update(@RequestBody @Valid UpdateCategoryDto request){
        var result = categoryService.update(request);

        var response = APIResponse
                .<ReadCategoryDto>builder()
                .status(SD.SUCCESS)
                .results(result)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable @Positive Long id){
        categoryService.deleteById(id);

        var response = APIResponse
                .builder()
                .status(SD.SUCCESS)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
    @GetMapping()
    public ResponseEntity<APIResponse> getAll(){
        var result = categoryService.getAll();
        var response  =APIResponse
                .<List<ReadCategoryDto>>builder()
                .results(result)
                .status(SD.SUCCESS)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getById(@PathVariable @Positive Long id){
        var result = categoryService.findById(id);
        var response  =APIResponse
                .<ReadCategoryDto>builder()
                .results(result)
                .status(SD.SUCCESS)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
