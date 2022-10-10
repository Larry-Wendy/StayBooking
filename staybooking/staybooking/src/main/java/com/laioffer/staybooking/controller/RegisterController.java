package com.laioffer.staybooking.controller;

import com.laioffer.staybooking.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.laioffer.staybooking.model.User;
import com.laioffer.staybooking.model.UserRole;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController  // @ResponseBody + @Controller
public class RegisterController {
    private RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register/guest")
    public void addGuest(@RequestBody User user) { // @RequestBody annotation to help convert the request body from JSON format to a Java object
        registerService.add(user, UserRole.ROLE_GUEST);
    }

    @PostMapping("/register/host")
    public void addHost(@RequestBody User user) {
        registerService.add(user, UserRole.ROLE_HOST);
    }
}

// 后端代码为什么没有logout相关逻辑？不需要写
// logout的核心是要不要把token删掉，删掉token是客户端可以干的，后端不需要参与
// 第一个项目是session base，需要后端参与
// 为什么数据用不同的方式存储？结构化数据存储在数据库上，非结构化，二进制的媒体文件存储在网盘上