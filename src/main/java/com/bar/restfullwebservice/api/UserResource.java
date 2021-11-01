package com.bar.restfullwebservice.api;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.bar.restfullwebservice.dto.UserDTO;
import com.bar.restfullwebservice.entity.User;
import com.bar.restfullwebservice.exception.ResourceNotFoundException;
import com.bar.restfullwebservice.mapper.UserMapper;
import com.bar.restfullwebservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Htay Hlaing Aung on 9/21/2021
 */

@RestController
@RequiredArgsConstructor
public class UserResource {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping("/")
    public String welcome(){
        return "welcome";
    }

    // retrieve all user
    @GetMapping("/users")
    public List<UserDTO> retrieveUsers() {
        List<UserDTO> userDTOS = this.userMapper.toDTOs(this.userRepository.findAll());
        userDTOS.forEach(userDTO -> {
            Link selfLink = linkTo(methodOn(UserResource.class).retrieveUser(userDTO.getId())).withRel("user");
            userDTO.add(selfLink);
        });
        return userDTOS;
    }

    // retrieve specific user
    @GetMapping("/users/{id}")
    public UserDTO retrieveUser(@PathVariable Long id){
        User user = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("User's id %d not found", id)));
        UserDTO userDTO = this.userMapper.toDTO(user);
        Link selfLink = linkTo(methodOn(UserResource.class).retrieveUsers()).withRel("all-user");
        userDTO.add(selfLink);
        return userDTO;
    }

    // create user
    @PostMapping("/user/create")
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(fieldError -> System.out.println(fieldError.getDefaultMessage()));
            throw new ResourceNotFoundException(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        UserDTO savedUserDTO = this.userMapper.toDTO(this.userRepository.save(this.userMapper.toEntity(userDTO)));
        Link selfLink = linkTo(methodOn(UserResource.class).retrieveUsers()).withRel("all-user");
        savedUserDTO.add(selfLink);
        return savedUserDTO;
    }

    // delete user
    @DeleteMapping("/user/delete/{id}")
    public UserDTO delete(@PathVariable Long id){
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User's id %d not found", id)));
        this.userRepository.delete(user);
        UserDTO userDTO = this.userMapper.toDTO(user);
        Link selfLink = linkTo(methodOn(UserResource.class).retrieveUsers()).withRel("all-user");
        userDTO.add(selfLink);
        return userDTO;
    }

}
