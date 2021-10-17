package com.mercury.SpringBootRestDemo.controller;

import com.mercury.SpringBootRestDemo.bean.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


//@Controller
@RestController//= Controller + ResponseBody
@RequestMapping("tests")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //@RequestMapping("/foo")
    //@ResponseBody
    @GetMapping("/foo")
    public void foo(){
        System.out.println("Foo......");
    }
    @GetMapping("/bar")
    public Sample bar(){
        System.out.println("bar()...");
        return new Sample("bar",10);
    }
    @GetMapping("/add")
    public int add(@RequestParam("x") int x, @RequestParam int y){
        return x+y;
    }
    @GetMapping("/mul/{x}/{y}")
    public int mul(@PathVariable int x, @PathVariable int y){
        return x*y;
    }
    @PostMapping("/getAge")
    public int test(@RequestBody Sample sample){
        return 200-sample.getAge();
    }



}
