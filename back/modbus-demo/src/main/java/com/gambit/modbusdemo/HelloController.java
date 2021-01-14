package com.gambit.modbusdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private ConverterService converter;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(
            value = "/api",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postAction(@RequestBody FileText fileText) {
        return "You did sent: " + fileText.getText();
    }
}
