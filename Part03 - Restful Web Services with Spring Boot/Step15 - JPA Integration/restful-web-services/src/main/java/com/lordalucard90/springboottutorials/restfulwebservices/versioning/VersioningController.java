package com.lordalucard90.springboottutorials.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

    @GetMapping(path = "v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Pinco Pallino");
    }

    @GetMapping(path = "v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Pinco", "Pallino"));
    }

    @GetMapping(value = "person", params = "version=1")
    public PersonV1 paramV1(){
        return new PersonV1("Pinco Pallino");
    }

    @GetMapping(value = "person", params = "version=2")
    public PersonV2 paramV2(){
        return new PersonV2(new Name("Pinco", "Pallino"));
    }

    @GetMapping(value = "person", headers = "X-API-VERSION=1")
    public PersonV1 headerV1(){
        return new PersonV1("Pinco Pallino");
    }

    @GetMapping(value = "person", headers = "X-API-VERSION=2")
    public PersonV2 headerV2(){
        return new PersonV2(new Name("Pinco", "Pallino"));
    }

    @GetMapping(value = "person", produces = "application/vnd.company.app-v1+json")
    public PersonV1 producesV1(){
        return new PersonV1("Pinco Pallino");
    }

    @GetMapping(value = "person", produces = "application/vnd.company.app-v2+json")
    public PersonV2 producesV2(){
        return new PersonV2(new Name("Pinco", "Pallino"));
    }
}
