package com.htpt.server.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htpt.server.Models.ResponseObject;
import com.htpt.server.Models.UserModel;
import com.htpt.server.Models.Form.LoginModel;
import com.htpt.server.Repository.UserRepository;
import com.htpt.server.Service.UserService;

@RestController
@RequestMapping(value = "/api")
public class api {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @GetMapping(value = "/all")
    public ResponseEntity<ResponseObject> Find(HttpServletRequest request) {

        try {
            System.out.println(request.getRemoteAddr());
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Tìm thành công", userRepository.findAll())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Không phù hợp", new UserModel())
            );
        }
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<ResponseObject> Login(
        @RequestBody LoginModel form,
        HttpServletRequest request) {

        try {
            System.out.println(request.getRemoteAddr());

            return userService.Login(form.getEmail(), form.getPass());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Không phù hợp", new UserModel())
            );
        }
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<ResponseObject> Update(
        @RequestParam(name = "email", required = true) String email,
        @RequestParam(name = "password", required = true) String password,
        @RequestParam(name = "password_new", required = true) String password_new,
        @RequestParam(name = "password_new2", required = true) String password_new2) {


        return userService.update(email, password, password_new, password_new2);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<ResponseObject> Signup(@RequestBody UserModel user) {
        return userService.insert(user);
    }

    @GetMapping(value = "/ping")
    public Boolean Ping() {
        return true;
    }

}
