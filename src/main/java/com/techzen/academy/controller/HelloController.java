package com.techzen.academy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello") //API, endpoint :http://localhost:8080/hello
    //http://localhost:8080/hello?name=HoangVanDung&address=daklak
    public String sayHello(@RequestParam(defaultValue = "Name") String name,@RequestParam(defaultValue = "daklak") String address) {
//        cách thông thường hoặc sử dụng RequestParam
//        if(name == null){
//            name = "World";
//        }
//        if(address == null){
//            address = "Address";
//        }
        return "Hello " + name + " - " + address;
    }
}
