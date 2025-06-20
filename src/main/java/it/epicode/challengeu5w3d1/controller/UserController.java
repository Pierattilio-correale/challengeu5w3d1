package it.epicode.challengeu5w3d1.controller;

import it.epicode.challengeu5w3d1.dto.UserDto;
import it.epicode.challengeu5w3d1.dto.ViaggioDto;
import it.epicode.challengeu5w3d1.exception.AlreadyExistException;
import it.epicode.challengeu5w3d1.exception.NotFoundException;
import it.epicode.challengeu5w3d1.exception.ValidationException;
import it.epicode.challengeu5w3d1.model.Stato;
import it.epicode.challengeu5w3d1.model.User;
import it.epicode.challengeu5w3d1.model.Viaggio;
import it.epicode.challengeu5w3d1.repository.UserRepository;
import it.epicode.challengeu5w3d1.service.UserService;
import it.epicode.challengeu5w3d1.service.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/users")
    public List<User> getAllUser(){
        return  userService.getAllUser();
    }
    @GetMapping("users/{id}")
    public User getUser(@PathVariable int id) throws NotFoundException {
        return  userService.getUser(id);
    }
    @PutMapping("users/{id}")
    public User aggiornaUser(@PathVariable int id , @RequestBody @Validated UserDto userDto, BindingResult bindingResult) throws ValidationException, NotFoundException {
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).reduce("",(e, s)->e+s));

        }
        return  userService.updateUser(id,userDto);


    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) throws NotFoundException {
        userService.deleteUser(id);
    }
}

