package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping(value ="/{name}", method= RequestMethod.GET)
    public ResponseEntity<String> getData(@PathVariable("name") String name){
        return new ResponseEntity<>("Successfully called the method--"+name, HttpStatus.OK);
    }
}
