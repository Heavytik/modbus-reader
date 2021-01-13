package com.gambit.modbusdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private ConverterService converter;

    @RequestMapping("/")
    public List<String> index() {
        return converter.allStrings();
    }
}
