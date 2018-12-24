package com.lordalucard90.springboottutorials.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public Resource<User> retrieveSingleUsers(@PathVariable int id){
        User user = service.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id-" + id);

        Resource<User> resource = new Resource<User>(user);

        ControllerLinkBuilder linkTo =
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(savedUser.getId())
                                    .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<Object> deleteSingleUsers(@PathVariable int id){
        User user = service.deleteById(id);
        if (user == null)
            throw new UserNotFoundException("id-" + id);
        return ResponseEntity.ok().build();
    }
}
