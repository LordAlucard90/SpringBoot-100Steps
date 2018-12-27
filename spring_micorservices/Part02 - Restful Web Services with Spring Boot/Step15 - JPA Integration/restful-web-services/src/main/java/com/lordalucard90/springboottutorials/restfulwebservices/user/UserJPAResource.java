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
import java.util.Optional;

@RestController
public class UserJPAResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public Resource<User> retrieveSingleUsers(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("id-" + id);

        Resource<User> resource = new Resource<User>(user.get());

        ControllerLinkBuilder linkTo =
                ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));

        return resource;
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(savedUser.getId())
                                    .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteSingleUsers(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getAllUserPosts(@PathVariable int id){
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent())
            throw new UserNotFoundException("id-" + id);

        return userOptional.get().getPosts();
    }

    @PostMapping(path = "/jpa/users/{id}/posts/")
    public ResponseEntity<Object> createPost(@PathVariable int id, @Valid @RequestBody Post post){
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent())
            throw new UserNotFoundException("id-" + id);

        User user = userOptional.get();

        post.setUser(user);

        postRepository.save(post);

//        TODO GET user post
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
