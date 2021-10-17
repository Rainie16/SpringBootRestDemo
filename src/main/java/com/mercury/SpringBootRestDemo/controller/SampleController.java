package com.mercury.SpringBootRestDemo.controller;

import com.mercury.SpringBootRestDemo.bean.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//RESTful Style
//name must same as the table you need to control, first letter lowercase & ending in "s"
//restcontroller = controller + responsebody
@RestController
//must have s at end, because it default is return getAll()/Post
@RequestMapping("/samples")
public class SampleController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping
    public void save(@RequestBody Sample sample){

    }
    //when we get by xxx just put variable after RequestMapping url
    //because it is id so it don't need getByName
    @GetMapping("/{name}")
    public Sample get(@PathVariable String name){
        System.out.println(name);
        //place holder {} it represent a variable
        logger.trace(name+" is a trace level log");
        logger.debug("{} is a debug level log",name);
        logger.info("{} is a info level log",name);
        logger.warn("{} is a warn level log",name);
        logger.error("{} is a error level log",name);

        //exception
        try{
            throw new Exception("This is my own exception");
        }catch(Exception e){
            logger.error("Testing throw", e);
        }
        return null;
    }
    //because it is not key add name in front
    @GetMapping("/age/{age}")
    public List<Sample> getByAge(@PathVariable int age){
        return new ArrayList<>();
    }
    //because it doesn't have parameter
    // default Get() = getAll()
    @GetMapping
    public List<Sample> getAll(){
        System.out.println("Running get all method");
        return new ArrayList<>();
    }
}
