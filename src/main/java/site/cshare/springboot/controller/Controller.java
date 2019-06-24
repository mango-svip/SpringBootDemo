package site.cshare.springboot.controller;


import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Data
public class Controller {


    @GetMapping
    public ResponseEntity hello() {

        return ResponseEntity.ok("Hello world");
    }


}
