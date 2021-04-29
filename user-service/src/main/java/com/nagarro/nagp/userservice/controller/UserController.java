package com.nagarro.nagp.userservice.controller;

import com.nagarro.nagp.userservice.service.ApplicationUserService;
import com.nagarro.nagp.userservice.service.model.ApplicationUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ApplicationUserService applicationUserService;

    @GetMapping
    public List<ApplicationUserDTO> getAll() {
        return applicationUserService.getAll();
    }

    @GetMapping("/{id}")
    public ApplicationUserDTO get(@PathVariable("id") String id) {
        return applicationUserService.get(id);
    }
}
