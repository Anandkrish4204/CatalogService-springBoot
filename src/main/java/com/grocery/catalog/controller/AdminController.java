package com.grocery.catalog.controller;

import com.grocery.catalog.model.User;
import com.grocery.catalog.model.UserListResponse;
import com.grocery.catalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("/users")
    public UserListResponse getAllUsers(){
        return catalogService.getAllUserFromUserService();
    }
}
