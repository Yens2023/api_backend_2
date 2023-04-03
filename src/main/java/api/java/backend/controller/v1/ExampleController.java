package api.java.backend.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class ExampleController {

    @GetMapping()
    public String getHello(){
        return "Hola";
    }

}
