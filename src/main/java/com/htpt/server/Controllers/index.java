package com.htpt.server.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.htpt.server.Repository.UserRepository;

@Controller
@RequestMapping
public class index {

    @Autowired UserRepository ur;
    
    @GetMapping("/index")
    public String loginGet() {
        
        return "index";
    }
}
