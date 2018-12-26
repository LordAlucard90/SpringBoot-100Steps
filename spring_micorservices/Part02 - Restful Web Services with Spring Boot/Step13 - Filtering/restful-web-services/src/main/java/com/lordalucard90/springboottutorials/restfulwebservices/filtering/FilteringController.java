package com.lordalucard90.springboottutorials.restfulwebservices.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping(path = "/filtering")
    public SomeBean retrieveSomeBean(){
        return new SomeBean("value1", "value2", "value3");
    }

    @GetMapping(path = "/filtering-list")
    public List<SomeBean> retrieveListOfSomeBean(){
        return Arrays.asList(
                new SomeBean("valueA1", "valueA2", "valueA3"),
                new SomeBean("valueB1", "valueB2", "valueB3")
        );
    }

}
