package com.htpt.server.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.htpt.server.Models.ResponseObject;
import com.htpt.server.Models.UserModel;
import com.htpt.server.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired UserRepository ur;

    public List<UserModel> findAll() {
        List<UserModel> users = ur.findAll();
        return users;
    }

    public ResponseEntity<ResponseObject> Login(String email, String password) {
        Optional<UserModel> foundUser = ur.findByEmailAndPassword(email.toLowerCase().trim(), password.trim());
        return foundUser.isPresent() ? 
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Đăng nhập thành công", foundUser)
            )
            :
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Tài khoản hoặc mật khẩu không đúng", foundUser)
            );
    }

    public ResponseEntity<ResponseObject> update(String email, String passwordOld, String passwordNew, String passwordNew2) {
        Optional<UserModel> foundUser = ur.findByEmailAndPassword(email.toLowerCase().trim(), passwordOld.trim());
        if(foundUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Tài khoản hoặc mật khẩu không đúng", foundUser)
            );
        }
        if(!passwordNew.trim().equals(passwordNew2.trim())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", "Mật khẩu mới không trùng khớp", new UserModel())
            );
        }
        foundUser.get().setPassword(passwordNew.trim());
        try {
            ur.save(foundUser.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Cập nhật thành công", foundUser)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Không phù hợp", new UserModel())
            );
        }
    }

    public ResponseEntity<ResponseObject> insert(UserModel user) {
        Optional<UserModel> foundUser = ur.findById(user.getEmail().toLowerCase().trim());
        if(foundUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseObject("failed", "Email này đã được đăng ký", new UserModel())
            );
        }
        user.setEmail(user.getEmail().toLowerCase().trim());
        user.setPassword(user.getPassword().trim());
        try {
            ur.save(foundUser.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("success", "Tạo tài khoản thành công", user)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseObject("failed", "Không phù hợp", new UserModel())
            );
        }
    }
}
