package api.java.backend.controller.v2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class EjemploController {
    @GetMapping("/hello")
    public String hello(){
        return  "Hello, world version 2";
    }
}
